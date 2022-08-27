package com.example.myapplication.remote.mapper

import com.example.myapplication.data.entity.VehicleEntity
import com.example.myapplication.data.entity.VehicleListEntity
import com.example.myapplication.remote.model.RemoteVehicleList

class VehicleListEntityMapper : EntityMapper<RemoteVehicleList, VehicleListEntity> {

    override fun mapFromRemote(type: RemoteVehicleList): VehicleListEntity {
        val itemsEntity = type.vehicles.map { remoteItem ->
            VehicleEntity(
                type = remoteItem.type,
                lat = remoteItem.lat,
                lng = remoteItem.lng,
                bearing = remoteItem.bearing,
                imageUrl = remoteItem.imageUrl
            )
        }
        return VehicleListEntity(vehicles = itemsEntity)
    }
}