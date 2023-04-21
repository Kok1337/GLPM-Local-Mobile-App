package com.kok1337.address.data.sync.datasource.database.dao

import com.kok1337.address.data.sync.datasource.database.entity.InfoLocalityGlpmEntity
import com.kok1337.address.data.sync.datasource.database.table.InfoLocalityGlpmTable
import com.kok1337.sync.data.datasource.database.dao.BaseSyncDaoKtormUuid
import org.ktorm.database.Database
import org.ktorm.dsl.AssignmentsBuilder

class InfoLocalityGlpmDaoKtormUuid(
    database: Database
) : BaseSyncDaoKtormUuid<InfoLocalityGlpmEntity>(database, InfoLocalityGlpmTable) {
    override fun buildAssignments(builder: AssignmentsBuilder, entity: InfoLocalityGlpmEntity) {
        InfoLocalityGlpmTable.let {
            builder.apply {
                set(it.federalDistrictId, entity.federalDistrictId)
                set(it.regionId, entity.regionId)
                set(it.forestryId, entity.forestryId)
                set(it.localForestryId, entity.localForestryId)
                set(it.subForestryId, entity.subForestryId)
                set(it.area, entity.area)
            }
        }
    }
}