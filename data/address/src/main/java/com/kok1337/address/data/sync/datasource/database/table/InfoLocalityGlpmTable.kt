package com.kok1337.address.data.sync.datasource.database.table

import com.kok1337.address.data.sync.datasource.database.entity.InfoLocalityGlpmEntity
import com.kok1337.sync.data.datasource.database.table.BaseSyncTableKtormUuid
import org.ktorm.schema.int
import org.ktorm.schema.text

object InfoLocalityGlpmTable :
    BaseSyncTableKtormUuid<InfoLocalityGlpmEntity>("glpm", "info_locality_glpm") {
    val federalDistrictId = int("fo_id").bindTo { it.federalDistrictId }
    val regionId = int("region_id").bindTo { it.regionId }
    val forestryId = int("forestry_id").bindTo { it.forestryId }
    val localForestryId = int("localforestry_id").bindTo { it.localForestryId }
    val subForestryId = int("subforestry_id").bindTo { it.subForestryId }
    val area = text("area").bindTo { it.area }
}