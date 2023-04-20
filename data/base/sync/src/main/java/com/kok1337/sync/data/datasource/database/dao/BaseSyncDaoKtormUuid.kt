package com.kok1337.sync.data.datasource.database.dao

import com.kok1337.sync.data.datasource.database.entity.BaseSyncEntityKtormUuid
import com.kok1337.sync.data.datasource.database.table.BaseSyncTableKtormUuid
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.ktorm.database.Database
import java.util.*

abstract class BaseSyncDaoKtormUuid<Entity : BaseSyncEntityKtormUuid<Entity>>(
    database: Database,
    table: BaseSyncTableKtormUuid<Entity>,
    daoDispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseSyncDaoKtorm<UUID, Entity>(database, table, daoDispatcher)