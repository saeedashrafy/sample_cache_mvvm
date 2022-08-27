package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.VehicleList
import kotlinx.coroutines.flow.Flow

interface VehicleRepository {

    fun getVehiclesStream(): Flow<VehicleList>

    suspend fun sync()
}