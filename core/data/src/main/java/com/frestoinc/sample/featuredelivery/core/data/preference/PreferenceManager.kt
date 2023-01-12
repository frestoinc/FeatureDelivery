package com.frestoinc.sample.featuredelivery.core.data.preference

interface PreferenceManager {

    suspend fun isInstallTimeEnabled(): Boolean

    suspend fun setInstallTimeEnabled(value: Boolean)

    suspend fun isOnDemandEnabled(): Boolean

    suspend fun setOnDemandEnabled(value: Boolean)
}