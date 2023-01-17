package com.frestoinc.sample.featuredelivery.core.domain.delivery

import android.app.*
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.frestoinc.sample.featuredelivery.core.domain.R
import com.frestoinc.sample.featuredelivery.core.domain.delivery.di.FeatureDeliveryNotification
import com.frestoinc.sample.featuredelivery.core.domain.delivery.di.FeatureDeliveryNotificationChannel
import com.frestoinc.sample.featuredelivery.core.domain.delivery.di.FeatureDeliveryNotificationChannelIdInt
import com.frestoinc.sample.featuredelivery.core.domain.provider.ActivityProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FeatureDeliveryService : Service() {

    @Inject
    internal lateinit var featureDeliveryManager: FeatureDeliveryManager

    @Inject
    internal lateinit var activityProvider: ActivityProvider

    @Inject
    internal lateinit var notificationManager: NotificationManager

    @Inject
    @FeatureDeliveryNotification
    internal lateinit var notificationCompat: NotificationCompat.Builder

    @Inject
    @FeatureDeliveryNotificationChannel
    internal lateinit var notificationChannel: NotificationChannel

    @Inject
    @FeatureDeliveryNotificationChannelIdInt
    internal var channelId: Int = 0

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun updateProgress(progressValue: Int) {
        notificationManager.notify(
            channelId,
            createNotification(progressValue)
        )
    }

    private fun createNotification(progressValue: Int): Notification {
        notificationManager.createNotificationChannel(notificationChannel)

        val pendingIntent =
            PendingIntent.getActivity(
                this@FeatureDeliveryService,
                channelId,
                activityProvider.provideActivityIntent(),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

        return with(notificationCompat) {
            setContentIntent(pendingIntent)
            when (progressValue) {
                in 1..99 -> {
                    setContentText(
                        getString(R.string.notification_downloading)
                    )
                    setProgress(100, progressValue, false)
                }
                0 -> {
                    setContentText(
                        getString(R.string.notification_download)
                    )
                    setProgress(100, progressValue, true)
                }
                else -> {
                    setContentText(
                        getString(R.string.notification_downloaded)
                    )
                }
            }
        }.build()
    }
}