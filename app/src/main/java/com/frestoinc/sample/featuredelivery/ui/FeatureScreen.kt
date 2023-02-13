package com.frestoinc.sample.featuredelivery.ui

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.frestoinc.sample.featuredelivery.core.designsystem.ui.FeatureBastText
import com.frestoinc.sample.featuredelivery.core.domain.delivery.events.FeatureDeliveryActionEvent
import com.frestoinc.sample.featuredelivery.navigation.FeatureAppRoute
import com.frestoinc.sample.featuredelivery.navigation.FeatureAppRoute.Companion.toAppRoute

@Composable
fun FeatureScreen(
    modifier: Modifier = Modifier,
    featureViewModel: FeatureViewModel = hiltViewModel(),
    availableFeatureList: List<FeatureAppRoute> = emptyList(),
    onNavigateToModule: (FeatureAppRoute) -> Unit = {},
) {
    val featureUiState by featureViewModel.featureUiState.collectAsStateWithLifecycle()

    when (featureUiState) {
        is FeatureUiState.Loading ->
            FeatureLoadingState(
                modifier = modifier
            )
        is FeatureUiState.FeaturesAvailable -> {
            FeatureInstallState(
                modifier = modifier,
                installedFeatureList = (featureUiState as FeatureUiState.FeaturesAvailable).modules.map {
                    it.toAppRoute()
                },
                availableFeatureList = availableFeatureList,
                onNavigateToModule = onNavigateToModule,
                onRequestToUninstallModule = { appRoute ->
                    featureViewModel.invokeEvent(
                        FeatureDeliveryActionEvent.Uninstall(appRoute.route)
                    )
                },
                onRequestToInstallModule = { appRoute ->
                    featureViewModel.invokeEvent(
                        FeatureDeliveryActionEvent.StartDownload(appRoute.route)
                    )
                })
        }
        is FeatureUiState.FeatureEmpty ->
            FeatureEmptyState(
                modifier = modifier,
                missingFeatureList = FeatureAppRoute.featureDestinations
            ) { appRoute ->
                featureViewModel.invokeEvent(
                    FeatureDeliveryActionEvent.StartDownload(appRoute.route)
                )
            }
    }
}

@Composable
fun FeatureLoadingState(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        AnimatedVisibility(
            modifier = modifier
                .wrapContentSize()
                .align(Alignment.Center),
            visible = true
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun FeatureEmptyState(
    modifier: Modifier = Modifier,
    missingFeatureList: List<FeatureAppRoute> = emptyList(),
    onRequestToInstallModule: (FeatureAppRoute) -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FeatureBastText(
            modifier = modifier.padding(10.dp),
            text = "No Modules Found"
        )
        LazyColumn(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(missingFeatureList) { appRoute ->
                Button(
                    modifier = modifier.padding(10.dp),
                    onClick = { onRequestToInstallModule(appRoute) }
                ) {
                    FeatureBastText(text = "Click to download $appRoute module")
                }
            }
        }
    }
}

@Composable
fun FeatureInstallState(
    modifier: Modifier = Modifier,
    installedFeatureList: List<FeatureAppRoute> = emptyList(),
    availableFeatureList: List<FeatureAppRoute> = emptyList(),
    onNavigateToModule: (FeatureAppRoute) -> Unit = {},
    onRequestToUninstallModule: (FeatureAppRoute) -> Unit = {},
    onRequestToInstallModule: (FeatureAppRoute) -> Unit = {}
) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        installedFeatureList.forEach { appRoute ->
            Row(
                modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                Button(
                    modifier = modifier
                        .padding(10.dp),
                    onClick = { onNavigateToModule(appRoute) }
                ) {
                    FeatureBastText(text = "Navigate To\n${appRoute.title} module")
                }
                Button(
                    modifier = modifier
                        .padding(10.dp),
                    onClick = { onRequestToUninstallModule(appRoute) }
                ) {
                    FeatureBastText(text = "Uninstall\n${appRoute.title} module")
                }
            }

        }

        availableFeatureList.filter { it !in installedFeatureList }.forEach { appRoute ->
            Button(
                modifier = modifier.padding(10.dp),
                onClick = { onRequestToInstallModule(appRoute) }
            ) {
                FeatureBastText(text = "Click to download\n${appRoute.title} module")
            }
        }
    }
}

@Preview("default", showSystemUi = true)
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
private fun FeatureLoadingStatePreview() {
    FeatureLoadingState()
}

@Preview("default", showSystemUi = true)
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
private fun FeatureEmptyStatePreview() {
    FeatureEmptyState(
        missingFeatureList = listOf(
            FeatureAppRoute.MAIN,
            FeatureAppRoute.ON_BOARDING,
            FeatureAppRoute.DEVICE_A
        )
    )
}

@Preview("default", showSystemUi = true)
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
private fun FeatureInstallStatePreview() {
    FeatureInstallState(
        installedFeatureList = listOf(
            FeatureAppRoute.MAIN,
            FeatureAppRoute.ON_BOARDING,
        ),
        availableFeatureList = FeatureAppRoute.featureDestinations
    )
}