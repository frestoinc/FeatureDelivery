package com.frestoinc.sample.featuredelivery.core.domain.network.di

import com.frestoinc.sample.featuredelivery.core.domain.network.ConnectivityNetworkMonitor
import com.frestoinc.sample.featuredelivery.core.domain.network.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    abstract fun bindsNetworkMonitor(
        networkMonitor: ConnectivityNetworkMonitor
    ): NetworkMonitor
}