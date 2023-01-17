package com.frestoinc.sample.featuredelivery.core.domain.di

import android.app.NotificationManager
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SystemServiceModule {

    @Provides
    @Singleton
    fun provideNotificationManager(
        @ApplicationContext appContext: Context
    ): NotificationManager =
        appContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

}