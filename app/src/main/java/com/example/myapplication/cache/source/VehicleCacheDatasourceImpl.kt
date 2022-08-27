package com.example.myapplication.cache.source

import com.example.myapplication.cache.db.VehicleDao
import com.example.myapplication.cache.mapper.VehicleEntityMapper
import com.example.myapplication.data.datasource.cache.VehicleCacheDataSource
import com.example.myapplication.data.entity.VehicleEntity
import com.example.myapplication.data.entity.VehicleListEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class VehicleCacheDatasourceImpl @Inject constructor(
    private val vehicleDao: VehicleDao,
    private val vehicleEntityMapper: VehicleEntityMapper
) : VehicleCacheDataSource {

    override fun getAllVehicles(): Flow<List<VehicleEntity>> {
        return vehicleDao.getAllVehicles()
            .map { cachedList ->
                cachedList.map { cachedVehicle ->
                    vehicleEntityMapper.mapFromCached(
                        cachedVehicle
                    )
                }
            }
    }

    override suspend fun upsertVehicles(vehicleList: VehicleListEntity) {

        vehicleDao.upsertVehicles(vehicleList.vehicles.map { vehicleEntityMapper.mapToCached(it) })
    }
}