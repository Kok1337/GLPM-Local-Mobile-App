package com.kok1337.dictionary.data.datasource.database.dao

import com.kok1337.dictionary.data.datasource.database.entity.BaseDictionaryEntity

interface BaseDictionaryDao<Entity : BaseDictionaryEntity> {
    suspend fun selectWhereId(id: Int): Entity?
    suspend fun selectAllWhereNameContainsSearch(search: String): List<Entity>
    suspend fun selectAllWhereIdInIdsAndNameContainsSearch(ids: Iterable<Int>, search: String): List<Entity>
}