package com.kok1337.sync.data.repository

import com.kok1337.sync.data.datasource.cache.BaseSyncCache
import com.kok1337.sync.data.datasource.database.dao.BaseSyncDao
import com.kok1337.sync.data.datasource.database.entity.BaseSyncEntity
import com.kok1337.sync.data.exception.SyncApiModelNotFoundException
import com.kok1337.sync.data.mapper.BaseSyncMapper
import com.kok1337.sync.data.model.BaseSyncApiModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseSyncDataRepository<Id, ApiModel : BaseSyncApiModel<Id>, Entity : BaseSyncEntity<Id>>(
    private val baseSyncMapper: BaseSyncMapper<ApiModel, Entity>,
    private val baseSyncCache: BaseSyncCache<Id, ApiModel>,
    private val baseSyncDao: BaseSyncDao<Id, Entity>,
    private val repositoryDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {
    val items: StateFlow<List<ApiModel>> = baseSyncCache.items

    suspend fun findById(id: Id): ApiModel = withContext(repositoryDispatcher) {
        return@withContext items.value.firstOrNull { cachedItem -> cachedItem.id == id }
            ?: throw SyncApiModelNotFoundException()
    }

    suspend fun findAllByIds(
        ids: Iterable<Id>,
    ): Iterable<ApiModel> = withContext(repositoryDispatcher) {
        val apiModels = items.value.filter { item -> ids.contains(item.id) }
        if (apiModels.size != ids.count()) throw SyncApiModelNotFoundException()
        return@withContext apiModels
    }

    open suspend fun pull() = withContext(repositoryDispatcher) {
        val entitiesForCache = findAllEntities()
        val apiModelsForCache = entitiesForCache.map(baseSyncMapper::entityToApiModel)
        baseSyncCache.pushItems(apiModelsForCache)
    }

    open suspend fun push() = withContext(repositoryDispatcher) {
        val userId = getUserId()

        val apiModelsToSave: List<ApiModel> = items.value
        val loadedEntities: Iterable<Entity> = findAllEntities()
        val allUniqueIds =
            (apiModelsToSave.map { it.id } + loadedEntities.map { it.id }).distinctBy { it }

        val savedAndLoadedPairs: Iterable<Pair<ApiModel?, Entity?>> = allUniqueIds.map { id ->
            val apiModelToSave = apiModelsToSave.firstOrNull { it.id == id }
            val loadedEntity = loadedEntities.firstOrNull { it.id == id }
            Pair(apiModelToSave, loadedEntity)
        }

        val idsToDelete = mutableListOf<Id>()
        val apiModelsToInsert = mutableListOf<ApiModel>()
        val saveAndLoadPairsToUpdate = mutableListOf<Pair<ApiModel, Entity>>()

        savedAndLoadedPairs.forEach { (savedApiModel, loadedEntity) ->
            if (savedApiModel == null) {
                idsToDelete.add(loadedEntity!!.id)
                return@forEach
            }
            if (loadedEntity == null) {
                apiModelsToInsert.add(savedApiModel)
                return@forEach
            }
            saveAndLoadPairsToUpdate.add(Pair(savedApiModel, loadedEntity))
        }

        // Удаление
        launch { baseSyncDao.deleteAllByIds(userId, idsToDelete) }
        // Добавление
        launch {
            val entitiesToInsert = apiModelsToInsert
                .map { apiModelToInsert -> baseSyncMapper.apiModelToEntity(apiModelToInsert) }
            baseSyncDao.insertAll(userId, entitiesToInsert)
        }
        // Обновление
        launch {
            val entitiesToUpdate = saveAndLoadPairsToUpdate
                .map { baseSyncMapper.mergeApiModelIntoEntity(it.first, it.second) }
            baseSyncDao.updateAll(userId, entitiesToUpdate)
        }
    }

    protected abstract suspend fun getUserId(): Int

    protected abstract suspend fun findAllEntities(): Iterable<Entity>

    suspend fun clearCache() = withContext(repositoryDispatcher) {
        baseSyncCache.clear()
    }

    suspend fun updateById(
        id: Id,
        transformation: (ApiModel) -> ApiModel,
    ) = withContext(repositoryDispatcher) {
        baseSyncCache.updateById(id, transformation)
    }

    suspend fun add(apiModel: ApiModel) = withContext(repositoryDispatcher) {
        baseSyncCache.add(apiModel)
    }

    open suspend fun deleteById(id: Id) = withContext(repositoryDispatcher) {
        baseSyncCache.deleteById(id)
    }
}