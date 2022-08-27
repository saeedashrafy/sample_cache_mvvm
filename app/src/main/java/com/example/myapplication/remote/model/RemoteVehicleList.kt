package com.example.myapplication.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteVehicleList(
    @SerializedName("vehicles")
    val vehicles: List<RemoteVehicle>
)

