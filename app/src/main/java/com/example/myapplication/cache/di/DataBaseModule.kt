package com.example.myapplication.cache.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.cache.db.AppDatabase
import com.example.myapplication.cache.db.VehicleDao
import com.example.myapplication.cache.mapper.VehicleEntityMapper
import com.example.myapplication.cache.source.VehicleCacheDatasourceImpl
import com.example.myapplication.data.datasource.cache.VehicleCacheDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .build()

    @Provides
    @Singleton
    fun provideVehicleDao(database: AppDatabase): VehicleDao =
        database.vehicleDao()


    @Provides
    fun provideVehicleEntityMapper(): VehicleEntityMapper = VehicleEntityMapper()

    @Provides
    @Singleton
    fun provideVehicleCacheDatasource(
        veiVehicleDao: VehicleDao,
        vehicleEntityMapper: VehicleEntityMapper
    ): VehicleCacheDataSource = VehicleCacheDatasourceImpl(veiVehicleDao, vehicleEntityMapper)
}