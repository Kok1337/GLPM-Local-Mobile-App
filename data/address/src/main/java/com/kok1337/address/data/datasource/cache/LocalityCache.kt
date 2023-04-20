package com.kok1337.address.data.datasource.cache

import com.kok1337.address.data.model.LocalityApiModel
import com.kok1337.sync.data.datasource.cache.BaseSyncCache
import java.util.*

class LocalityCache : BaseSyncCache<UUID, LocalityApiModel>()