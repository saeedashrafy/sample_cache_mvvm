package com.example.myapplication.remote.di

import com.example.myapplication.data.datasource.remote.VehicleRemoteDataSource
import com.example.myapplication.remote.mapper.VehicleListEntityMapper
import com.example.myapplication.remote.service.VehicleApiService
import com.example.myapplication.remote.source.VehicleRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .client(getOkHttpClient())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    private fun getOkHttpClient() =
        OkHttpClient.Builder()
            .connectTimeout(NETWORK_REQUEST_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(NETWORK_REQUEST_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_REQUEST_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()


    @Singleton
    @Provides
    fun provideVehicleApisService(retrofit: Retrofit): VehicleApiService =
        retrofit.create(VehicleApiService::class.java)

    @Provides
    fun provideVehicleEntityMapper(): VehicleListEntityMapper = VehicleListEntityMapper()

    @Provides
    fun provideVehicleRemoteDataSource(
        vehicleApiService: VehicleApiService,
        vehicleListEntityMapper: VehicleListEntityMapper
    ): VehicleRemoteDataSource =
        VehicleRemoteDataSourceImpl(vehicleApiService, vehicleListEntityMapper)

    companion object {
        const val NETWORK_REQUEST_TIMEOUT_SECONDS = 15L
        const val BASE_URL = "https://BASE_URL/api/"
    }
}