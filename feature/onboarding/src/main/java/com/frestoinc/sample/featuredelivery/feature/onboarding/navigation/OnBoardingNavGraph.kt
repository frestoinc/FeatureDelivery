package com.frestoinc.sample.featuredelivery.feature.onboarding.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.frestoinc.sample.featuredelivery.core.designsystem.navigator.FeatureNavGraph
import com.frestoinc.sample.featuredelivery.feature.onboarding.ui.OnBoardingScreen
import com.frestoinc.sample.featuredelivery.navigation.FeatureAppRoute

class OnBoardingNavGraph : FeatureNavGraph {

    override val featureRoute: String
        get() = FeatureAppRoute.ON_BOARDING.route

    override fun navigateToFeatures(navController: NavHostController, navOptions: NavOptions?) {
        navController.navigate(featureRoute, navOptions)
    }

    override fun moduleScreenComposable(
        modifier: Modifier,
        navController: NavHostController
    ): @Composable (NavBackStackEntry) -> Unit = {
        OnBoardingScreen(
            modifier = modifier,
            navController = navController
        )
    }
}