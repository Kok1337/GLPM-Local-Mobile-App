package com.kok1337.dictionary.data.datasource.database.table

import com.kok1337.dictionary.data.datasource.database.entity.BaseDictionaryEntityKtorm
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.text

abstract class BaseDictionaryTableKtorm<Entity : BaseDictionaryEntityKtorm<Entity>>(
    schema: String,
    tableName: String
) : Table<Entity>(tableName = tableName, schema = schema) {
    val id = int("id").bindTo { it.id }
    val name = text("name").bindTo { it.name }
}