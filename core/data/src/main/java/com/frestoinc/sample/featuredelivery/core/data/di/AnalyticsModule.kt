package com.frestoinc.sample.featuredelivery.core.data.di

import com.frestoinc.sample.featuredelivery.core.data.analytics.EventAnalytics
import com.frestoinc.sample.featuredelivery.core.data.analytics.EventAnalyticsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AnalyticsModule {

    @Binds
    @PreferenceDataManager
    abstract fun bindsEventAnalytics(
        eventAnalyticsImpl: EventAnalyticsImpl
    ): EventAnalytics

}