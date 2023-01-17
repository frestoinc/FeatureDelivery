package com.frestoinc.sample.featuredelivery.ui.main

import androidx.lifecycle.ViewModel
import com.frestoinc.sample.featuredelivery.core.data.di.PreferenceDataManager
import com.frestoinc.sample.featuredelivery.core.data.preference.PreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @PreferenceDataManager private val preferenceManager: PreferenceManager
) : ViewModel() {

    val isInstallTimeFeatureEnabled: Flow<Boolean>
        get() = preferenceManager.isInstallTimeEnabled

    val isOnDemandEnabled: Flow<Boolean>
        get() = preferenceManager.isOnDemandEnabled
}