package com.example.myapplication

import android.app.Application
import com.example.myapplication.work.Sync
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Sync.initialize(context = this)
    }
}

