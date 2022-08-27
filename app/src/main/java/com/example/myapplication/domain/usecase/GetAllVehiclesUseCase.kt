package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.repository.VehicleRepository
import javax.inject.Inject

class GetAllVehiclesUseCase @Inject constructor(private val vehicleRepository: VehicleRepository) {
    operator fun invoke() = vehicleRepository.getVehiclesStream()
}