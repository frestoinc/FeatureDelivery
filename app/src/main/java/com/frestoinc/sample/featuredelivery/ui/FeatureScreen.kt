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
import com.frestoinc.sample.featuredelivery.FeatureDeliveryAppState
import com.frestoinc.sample.featuredelivery.core.designsystem.ui.FeatureBastText
import com.frestoinc.sample.featuredelivery.core.domain.delivery.events.FeatureDeliveryActionEvent
import com.frestoinc.sample.featuredelivery.navigation.FeatureAppRoute
import com.frestoinc.sample.featuredelivery.navigation.FeatureAppRoute.Companion.toAppRoute

@Composable
fun FeatureScreen(
    modifier: Modifier = Modifier,
    appState: FeatureDeliveryAppState,
    featureViewModel: FeatureViewModel = hiltViewModel(),
    availableFeatureList: List<String> = emptyList(),
) {
    val featureUiState by featureViewModel.featureUiState.collectAsStateWithLifecycle()

    when (featureUiState) {
        is FeatureUiState.Loading ->
            FeatureLoadingState(
                modifier = modifier
            )
        is FeatureUiState.FeaturesAvailable ->
            FeatureInstallState(
                modifier = modifier,
                installedFeatureList = (featureUiState as FeatureUiState.FeaturesAvailable).modules.toList(),
                availableFeatureList = availableFeatureList,
                onNavigateToModule = { moduleName ->
                    appState.navigateToTopLevelDestination(moduleName.toAppRoute())
                }, onRequestOnBoardingModule = { moduleName ->
                    featureViewModel.invokeEvent(
                        FeatureDeliveryActionEvent.StartDownload(moduleName)
                    )
                })
        is FeatureUiState.FeatureError ->
            FeatureNotInstalledState(
                modifier = modifier,
                missingFeatureList = appState.destinations.map { it.route }
            ) { moduleName ->
                featureViewModel.invokeEvent(
                    FeatureDeliveryActionEvent.StartDownload(moduleName)
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
fun FeatureNotInstalledState(
    modifier: Modifier = Modifier,
    missingFeatureList: List<String> = emptyList(),
    onRequestOnBoardingModule: (String) -> Unit = {}
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
            items(missingFeatureList) { moduleName ->
                Button(
                    modifier = modifier.padding(10.dp),
                    onClick = { onRequestOnBoardingModule(moduleName) }
                ) {
                    FeatureBastText(text = "Click to download $moduleName module")
                }
            }
        }
    }
}

@Composable
fun FeatureInstallState(
    modifier: Modifier = Modifier,
    installedFeatureList: List<String> = emptyList(),
    availableFeatureList: List<String> = emptyList(),
    onNavigateToModule: (String) -> Unit = {},
    onRequestOnBoardingModule: (String) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(installedFeatureList) { moduleName ->
            Button(
                modifier = modifier.padding(10.dp),
                onClick = { onNavigateToModule(moduleName) }
            ) {
                FeatureBastText(text = "$moduleName module")
            }
        }
        val filteredList = availableFeatureList.filter { it !in installedFeatureList }
        items(filteredList) { moduleName ->
            Button(
                modifier = modifier.padding(10.dp),
                onClick = { onRequestOnBoardingModule(moduleName) }
            ) {
                FeatureBastText(text = "Click to download $moduleName module")
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
private fun FeatureNotInstalledStatePreview() {
    FeatureNotInstalledState(
        missingFeatureList = listOf(
            "main",
            "OnBoarding",
            "DeviceA"
        )
    )
}

@Preview("default", showSystemUi = true)
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
private fun FeatureInstallStatePreview() {
    FeatureInstallState(
        installedFeatureList = listOf(
            "main",
            "onboarding",
        ),
        availableFeatureList = FeatureAppRoute.values().map { it.route }
    )
}