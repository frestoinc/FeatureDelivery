package com.frestoinc.sample.featuredelivery.feature.onboarding.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.frestoinc.sample.featuredelivery.core.designsystem.ui.FeatureBastText

@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    FeatureBastText(
        modifier = modifier,
        text = "Hi this is the OnBoarding Screen"
    )
}