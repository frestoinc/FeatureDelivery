package com.frestoinc.sample.featuredelivery.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.frestoinc.sample.featuredelivery.FeatureDeliveryAppState
import com.frestoinc.sample.featuredelivery.core.designsystem.navigator.FeatureNavGraph
import com.frestoinc.sample.featuredelivery.ui.FeatureScreen
import kotlin.reflect.full.createInstance

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
        val destinations = appState.destinations.filter { it != startDestinationRoute }

        composable(route = startDestinationRoute.route) {
            FeatureScreen(
                appState = appState,
                availableFeatureList = destinations.map { it.route }
            )
        }

        destinations.forEach { route ->
            val navGraph =
                runCatching {
                    Class.forName(route.navigationRoute)?.kotlin?.createInstance() as FeatureNavGraph?
                }.getOrNull() ?: return@NavHost

            composable(
                route = route.route,
                content = navGraph.moduleScreenComposable(
                    modifier = modifier,
                    navController = appState.navController,
                )
            )
        }

    }
}