package com.example.myapplication.data.di

import com.example.myapplication.data.datasource.cache.VehicleCacheDataSource
import com.example.myapplication.data.datasource.remote.VehicleRemoteDataSource
import com.example.myapplication.data.mapper.VehicleMapper
import com.example.myapplication.data.repository.VehicleRepositoryImpl
import com.example.myapplication.domain.repository.VehicleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideVehicleMapper(): VehicleMapper = VehicleMapper()

    @Singleton
    @Provides
    fun providesVehicleRepository(
        vehicleCacheDataSource: VehicleCacheDataSource,
        vehicleRemoteDataSource: VehicleRemoteDataSource,
        vehicleMapper: VehicleMapper
    ): VehicleRepository =
        VehicleRepositoryImpl(vehicleCacheDataSource, vehicleRemoteDataSource, vehicleMapper)
}