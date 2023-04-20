package com.kok1337.address.data.model

import com.kok1337.sync.data.model.BaseSyncApiModel
import java.util.*

data class LocalityApiModel(
    override val id: UUID,
    val federalDistrictId: Int,
    val regionId: Int,
    val forestryId: Int,
    val localForestryId: Int,
    val subForestryId: Int?,
    val area: String,
) : BaseSyncApiModel<UUID>