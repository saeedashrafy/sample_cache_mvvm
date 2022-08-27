package com.example.myapplication.data.mapper

import com.example.myapplication.data.entity.VehicleEntity
import com.example.myapplication.domain.model.Vehicle
import java.util.*

class VehicleMapper : Mapper<VehicleEntity, Vehicle> {
    override fun mapFromEntity(entity: VehicleEntity): Vehicle {
        return Vehicle(
            type = entity.type ?: "",
            bearing = entity.bearing ?: 0,
            lng = entity.lng ?: 0.0,
            lat = entity.lat ?: 0.0,
            imageUrl = entity.imageUrl ?: "",
            modifiedAt = entity.modifiedAt ?: Date()
        )
    }

    override fun mapToEntity(domainModel: Vehicle): VehicleEntity {
        return VehicleEntity()
    }
}