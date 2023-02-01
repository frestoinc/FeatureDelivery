package com.frestoinc.sample.featuredelivery.core.domain.delivery.installer

import com.frestoinc.sample.featuredelivery.core.domain.delivery.model.FeatureEntity
import com.google.android.play.core.ktx.moduleNames
import com.google.android.play.core.ktx.sessionId
import com.google.android.play.core.ktx.status
import com.google.android.play.core.splitinstall.SplitInstallSessionState
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus

@PublishedApi
internal fun SplitInstallSessionState.toFeatureEntity() =
    FeatureEntity(
        moduleNames,
        sessionId
    )

inline fun createSplitInstallListener(
    crossinline onUnknown: (FeatureEntity, Int) -> Unit = { _, _ -> },
    crossinline onDownloading: (FeatureEntity, Int) -> Unit = { _, _ -> },
    crossinline onDownloaded: (FeatureEntity) -> Unit = {},
    crossinline onInstalling: (FeatureEntity) -> Unit = {},
    crossinline onInstalled: (FeatureEntity) -> Unit = {},
    crossinline onFailed: (FeatureEntity, Int) -> Unit = { _, _ -> },
    crossinline onCanceling: (FeatureEntity) -> Unit = {},
    crossinline onCanceled: (FeatureEntity) -> Unit = {},
): SplitInstallStateUpdatedListener = SplitInstallStateUpdatedListener { state ->
    when (state.status()) {
        SplitInstallSessionStatus.DOWNLOADING ->
            onDownloading(
                state.toFeatureEntity(),
                ((state.bytesDownloaded() * 100) / state.totalBytesToDownload()).toInt()
            )
        SplitInstallSessionStatus.DOWNLOADED ->
            onDownloaded(state.toFeatureEntity())
        SplitInstallSessionStatus.INSTALLING -> onInstalling(state.toFeatureEntity())
        SplitInstallSessionStatus.INSTALLED -> onInstalled(state.toFeatureEntity())
        SplitInstallSessionStatus.FAILED -> onFailed(state.toFeatureEntity(), state.errorCode())
        SplitInstallSessionStatus.CANCELING -> onCanceling(state.toFeatureEntity())
        SplitInstallSessionStatus.CANCELED -> onCanceled(state.toFeatureEntity())
        else -> onUnknown(state.toFeatureEntity(), state.status)
    }
}

