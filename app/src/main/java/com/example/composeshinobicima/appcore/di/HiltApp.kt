package com.example.composeshinobicima.appcore.di

import android.app.Application
import androidx.work.Configuration
import com.example.composeshinobicima.TrendingWorkerFactory
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


@HiltAndroidApp
class HiltApp : Application(),Configuration.Provider{

    @Inject
    lateinit var workerFactory: TrendingWorkerFactory


    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
}

