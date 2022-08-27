package com.example.myapplication.remote.source

import com.example.myapplication.data.datasource.remote.VehicleRemoteDataSource
import com.example.myapplication.data.entity.VehicleListEntity
import com.example.myapplication.remote.mapper.VehicleListEntityMapper
import com.example.myapplication.remote.service.VehicleApiService
import javax.inject.Inject


class VehicleRemoteDataSourceImpl @Inject constructor(
    private val vehicleApiService: VehicleApiService,
    private val vehicleListEntityMapper: VehicleListEntityMapper
) : VehicleRemoteDataSource {

    override suspend fun getVehicles(): VehicleListEntity {
        return vehicleListEntityMapper.mapFromRemote(vehicleApiService.getVehicles())
    }
}