package com.example.myapplication.data.datasource.cache

import com.example.myapplication.data.entity.VehicleEntity
import com.example.myapplication.data.entity.VehicleListEntity
import kotlinx.coroutines.flow.Flow

interface VehicleCacheDataSource {

    fun getAllVehicles(): Flow<List<VehicleEntity>>

    suspend fun upsertVehicles(vehicleList: VehicleListEntity)
}