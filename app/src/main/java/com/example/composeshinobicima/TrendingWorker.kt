package com.example.composeshinobicima

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import coil.ImageLoader
import coil.request.ImageRequest
import com.example.composeshinobicima.appcore.domain.model.MediaItem
import com.example.composeshinobicima.appcore.domain.usecase.GetTrendingAllUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject


class TrendingWorker @AssistedInject constructor  (
     @Assisted context: Context,
     @Assisted workerParams: WorkerParameters,
    private val trendingAllUseCase: GetTrendingAllUseCase
) : CoroutineWorker(context, workerParams) {


    override suspend fun doWork(): Result {


        // Step 1: Call TMDB trending API
        val movies = trendingAllUseCase(1)



        // Step 2: If movies exist, send a notification
      if (!movies.data().isNullOrEmpty()) {
           showNotification(movies.data()!!.random())
       }

        return Result.success()
    }

    private suspend fun showNotification(mediaItem: MediaItem) {
        val channelId = "trending_channel"
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            putExtra("media_id", mediaItem.id) // optional extra to know it came from notification
            putExtra("media_type", mediaItem.media_type.value)

            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Trending Movies",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        // Load image from URL into bitmap
        val posterUrl = "https://image.tmdb.org/t/p/w500${ mediaItem.resolvedPoster?: ""}"  // whatever your model calls it
        val backdropUrl = "https://image.tmdb.org/t/p/w500${ mediaItem.backdrop_path?: ""}"  // whatever your model calls it

        val bitmapPoster = loadBitmap(posterUrl)
        val bitmapBackdrop = loadBitmap(backdropUrl)


        val builder = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.shinobihub)
            .setContentTitle("Trending Now")
            .setContentText("Check out: ${mediaItem.resolvedTitle}")
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        if (bitmapPoster != null) {
                builder.setLargeIcon(bitmapPoster)
        }

        if (bitmapBackdrop != null) {
            builder.setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(bitmapBackdrop)
                    .bigLargeIcon(null as Bitmap?)
            )
        }



        notificationManager.notify(1, builder.build())
    }

    // Load image into Bitmap using Coil
    private suspend fun loadBitmap(url: String): Bitmap? {
        return try {
            val loader = ImageLoader(applicationContext)
            val request = ImageRequest.Builder(applicationContext)
                .data(url)
                .allowHardware(false) // hardware bitmaps can't be put in notifications
                .build()
            val result = loader.execute(request)
            (result.drawable as BitmapDrawable).bitmap
        } catch (e: Exception) {
            null
        }
    }

}