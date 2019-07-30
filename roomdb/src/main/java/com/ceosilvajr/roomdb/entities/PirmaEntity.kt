package com.ceosilvajr.roomdb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * @author ceosilvajr@gmail.com
 */
@Entity(tableName = "Pirma")
data class PirmaEntity(
        @PrimaryKey
        @ColumnInfo(name = "pirmaId")
        var id: String = UUID.randomUUID().toString(),
        var base64EncodedImage: String = "",
        var date: Date = Date()
)