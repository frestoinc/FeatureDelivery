package com.frestoinc.sample.featuredelivery.core.domain.network.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.getSystemService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConnectivityModule {

    @Provides
    @Singleton
    fun provideConnectivityManager(
        @ApplicationContext appContext: Context
    ): ConnectivityManager? =
        appContext.getSystemService() as ConnectivityManager?
}