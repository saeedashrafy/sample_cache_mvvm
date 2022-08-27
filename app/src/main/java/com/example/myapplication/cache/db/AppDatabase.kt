package com.example.myapplication.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.cache.model.CachedVehicle

@Database(entities = [CachedVehicle::class], version = 1)
@TypeConverters(Convertors::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun vehicleDao(): VehicleDao

    companion object {
        const val DATABASE_NAME = "clean_mvvm"
    }
}