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
import com.frestoinc.sample.featuredelivery.analytics.EventAnalytics
import com.frestoinc.sample.featuredelivery.core.designsystem.navigator.FeatureNavGraph
import com.frestoinc.sample.featuredelivery.core.domain.network.NetworkMonitor
import com.frestoinc.sample.featuredelivery.navigation.FeatureAppRoute
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlin.reflect.full.createInstance

class FeatureDeliveryAppState(
    val navController: NavHostController,
    private val eventAnalytics: EventAnalytics,
    private val coroutineScope: CoroutineScope,
    networkMonitor: NetworkMonitor,
) {

    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: FeatureAppRoute?
        @Composable get() = when (currentDestination?.route) {
            FeatureAppRoute.MAIN.route -> FeatureAppRoute.MAIN
            FeatureAppRoute.ON_BOARDING.route -> FeatureAppRoute.ON_BOARDING
            FeatureAppRoute.DEVICE_A.route -> FeatureAppRoute.DEVICE_A
            FeatureAppRoute.DEVICE_B.route -> FeatureAppRoute.DEVICE_B
            else -> null
        }

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )

    val destinations: List<FeatureAppRoute> =
        FeatureAppRoute.values().filter { it != FeatureAppRoute.MAIN }

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
        val navGraph =
            runCatching {
                Class.forName(destination.navigationRoute)?.kotlin?.createInstance() as FeatureNavGraph?
            }.getOrNull() ?: return
        logEvent("navigation", destination.navigationRoute)
        navGraph.navigateToFeatures(
            navController,
            topLevelNavOptions
        )
    }

    fun onBackClick() {
        navController.popBackStack()
    }

    fun logEvent(event: String, param: String) {
        eventAnalytics.logEvent(event, param)
    }

    fun logCrash(exception: Exception) {
        eventAnalytics.logCrash(exception)
    }
}

@Composable
fun rememberFeatureDeliveryAppState(
    networkMonitor: NetworkMonitor,
    eventAnalytics: EventAnalytics,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController()
): FeatureDeliveryAppState =
    remember(navController, coroutineScope, networkMonitor) {
        FeatureDeliveryAppState(navController, eventAnalytics, coroutineScope, networkMonitor)
    }