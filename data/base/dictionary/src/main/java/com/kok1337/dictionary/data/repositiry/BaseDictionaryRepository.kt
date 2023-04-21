package com.kok1337.dictionary.data.repositiry

import com.kok1337.dictionary.data.datasource.cache.BaseDictionaryCache
import com.kok1337.dictionary.data.datasource.database.dao.BaseDictionaryDao
import com.kok1337.dictionary.data.datasource.database.entity.BaseDictionaryEntity
import com.kok1337.dictionary.data.exception.DictionaryApiModelNotFoundException
import com.kok1337.dictionary.data.mapper.BaseDictionaryMapper
import com.kok1337.dictionary.data.model.BaseDictionaryApiModel

abstract class BaseDictionaryRepository<ApiModel : BaseDictionaryApiModel, Entity : BaseDictionaryEntity>(
    private val baseDictionaryMapper: BaseDictionaryMapper<ApiModel, Entity>,
    private val baseDictionaryCache: BaseDictionaryCache<ApiModel>,
    private val baseDictionaryDao: BaseDictionaryDao<Entity>,
) {
    suspend fun findById(id: Int): ApiModel {
        val apiModel = baseDictionaryCache.getById(id)
        if (apiModel != null) return apiModel
        val loadedEntity =
            baseDictionaryDao.selectWhereId(id) ?: throw DictionaryApiModelNotFoundException()
        return baseDictionaryMapper.entityToApiModel(loadedEntity)
    }

    suspend fun findAllWithSearch(search: String): Iterable<ApiModel> {
        return baseDictionaryDao.selectAllWhereNameContainsSearch(search)
            .map { baseDictionaryMapper.entityToApiModel(it) }
    }

    suspend fun add(apiModel: ApiModel) {
        baseDictionaryCache.add(apiModel)
    }

    suspend fun clearCache() {
        baseDictionaryCache.clear()
    }
}