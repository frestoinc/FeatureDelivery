package com.frestoinc.sample.featuredelivery.feature.deviceb.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.frestoinc.sample.featuredelivery.core.designsystem.ui.FeatureBastText

@Composable
fun DeviceBScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    FeatureBastText(
        modifier = modifier,
        text = "Hi im Device B Screen"
    )
}