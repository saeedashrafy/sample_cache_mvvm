package com.example.myapplication.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteVehicle(
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("lat")
    val lat: Double? = null,
    @SerializedName("lng")
    val lng: Double? = null,
    @SerializedName("bearing")
    val bearing: Int? = null,
    @SerializedName("image_url")
    val imageUrl: String? = null
)
