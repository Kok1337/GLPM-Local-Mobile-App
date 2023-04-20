package com.kok1337.sync.data.datasource.database.entity

import java.sql.Timestamp

interface BaseSyncEntity<Id> {
    var id: Id

    val userId: Int?
    val registrationDate: Timestamp?
    val modificationDate: Timestamp?
    val isDeleted: Boolean
}