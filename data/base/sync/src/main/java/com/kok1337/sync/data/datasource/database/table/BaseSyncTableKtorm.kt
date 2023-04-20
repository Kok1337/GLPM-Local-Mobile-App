package com.kok1337.sync.data.datasource.database.table

import com.kok1337.sync.data.datasource.database.entity.BaseSyncEntityKtorm
import org.ktorm.schema.*

abstract class BaseSyncTableKtorm<Id : Any, Entity : BaseSyncEntityKtorm<Id, Entity>>(
    schema: String,
    tableName: String
) : Table<Entity>(tableName = tableName, schema = schema) {
    abstract val id: Column<Id>

    val userId = int("user_id").bindTo { it.userId }
    val registrationDate = jdbcTimestamp("registration_date").bindTo { it.registrationDate }
    val modificationDate = jdbcTimestamp("modification_date").bindTo { it.modificationDate }
    val isDeleted = boolean("is_deleted").bindTo { it.isDeleted }
}