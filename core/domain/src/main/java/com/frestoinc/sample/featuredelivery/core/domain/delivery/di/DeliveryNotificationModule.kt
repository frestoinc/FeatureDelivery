package com.frestoinc.sample.featuredelivery.core.domain.delivery.di

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.frestoinc.sample.featuredelivery.core.domain.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ServiceComponent::class)
object DeliveryNotificationModule {

    private const val NOTIFICATION_CHANNEL_ID_STRING = "Feature Delivery"
    private const val NOTIFICATION_CHANNEL_ID = "46"

    @Provides
    @FeatureDeliveryNotification
    fun provideNotificationBuilder(
        @ApplicationContext appContext: Context,
        @FeatureDeliveryNotificationChannelIdString channelId: String
    ): NotificationCompat.Builder =
        NotificationCompat.Builder(appContext, channelId)
            .setOngoing(true)
            .setAutoCancel(true)
            .setOnlyAlertOnce(true)
            .setVibrate(longArrayOf(2000))
            .setCategory(NotificationCompat.CATEGORY_SERVICE)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(
                appContext.getString(R.string.app_name)
            )

    @Provides
    @FeatureDeliveryNotificationChannelIdString
    fun provideNotificationChannelIdString(): String =
        NOTIFICATION_CHANNEL_ID_STRING

    @Provides
    @FeatureDeliveryNotificationChannelIdInt
    fun provideNotificationChannelIdInt(): String =
        NOTIFICATION_CHANNEL_ID

    @Provides
    @FeatureDeliveryNotificationChannel
    fun provideNotificationChannel(
        @FeatureDeliveryNotificationChannelIdString channelId: String,
    ): NotificationChannel =
        NotificationChannel(
            channelId,
            channelId,
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        }
}