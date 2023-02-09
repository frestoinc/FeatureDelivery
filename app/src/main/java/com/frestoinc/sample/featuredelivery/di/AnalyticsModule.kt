package com.frestoinc.sample.featuredelivery.di

import com.frestoinc.sample.featuredelivery.analytics.EventAnalytics
import com.frestoinc.sample.featuredelivery.analytics.EventAnalyticsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AnalyticsModule {

    @Binds
    abstract fun bindsEventAnalytics(
        eventAnalyticsImpl: EventAnalyticsImpl
    ): EventAnalytics

}