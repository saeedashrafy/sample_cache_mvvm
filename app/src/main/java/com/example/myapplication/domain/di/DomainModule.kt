package com.example.myapplication.domain.di

import com.example.myapplication.domain.repository.VehicleRepository
import com.example.myapplication.domain.usecase.GetAllVehiclesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun providesGetAllVehicleUseCase(vehicleRepository: VehicleRepository): GetAllVehiclesUseCase =
        GetAllVehiclesUseCase(vehicleRepository)
}