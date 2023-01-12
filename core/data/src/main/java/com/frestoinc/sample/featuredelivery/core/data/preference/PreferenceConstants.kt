package com.frestoinc.sample.featuredelivery.core.data.preference

import androidx.datastore.preferences.core.booleanPreferencesKey

object PreferenceConstants {

    val INSTALL_TIME_ENABLED = booleanPreferencesKey("INSTALL_TIME_ENABLED")

    val ON_DEMAND_ENABLED = booleanPreferencesKey("ON_DEMAND_ENABLED")
}