package com.example.myapplication.cache.db

import androidx.room.*
import com.example.myapplication.cache.model.CachedVehicle
import kotlinx.coroutines.flow.Flow

@Dao
interface VehicleDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertVehicles(entities: List<CachedVehicle>)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateVehicle(entity: CachedVehicle): Long

    @Query("SELECT * FROM vehicle")
    fun getAllVehicles(): Flow<List<CachedVehicle>>

    @Delete
    suspend fun delete(vehiclesEntity: CachedVehicle)

}