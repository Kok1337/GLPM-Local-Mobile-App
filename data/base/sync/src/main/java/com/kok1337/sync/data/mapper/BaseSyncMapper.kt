package com.kok1337.sync.data.mapper

import com.kok1337.sync.data.datasource.database.entity.BaseSyncEntity
import com.kok1337.sync.data.model.BaseSyncApiModel

interface BaseSyncMapper<ApiModel : BaseSyncApiModel<*>, Entity : BaseSyncEntity<*>> {
    fun apiModelToEntity(apiModel: ApiModel): Entity
    fun entityToApiModel(entity: Entity): ApiModel
    fun mergeApiModelIntoEntity(apiModel: ApiModel, entity: Entity): Entity
}