package com.example.myapplication.data.entity

import java.util.*

data class VehicleEntity(
    val type: String? = null,
    val lat: Double? = null,
    val lng: Double? = null,
    val bearing: Int? = null,
    val imageUrl: String? = null,
    val modifiedAt: Date? = null
)
