package com.frestoinc.sample.featuredelivery.core.data.di

import com.frestoinc.sample.featuredelivery.core.data.preference.PreferenceManager
import com.frestoinc.sample.featuredelivery.core.data.preference.PreferenceManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferenceModule {

    @Binds
    @PreferenceDataManager
    abstract fun bindsPreferenceDatastore(
        preferenceManagerImpl: PreferenceManagerImpl
    ): PreferenceManager

}