package com.frestoinc.sample.featuredelivery.core.domain.delivery

import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeatureDeliveryManagerImpl @Inject constructor(
    private val splitInstallManager: SplitInstallManager
) : FeatureDeliveryManager {

    override val installedModules: Set<String>
        get() = splitInstallManager.installedModules

    fun requestForInstall(
        vararg modules: String,
        onSuccessListener: (Int) -> Unit = {},
        onFailureListener: (Exception) -> Unit = {}
    ) {

        splitInstallManager
            .startInstall(
                buildRequest(modules = modules)
            )
            .addOnSuccessListener(onSuccessListener)
            .addOnFailureListener(onFailureListener)
    }


    private fun buildRequest(vararg modules: String): SplitInstallRequest {
        if (modules.isEmpty()) throw IllegalArgumentException("module name cannot be empty")
        return with(SplitInstallRequest.newBuilder()) {
            modules.forEach { module ->
                addModule(module)
            }
            build()
        }
    }

}