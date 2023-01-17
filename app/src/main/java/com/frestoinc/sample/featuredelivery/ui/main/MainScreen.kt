package com.frestoinc.sample.featuredelivery.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = hiltViewModel()
) {

    val isInstallTimeEnabled = mainViewModel.isInstallTimeFeatureEnabled.collectAsState(false)
    val isOnDemandEnabled = mainViewModel.isOnDemandEnabled.collectAsState(false)

    //show greetings and show status of install time module
}