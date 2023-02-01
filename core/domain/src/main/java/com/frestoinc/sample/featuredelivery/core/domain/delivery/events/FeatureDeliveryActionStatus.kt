package com.frestoinc.sample.featuredelivery.core.domain.delivery.events

import com.frestoinc.sample.featuredelivery.core.domain.delivery.installer.FeatureDeliveryException
import com.frestoinc.sample.featuredelivery.core.domain.delivery.model.FeatureEntity

sealed class FeatureDeliveryActionStatus {
    sealed class FeatureDownloadState : FeatureDeliveryActionStatus() {
        class FeatureNotDownload(val entity: FeatureEntity) : FeatureDownloadState()
        class FeatureDownloaded(val entity: FeatureEntity) : FeatureDownloadState()
        class FeatureDownloading(val entity: FeatureEntity, val progress: Int) :
            FeatureDownloadState()
    }

    sealed class FeatureInstallState : FeatureDeliveryActionStatus() {
        class FeatureInstalling(val entity: FeatureEntity) : FeatureInstallState()
        class FeatureInstalled(val entity: FeatureEntity) : FeatureInstallState()
    }

    class FeatureUnknown(
        val entity: FeatureEntity,
        val status: Int
    ) : FeatureDeliveryActionStatus()

    class FeatureError(
        val entity: FeatureEntity,
        val error: FeatureDeliveryException? = null
    ) : FeatureDeliveryActionStatus()

}