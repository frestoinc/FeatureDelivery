package com.frestoinc.sample.featuredelivery.ui

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.frestoinc.sample.featuredelivery.FeatureDeliveryAppState
import timber.log.Timber

@Composable
fun FeatureScreen(
    modifier: Modifier = Modifier,
    appState: FeatureDeliveryAppState,
    featureViewModel: FeatureViewModel = hiltViewModel(),
    onOnBoardingInstalled: () -> Unit = {},
) {
    val featureUiState by featureViewModel.featureUiState.collectAsStateWithLifecycle()

    when (featureUiState) {
        is FeatureUiState.Loading ->
            FeatureLoadingState(
                modifier = modifier
            )
        is FeatureUiState.FeaturesAvailable -> {
            Timber.e("asfaf: ${(featureUiState as FeatureUiState.FeaturesAvailable).modules}")
        }
        is FeatureUiState.FeatureError ->
            FeatureNotInstalledState(
                modifier = modifier,
                featureList = appState.destinations.map { it.route }
            )
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
    featureList: List<String> = emptyList(),
    onRequestOnBoardingModule: (String) -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = modifier.padding(10.dp),
            text = "No Modules Found"
        )
        LazyColumn(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(featureList) { moduleName ->
                Button(
                    modifier = modifier.padding(10.dp),
                    onClick = { onRequestOnBoardingModule(moduleName) }
                ) {
                    Text(text = "Click to download $moduleName module")
                }
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
    FeatureNotInstalledState()
}