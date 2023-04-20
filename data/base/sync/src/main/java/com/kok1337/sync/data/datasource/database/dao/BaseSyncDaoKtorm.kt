package com.kok1337.sync.data.datasource.database.dao

import com.kok1337.sync.data.datasource.database.entity.BaseSyncEntityKtorm
import com.kok1337.sync.data.datasource.database.table.BaseSyncTableKtorm
import com.kok1337.sync.data.exception.DeleteSyncEntityException
import kotlinx.coroutines.*
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.filter
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.singleOrNull
import java.sql.Timestamp

abstract class BaseSyncDaoKtorm<Id : Any, Entity : BaseSyncEntityKtorm<Id, Entity>>(
    private val database: Database,
    private val table: BaseSyncTableKtorm<Id, Entity>,
    private val daoDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : BaseSyncDao<Id, Entity> {
    override suspend fun selectWhereId(id: Id): Entity? = withContext(daoDispatcher) {
        return@withContext database.sequenceOf(table)
            .filter { it.isDeleted eq false }
            .filter { it.id eq id }
            .singleOrNull()
    }

    override suspend fun insert(userId: Int?, entity: Entity): Id = withContext(daoDispatcher) {
        val id = entity.id
        database.insert(table) {
            set(it.id, id)

            buildAssignments(this, entity)

            set(it.userId, userId)
            set(it.modificationDate, Timestamp(System.currentTimeMillis()))
            set(it.registrationDate, null)
            set(it.isDeleted, false)
        }
        return@withContext id
    }

    protected abstract fun buildAssignments(builder: AssignmentsBuilder, entity: Entity)

    override suspend fun insertAll(userId: Int?, entities: Iterable<Entity>): List<Id> =
        withContext(daoDispatcher) {
            val inserts = entities.map { entity -> async { insert(userId, entity) } }
            return@withContext inserts.map { it.await() }
        }

    override suspend fun update(userId: Int?, entity: Entity): Unit = withContext(daoDispatcher) {
        database.update(table) {
            where { it.id eq entity.id }

            buildAssignments(this, entity)

            set(it.userId, userId)
            set(it.modificationDate, Timestamp(System.currentTimeMillis()))
        }
    }

    override suspend fun updateAll(userId: Int?, entities: Iterable<Entity>) =
        withContext(daoDispatcher) {
            entities.forEach { entity -> launch { update(userId, entity) } }
            return@withContext
        }

    override suspend fun deleteById(userId: Int?, id: Id): Unit = withContext(daoDispatcher) {
        val item = selectWhereId(id) ?: throw DeleteSyncEntityException()
        val isRegistered = item.registrationDate != null
        if (isRegistered) database.update(table) {
            where { it.id eq id }
            set(it.modificationDate, Timestamp(System.currentTimeMillis()))
            set(it.isDeleted, true)
        } else database.delete(table) { it.id eq id }
    }

    override suspend fun deleteAllByIds(userId: Int?, ids: Iterable<Id>) =
        withContext(daoDispatcher) {
            ids.forEach { id -> launch { deleteById(userId, id) } }
        }
}