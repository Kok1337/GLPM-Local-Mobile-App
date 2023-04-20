package com.kok1337.sync.data.datasource.database.table

import com.kok1337.sync.data.datasource.database.entity.BaseSyncEntityKtormUuid
import org.ktorm.schema.uuid
import java.util.*

abstract class BaseSyncTableKtormUuid<Entity : BaseSyncEntityKtormUuid<Entity>>(
    schema: String,
    tableName: String
) : BaseSyncTableKtorm<UUID, Entity>(tableName = tableName, schema = schema) {
    override val id = uuid("id").bindTo { it.id }
}