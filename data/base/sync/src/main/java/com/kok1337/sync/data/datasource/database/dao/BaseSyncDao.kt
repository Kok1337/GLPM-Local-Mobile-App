package com.kok1337.sync.data.datasource.database.dao

import com.kok1337.sync.data.datasource.database.entity.BaseSyncEntity

interface BaseSyncDao<Id, Entity : BaseSyncEntity<Id>> {
    suspend fun selectWhereId(id: Id): Entity?
    suspend fun insert(userId: Int?, entity: Entity): Id
    suspend fun insertAll(userId: Int?, entities: Iterable<Entity>): List<Id>
    suspend fun update(userId: Int?, entity: Entity)
    suspend fun updateAll(userId: Int?, entities: Iterable<Entity>)
    suspend fun deleteById(userId: Int?, id: Id)
    suspend fun deleteAllByIds(userId: Int?, ids: Iterable<Id>)
}