package com.example.myapplication.cache.mapper

import com.example.myapplication.cache.model.CachedVehicle
import com.example.myapplication.data.entity.VehicleEntity
import java.util.*

class VehicleEntityMapper : EntityMapper<CachedVehicle, VehicleEntity> {
    override fun mapFromCached(model: CachedVehicle): VehicleEntity {
        return VehicleEntity(
            type = model.type,
            imageUrl = model.imageUrl,
            lat = model.lat,
            lng = model.lng,
            bearing = model.bearing,
            modifiedAt = model.modifiedAt
        )
    }

    override fun mapToCached(model: VehicleEntity): CachedVehicle {
        return CachedVehicle(
            type = model.type,
            imageUrl = model.imageUrl,
            lat = model.lat,
            lng = model.lng,
            bearing = model.bearing,
            modifiedAt = Date()
        )
    }
}