package com.example.myapplication.domain.model

import java.util.*

data class Vehicle(
    val type: String,
    val lat: Double,
    val lng: Double ,
    val bearing: Int ,
    val imageUrl: String,
    val modifiedAt:Date
)
