package com.frestoinc.sample.featuredelivery

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.frestoinc.sample.featuredelivery.core.designsystem.navigator.FeatureNavGraph
import com.frestoinc.sample.featuredelivery.core.domain.network.NetworkMonitor
import com.frestoinc.sample.featuredelivery.navigation.FeatureAppRoute
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class FeatureDeliveryAppState(
    val navController: NavHostController,
    private val coroutineScope: CoroutineScope,
    networkMonitor: NetworkMonitor,
) {

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: FeatureAppRoute
        @Composable get() = when (currentDestination?.route) {
            FeatureAppRoute.ON_BOARDING.route -> FeatureAppRoute.ON_BOARDING
            FeatureAppRoute.DEVICE_A.route -> FeatureAppRoute.DEVICE_A
            FeatureAppRoute.DEVICE_B.route -> FeatureAppRoute.DEVICE_B
            else -> FeatureAppRoute.MAIN
        }

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )

    val destinations: List<FeatureAppRoute> = FeatureAppRoute.values().toMutableList().apply {
        remove(FeatureAppRoute.MAIN)
    }.toList()

    fun navigateToTopLevelDestination(destination: FeatureAppRoute) {
        val topLevelNavOptions = navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // re-selecting the same item
            launchSingleTop = true
            // Restore state when re-selecting a previously selected item
            restoreState = true
        }

        (Class.forName(destination.navigationRoute).kotlin.objectInstance as FeatureNavGraph)
            .navigateToFeatures(navController, topLevelNavOptions)
    }

    fun onBackClick() {
        navController.popBackStack()
    }
}

@Composable
fun rememberFeatureDeliveryAppState(
    networkMonitor: NetworkMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController()
): FeatureDeliveryAppState =
    remember(navController, coroutineScope, networkMonitor) {
        FeatureDeliveryAppState(navController, coroutineScope, networkMonitor)
    }