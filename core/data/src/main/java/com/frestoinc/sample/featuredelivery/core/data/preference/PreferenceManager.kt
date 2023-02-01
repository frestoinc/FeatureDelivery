package com.frestoinc.sample.featuredelivery.core.data.preference

import kotlinx.coroutines.flow.Flow

interface PreferenceManager {

    val onBoardingEnabled: Flow<Boolean>

    suspend fun setOnBoardingEnabled(value: Boolean)

    val deviceAEnabled: Flow<Boolean>

    suspend fun setDeviceAEnabled(value: Boolean)

    val deviceBEnabled: Flow<Boolean>

    suspend fun setDeviceBEnabled(value: Boolean)
}