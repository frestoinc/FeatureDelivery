package com.frestoinc.sample.featuredelivery.feature.devicea.navigation

import androidx.annotation.Keep
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.frestoinc.sample.featuredelivery.core.designsystem.navigator.FeatureNavGraph
import com.frestoinc.sample.featuredelivery.feature.devicea.ui.DeviceAScreen
import com.frestoinc.sample.featuredelivery.navigation.FeatureAppRoute

@Keep
class DeviceANavGraph : FeatureNavGraph {

    override val featureRoute: String
        get() = FeatureAppRoute.DEVICE_A.route

    override fun navigateToFeatures(navController: NavHostController, navOptions: NavOptions?) {
        navController.navigate(featureRoute, navOptions)
    }

    override fun moduleScreenComposable(
        modifier: Modifier,
        navController: NavHostController
    ): @Composable (NavBackStackEntry) -> Unit = {
        DeviceAScreen(
            modifier = modifier,
            navController = navController
        )
    }
}