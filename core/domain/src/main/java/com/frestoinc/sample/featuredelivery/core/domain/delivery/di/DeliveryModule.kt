package com.frestoinc.sample.featuredelivery.core.domain.delivery.di

import com.frestoinc.sample.featuredelivery.core.domain.delivery.installer.FeatureDeliveryInstaller
import com.frestoinc.sample.featuredelivery.core.domain.delivery.installer.FeatureDeliveryInstallerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DeliveryModule {

    @Binds
    abstract fun bindFeatureDeliveryManager(
        featureDeliveryManagerImpl: FeatureDeliveryInstallerImpl
    ): FeatureDeliveryInstaller
}