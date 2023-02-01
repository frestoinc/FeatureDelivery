package com.frestoinc.sample.featuredelivery.core.data.preference

import androidx.datastore.preferences.core.booleanPreferencesKey

object PreferenceConstants {

    val ON_BOARDING_ENABLED = booleanPreferencesKey("ON_BOARDING_ENABLED")

    val DEVICE_A_ENABLED = booleanPreferencesKey("DEVICE_A_ENABLED")

    val DEVICE_B_ENABLED = booleanPreferencesKey("DEVICE_B_ENABLED")
}