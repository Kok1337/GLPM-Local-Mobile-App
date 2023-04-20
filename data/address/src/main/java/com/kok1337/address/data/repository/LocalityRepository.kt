package com.kok1337.address.data.repository

import com.kok1337.address.data.datasource.database.entity.InfoLocalityGlpmEntity
import com.kok1337.address.data.model.LocalityApiModel
import com.kok1337.sync.data.repository.BaseSyncDataRepository
import java.util.*

class LocalityRepository : BaseSyncDataRepository<UUID, LocalityApiModel, InfoLocalityGlpmEntity>(

) {
    override suspend fun getUserId(): Int {
        TODO("Not yet implemented")
    }

    override suspend fun findAllEntities(): Iterable<InfoLocalityGlpmEntity> {
        TODO("Not yet implemented")
    }
}