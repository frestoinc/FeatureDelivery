package com.frestoinc.sample.featuredelivery.core.domain.delivery.events

sealed class FeatureDeliveryActionEvent {
    class StartDownload(val moduleName: String) : FeatureDeliveryActionEvent()
    class StopDownload(val taskId: Int) : FeatureDeliveryActionEvent()
}