package com.example.myapplication.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "vehicle")
data class CachedVehicle(
    @PrimaryKey
    val bearing: Int? = null,
    val type: String? = null,
    val lat: Double? = null,
    val lng: Double? = null,
    @ColumnInfo(name = "image_url")
    val imageUrl: String? = null,
    @ColumnInfo(name = "modified_at")
    val modifiedAt: Date? =null
)
