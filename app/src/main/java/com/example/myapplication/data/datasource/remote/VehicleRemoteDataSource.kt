package com.example.myapplication.data.datasource.remote

import com.example.myapplication.data.entity.VehicleListEntity

interface VehicleRemoteDataSource {

    suspend fun getVehicles(): VehicleListEntity
}