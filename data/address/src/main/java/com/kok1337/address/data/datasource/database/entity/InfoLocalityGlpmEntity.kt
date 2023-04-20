package com.kok1337.address.data.datasource.database.entity

import com.kok1337.sync.data.datasource.database.entity.BaseSyncEntityKtormUuid
import org.ktorm.entity.Entity

interface InfoLocalityGlpmEntity : BaseSyncEntityKtormUuid<InfoLocalityGlpmEntity> {
    companion object : Entity.Factory<InfoLocalityGlpmEntity>()

    var federalDistrictId: Int
    var regionId: Int
    var forestryId: Int
    var localForestryId: Int
    var subForestryId: Int?
    var area: String
}