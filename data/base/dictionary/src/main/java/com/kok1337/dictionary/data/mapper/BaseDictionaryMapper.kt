package com.kok1337.dictionary.data.mapper

import com.kok1337.dictionary.data.datasource.database.entity.BaseDictionaryEntity
import com.kok1337.dictionary.data.model.BaseDictionaryApiModel

interface BaseDictionaryMapper<ApiModel : BaseDictionaryApiModel, Entity : BaseDictionaryEntity> {
    fun entityToApiModel(entity: Entity): ApiModel
}