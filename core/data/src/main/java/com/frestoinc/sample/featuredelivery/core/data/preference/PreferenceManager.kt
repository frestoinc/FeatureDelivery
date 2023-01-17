package com.frestoinc.sample.featuredelivery.core.data.preference

import kotlinx.coroutines.flow.Flow

interface PreferenceManager {

    val isInstallTimeEnabled: Flow<Boolean>

    suspend fun setInstallTimeEnabled(value: Boolean)

    val isOnDemandEnabled: Flow<Boolean>

    suspend fun setOnDemandEnabled(value: Boolean)
}