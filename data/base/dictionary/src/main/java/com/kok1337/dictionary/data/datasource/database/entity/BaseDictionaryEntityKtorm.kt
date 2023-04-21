package com.kok1337.dictionary.data.datasource.database.entity

import org.ktorm.entity.Entity

interface BaseDictionaryEntityKtorm<T> : Entity<T>,
    BaseDictionaryEntity where T : Entity<T>, T : BaseDictionaryEntity