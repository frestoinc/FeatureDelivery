package com.frestoinc.sample.featuredelivery.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frestoinc.sample.featuredelivery.core.domain.delivery.events.FeatureDeliveryActionEvent
import com.frestoinc.sample.featuredelivery.core.domain.delivery.events.FeatureDeliveryActionStatus
import com.frestoinc.sample.featuredelivery.core.domain.delivery.installer.FeatureDeliveryInstaller
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class FeatureViewModel @Inject constructor(
    private val featureDeliveryInstaller: FeatureDeliveryInstaller
) : ViewModel() {

    companion object {
        private const val LOAD_DELAY = 1 * 1000L
    }

    private val parseStatusResult: (FeatureDeliveryActionStatus) -> Unit = { status ->
        _featureUiState.value = when (status) {
            is FeatureDeliveryActionStatus.FeatureInstallState.FeatureInstalled ->
                FeatureUiState.FeaturesAvailable(featureDeliveryInstaller.installedFeatures.value)
            is FeatureDeliveryActionStatus.FeatureError ->
                FeatureUiState.FeatureEmpty
            else -> FeatureUiState.Loading

        }
    }

    private val _featureUiState: MutableStateFlow<FeatureUiState> =
        MutableStateFlow(FeatureUiState.Loading)

    val installedModules: Set<String>
        get() = featureDeliveryInstaller.installedFeatures.value

    val featureUiState: StateFlow<FeatureUiState> =
        _featureUiState.asStateFlow().combine(
            featureDeliveryInstaller.installedFeatures
        ) { _: FeatureUiState, b: Set<String> ->
            if (b.isEmpty()) {
                FeatureUiState.FeatureEmpty
            } else {
                FeatureUiState.FeaturesAvailable(b)
            }
        }.onStart {
            delay(LOAD_DELAY)
            emit(FeatureUiState.Loading)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = FeatureUiState.Loading
        )

    fun invokeEvent(event: FeatureDeliveryActionEvent) {
        when (event) {
            is FeatureDeliveryActionEvent.StartDownload -> startDownload(event.moduleName)
            is FeatureDeliveryActionEvent.StopDownload -> stopDownload(event.taskId)
            is FeatureDeliveryActionEvent.Uninstall -> uninstallModule(event.moduleName)
        }
    }

    private fun startDownload(module: String) {
        featureDeliveryInstaller.downloadFeature(
            module = module,
            onStateChanged = parseStatusResult
        )
    }

    private fun stopDownload(taskId: Int) {
        featureDeliveryInstaller.cancelDownload(taskId)
    }

    private fun uninstallModule(module: String) {
        featureDeliveryInstaller.deleteFeature(module)
    }
}

sealed interface FeatureUiState {
    object Loading : FeatureUiState
    object FeatureEmpty : FeatureUiState
    data class FeaturesAvailable(val modules: Set<String>) : FeatureUiState
}