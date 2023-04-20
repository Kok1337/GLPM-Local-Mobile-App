package com.kok1337.sync.data.datasource.database.entity

import org.ktorm.entity.Entity

interface BaseSyncEntityKtorm<Id, T> : Entity<T>,
    BaseSyncEntity<Id> where T : Entity<T>, T : BaseSyncEntity<Id>