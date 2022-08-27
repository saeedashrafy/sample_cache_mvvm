package com.example.myapplication.remote.service

import com.example.myapplication.remote.model.RemoteVehicleList
import retrofit2.http.GET

interface VehicleApiService {

    @GET("vehicle")
    suspend fun getVehicles(): RemoteVehicleList
}