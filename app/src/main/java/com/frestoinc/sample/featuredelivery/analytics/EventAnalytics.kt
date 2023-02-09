package com.frestoinc.sample.featuredelivery.analytics

interface EventAnalytics {

    fun logEvent(event: String, param: String)

    fun logCrash(exception: Exception)
}