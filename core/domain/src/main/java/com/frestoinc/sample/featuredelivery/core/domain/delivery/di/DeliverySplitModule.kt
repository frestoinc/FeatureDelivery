package com.frestoinc.sample.featuredelivery.core.domain.delivery.di

import android.content.Context
import com.frestoinc.sample.featuredelivery.core.domain.BuildConfig
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.testing.FakeSplitInstallManagerFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DeliverySplitModule {

    @Provides
    @Singleton
    fun provideSplitManager(
        @ApplicationContext appContext: Context
    ): SplitInstallManager =
        if (BuildConfig.DEBUG) {
            FakeSplitInstallManagerFactory.create(
                appContext,
                appContext.getExternalFilesDir("local_testing")
            )
        } else {
            SplitInstallManagerFactory.create(appContext)
        }

}