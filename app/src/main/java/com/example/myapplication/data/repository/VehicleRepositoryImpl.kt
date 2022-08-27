package com.example.myapplication.data.repository

import com.example.myapplication.data.datasource.cache.VehicleCacheDataSource
import com.example.myapplication.data.datasource.remote.VehicleRemoteDataSource
import com.example.myapplication.data.mapper.VehicleMapper
import com.example.myapplication.domain.model.VehicleList
import com.example.myapplication.domain.repository.VehicleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class VehicleRepositoryImpl @Inject constructor(
    private val vehicleCacheDataSource: VehicleCacheDataSource,
    private val vehicleRemoteDataSource: VehicleRemoteDataSource,
    private val vehicleMapper: VehicleMapper
) :
    VehicleRepository {

    override fun getVehiclesStream(): Flow<VehicleList> {
        return vehicleCacheDataSource.getAllVehicles().map { vehicleList ->
            VehicleList(vehicles = vehicleList.map { vehicleEntity ->
                vehicleMapper.mapFromEntity(
                    vehicleEntity
                )
            })
        }
    }

    override suspend fun sync() {
        val vehicleList = vehicleRemoteDataSource.getVehicles()
        vehicleCacheDataSource.upsertVehicles(vehicleList)
    }
}