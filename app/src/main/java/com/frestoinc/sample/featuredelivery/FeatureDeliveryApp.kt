package com.frestoinc.sample.featuredelivery

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.dp
import com.frestoinc.sample.featuredelivery.core.designsystem.extension.BaseLocaleText
import com.frestoinc.sample.featuredelivery.core.designsystem.ui.FeatureTopBar
import com.frestoinc.sample.featuredelivery.core.domain.R
import com.frestoinc.sample.featuredelivery.core.domain.network.NetworkMonitor
import com.frestoinc.sample.featuredelivery.navigation.AppNavHost

@Composable
fun FeatureDeliveryApp(
    networkMonitor: NetworkMonitor,
    appState: FeatureDeliveryAppState = rememberFeatureDeliveryAppState(
        networkMonitor = networkMonitor
    )
) {
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier.semantics {
            testTagsAsResourceId = true
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        snackbarHost = { SnackbarHost(snackBarHostState) },
        topBar = {
            FeatureTopBar(
                title = appState.currentTopLevelDestination.title,
                shouldShowBackButton = appState.currentDestination == null,
                onBackClick = { appState.onBackClick() }
            )
        }
    ) { padding ->

        val isOffline by appState.isOffline.collectAsState()
        val notConnectedString =
            BaseLocaleText.FromResource(R.string.internetNotConnected).asString()
        LaunchedEffect(isOffline) {
            if (isOffline) snackBarHostState.showSnackbar(
                message = notConnectedString,
                duration = SnackbarDuration.Indefinite
            )
        }

        AppNavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
                .padding(padding),
            appState = appState,
        )
    }
}