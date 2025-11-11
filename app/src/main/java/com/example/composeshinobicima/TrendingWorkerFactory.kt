package com.example.composeshinobicima

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.composeshinobicima.appcore.domain.usecase.GetTrendingAllUseCase
import javax.inject.Inject

class TrendingWorkerFactory @Inject constructor(
    private val trendingAllUseCase: GetTrendingAllUseCase
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {

        return when (workerClassName) {
            TrendingWorker::class.java.name ->
                TrendingWorker(appContext, workerParameters, trendingAllUseCase)
            else -> null
        }
    }
}