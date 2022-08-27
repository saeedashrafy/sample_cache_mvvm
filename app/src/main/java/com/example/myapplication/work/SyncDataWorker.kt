package com.example.myapplication.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.*
import com.example.myapplication.core.CoroutineDispatcherProvider
import com.example.myapplication.domain.repository.VehicleRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import java.util.concurrent.TimeUnit

@HiltWorker
class SyncDataWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted params: WorkerParameters,
    private val dispatcherProvider: CoroutineDispatcherProvider,
    private val vehicleRepository: VehicleRepository,
) : CoroutineWorker(appContext, params) {


    override suspend fun doWork(): Result = withContext(dispatcherProvider.io) {
        try {
            vehicleRepository.sync()
            Timber.d("WorkManager: Work request for sync is finished")
        } catch (e: HttpException) {
            Result.retry()
        }

        Result.success()
    }

    companion object {

        private fun getConstraints() = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        fun startUpSyncWork() = PeriodicWorkRequestBuilder<DelegatingWorker>(15, TimeUnit.MINUTES)
            .setConstraints(getConstraints())
            .setInputData(SyncDataWorker::class.delegatedData())
            .build()
    }

}

