package com.frestoinc.sample.featuredelivery.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.frestoinc.sample.featuredelivery.FeatureDeliveryAppState
import com.frestoinc.sample.featuredelivery.core.designsystem.navigator.FeatureNavGraph
import com.frestoinc.sample.featuredelivery.ui.FeatureScreen
import timber.log.Timber

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    appState: FeatureDeliveryAppState,
    startDestinationRoute: FeatureAppRoute = FeatureAppRoute.MAIN
) {
    NavHost(
        modifier = modifier,
        navController = appState.navController,
        startDestination = startDestinationRoute.route
    ) {
        composable(route = startDestinationRoute.route) {
            FeatureScreen(
                appState = appState,
            ) {
                appState.navigateToTopLevelDestination(FeatureAppRoute.ON_BOARDING)
            }
        }

        appState.destinations.filter { it != startDestinationRoute }.forEach { route ->
            val navGraph =
                Class.forName(route.navigationRoute).kotlin.objectInstance as FeatureNavGraph?
            if (navGraph != null) {
                Timber.e("not null: ${route.route}")
                composable(
                    route = route.route,
                    content = navGraph.moduleScreenComposable(
                        modifier = modifier,
                        navController = appState.navController,
                    )
                )
            } else {
                Timber.e("null for: ${route.route}")
            }
        }

    }
}