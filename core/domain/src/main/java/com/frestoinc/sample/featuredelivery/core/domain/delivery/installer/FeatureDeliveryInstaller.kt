package com.frestoinc.sample.featuredelivery.core.domain.delivery.installer

import com.frestoinc.sample.featuredelivery.core.domain.delivery.events.FeatureDeliveryActionStatus
import kotlinx.coroutines.flow.StateFlow

interface FeatureDeliveryInstaller {

    val installedFeatures: StateFlow<Set<String>>

    fun downloadFeature(
        module: String,
        onStateChanged: (FeatureDeliveryActionStatus) -> Unit
    )

    fun deleteFeature(
        module: String
    )

    fun cancelDownload(
        taskId: Int
    )
}