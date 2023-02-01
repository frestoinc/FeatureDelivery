package com.frestoinc.sample.featuredelivery.core.domain.delivery.installer

import com.frestoinc.sample.featuredelivery.core.domain.delivery.events.FeatureDeliveryActionStatus

interface FeatureDeliveryInstaller {

    val installedFeatures: Set<String>

    fun isFeatureInstalled(value: String): Boolean

    fun downloadFeature(
        vararg modules: String,
        onStateChanged: (FeatureDeliveryActionStatus) -> Unit
    )

    fun cancelDownload(
        taskId: Int
    )
}