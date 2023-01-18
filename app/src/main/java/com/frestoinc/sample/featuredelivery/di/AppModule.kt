package com.frestoinc.sample.featuredelivery.di

import android.content.Context
import com.frestoinc.sample.featuredelivery.core.domain.provider.ActivityProvider
import com.frestoinc.sample.featuredelivery.provider.ActivityProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMainActivityIntent(
        @ApplicationContext appContext: Context
    ): ActivityProvider =
        ActivityProviderImpl(appContext)
}