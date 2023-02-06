package com.frestoinc.sample.featuredelivery.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frestoinc.sample.featuredelivery.core.domain.delivery.events.FeatureDeliveryActionEvent
import com.frestoinc.sample.featuredelivery.core.domain.delivery.events.FeatureDeliveryActionStatus
import com.frestoinc.sample.featuredelivery.core.domain.delivery.installer.FeatureDeliveryInstaller
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FeatureViewModel @Inject constructor(
    private val featureDeliveryInstaller: FeatureDeliveryInstaller
) : ViewModel() {

    companion object {
        private const val LOAD_DELAY = 1 * 1000L
    }

    private val parseStatusResult: (FeatureDeliveryActionStatus) -> Unit = { status ->
        Timber.e("status: $status")
    }

    private val _installedFeatures: MutableSet<String>
        get() = featureDeliveryInstaller.installedFeatures.toMutableSet()
    val featureUiState: StateFlow<FeatureUiState> =
        flowOf(_installedFeatures)
            .map {
                if (it.isEmpty()) {
                    FeatureUiState.FeatureError
                } else {
                    FeatureUiState.FeaturesAvailable(it)
                }
            }
            .onStart {
                FeatureUiState.Loading
                delay(LOAD_DELAY)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = FeatureUiState.Loading
            )

    private val _currentFeatureModuleName: MutableStateFlow<String?> =
        MutableStateFlow(null)

    fun invokeEvent(event: FeatureDeliveryActionEvent) {
        when (event) {
            is FeatureDeliveryActionEvent.StartDownload -> startDownload(event.moduleName)
            is FeatureDeliveryActionEvent.StopDownload -> stopDownload(event.taskId)
        }
    }

    private fun startDownload(module: String) {
        featureDeliveryInstaller.downloadFeature(
            modules = arrayOf(module),
            onStateChanged = parseStatusResult
        )
    }

    private fun stopDownload(taskId: Int) {
        featureDeliveryInstaller.cancelDownload(taskId)
    }
}

sealed interface FeatureUiState {
    object Loading : FeatureUiState
    object FeatureError : FeatureUiState
    data class FeaturesAvailable(val modules: Set<String>) : FeatureUiState
}