package com.kok1337.dictionary.data.datasource.database.dao

import com.kok1337.dictionary.data.datasource.database.entity.BaseDictionaryEntityKtorm
import com.kok1337.dictionary.data.datasource.database.table.BaseDictionaryTableKtorm
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.dsl.inList
import org.ktorm.dsl.like
import org.ktorm.entity.*

abstract class BaseDictionaryDaoKtorm<Entity : BaseDictionaryEntityKtorm<Entity>>(
    private val database: Database,
    private val table: BaseDictionaryTableKtorm<Entity>,
    private val daoDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : BaseDictionaryDao<Entity> {
    override suspend fun selectWhereId(id: Int): Entity? = withContext(daoDispatcher) {
        return@withContext database.sequenceOf(table).singleOrNull { it.id eq id }
    }

    override suspend fun selectAllWhereNameContainsSearch(search: String): List<Entity> =
        withContext(daoDispatcher) {
            val namePattern = "%${search}%"
            return@withContext database.sequenceOf(table).filter { it.name like namePattern }
                .sortedBy { it.name }.toList()
        }

    override suspend fun selectAllWhereIdInIdsAndNameContainsSearch(
        ids: Iterable<Int>, search: String
    ): List<Entity> = withContext(daoDispatcher) {
        val idsList = ids.toList()
        val namePattern = "%${search}%"
        return@withContext database.sequenceOf(table).filter { it.id inList idsList }
            .filter { it.name like namePattern }.sortedBy { it.name }.toList()
    }
}