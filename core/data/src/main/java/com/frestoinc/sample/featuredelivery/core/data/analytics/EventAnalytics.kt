package com.frestoinc.sample.featuredelivery.core.data.analytics

interface EventAnalytics {

    fun logEvent(event: String, param: String)

    fun logCrash(exception: Exception)
}