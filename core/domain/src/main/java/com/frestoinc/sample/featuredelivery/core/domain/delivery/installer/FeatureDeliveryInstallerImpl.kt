package com.frestoinc.sample.featuredelivery.core.domain.delivery.installer

import com.frestoinc.sample.featuredelivery.core.domain.delivery.events.FeatureDeliveryActionStatus
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeatureDeliveryInstallerImpl @Inject constructor(
    private val splitInstallManager: SplitInstallManager,
) : FeatureDeliveryInstaller {

    private var _stateChangedEmitter: ((FeatureDeliveryActionStatus) -> Unit)? = null

    private var _currentDownloadModuleName: MutableList<String> = mutableListOf()

    private val _splitInstallListener = createSplitInstallListener(
        onInstalling = { entity ->
            if (entity.featureNames.containsAll(_currentDownloadModuleName)) {
                _stateChangedEmitter?.invoke(
                    FeatureDeliveryActionStatus.FeatureInstallState.FeatureInstalling(
                        entity
                    )
                )
            }
        },
        onInstalled = { entity ->
            if (entity.featureNames.containsAll(_currentDownloadModuleName)) {
                _stateChangedEmitter?.invoke(
                    FeatureDeliveryActionStatus.FeatureInstallState.FeatureInstalled(
                        entity
                    )
                )
                unregisterListener()
            }
        },
        onDownloaded = { entity ->
            if (entity.featureNames.containsAll(_currentDownloadModuleName)) {
                _stateChangedEmitter?.invoke(
                    FeatureDeliveryActionStatus.FeatureDownloadState.FeatureDownloaded(
                        entity
                    )
                )
            }
        },
        onCanceling = { entity ->
            if (entity.featureNames.containsAll(_currentDownloadModuleName)) {
                _stateChangedEmitter?.invoke(FeatureDeliveryActionStatus.FeatureError(entity))
            }
        },
        onCanceled = { entity ->
            if (entity.featureNames.containsAll(_currentDownloadModuleName)) {
                _stateChangedEmitter?.invoke(FeatureDeliveryActionStatus.FeatureError(entity))
                unregisterListener()
            }
        },
        onDownloading = { entity, value ->
            if (entity.featureNames.containsAll(_currentDownloadModuleName)) {
                _stateChangedEmitter?.invoke(
                    FeatureDeliveryActionStatus.FeatureDownloadState.FeatureDownloading(
                        entity,
                        value
                    )
                )
            }
        },
        onFailed = { entity, errorCode ->
            if (entity.featureNames.containsAll(_currentDownloadModuleName)) {
                _stateChangedEmitter?.invoke(
                    FeatureDeliveryActionStatus.FeatureError(
                        entity, FeatureDeliveryException(errorCode)
                    )
                )
                unregisterListener()
            }
        },
        onUnknown = { entity, status ->
            if (entity.featureNames.containsAll(_currentDownloadModuleName)) {
                _stateChangedEmitter?.invoke(
                    FeatureDeliveryActionStatus.FeatureUnknown(
                        entity,
                        status
                    )
                )
            }
        },
    )

    private val _installedFeatures: MutableStateFlow<Set<String>> =
        MutableStateFlow(splitInstallManager.installedModules)

    override val installedFeatures: StateFlow<Set<String>> =
        _installedFeatures.asStateFlow()

    override fun downloadFeature(
        vararg modules: String,
        onStateChanged: (FeatureDeliveryActionStatus) -> Unit
    ) {
        registerListener(onStateChanged)
        splitInstallManager.startInstall(createRequest(modules = modules))
    }

    override fun deleteFeature(module: String) {
        splitInstallManager.deferredUninstall(listOf(module))
            .addOnCompleteListener {
                _installedFeatures.value = splitInstallManager.installedModules
            }
    }

    override fun cancelDownload(taskId: Int) {
        splitInstallManager.cancelInstall(taskId)
        unregisterListener()
    }

    private fun registerListener(onStateChanged: (FeatureDeliveryActionStatus) -> Unit) {
        _stateChangedEmitter = onStateChanged
        splitInstallManager.registerListener(_splitInstallListener)
    }

    private fun unregisterListener() {
        _stateChangedEmitter = null
        _currentDownloadModuleName.clear()
        splitInstallManager.unregisterListener(_splitInstallListener)
        _installedFeatures.value = splitInstallManager.installedModules
    }

    private fun createRequest(vararg modules: String): SplitInstallRequest {
        if (modules.isEmpty()) throw IllegalArgumentException("module name cannot be empty")
        return with(SplitInstallRequest.newBuilder()) {
            modules.forEach { moduleName ->
                _currentDownloadModuleName.add(moduleName)
                addModule(moduleName)
            }
            build()
        }
    }
}