package com.frestoinc.sample.featuredelivery.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.get
import com.frestoinc.sample.featuredelivery.FeatureDeliveryAppState
import com.frestoinc.sample.featuredelivery.core.designsystem.navigator.FeatureNavGraph
import com.frestoinc.sample.featuredelivery.navigation.FeatureAppRoute.Companion.toAppRoute
import com.frestoinc.sample.featuredelivery.ui.FeatureScreen
import com.frestoinc.sample.featuredelivery.ui.FeatureViewModel
import com.google.android.play.core.splitinstall.SplitInstallHelper
import kotlin.reflect.full.createInstance

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    appState: FeatureDeliveryAppState,
    featureViewModel: FeatureViewModel = hiltViewModel(),
    startDestinationRoute: FeatureAppRoute = FeatureAppRoute.MAIN
) {
    val context = LocalContext.current

    NavHost(
        modifier = modifier,
        navController = appState.navController,
        startDestination = startDestinationRoute.route
    ) {

        composable(route = startDestinationRoute.route) {
            FeatureScreen(
                availableFeatureList = FeatureAppRoute.featureDestinations,
                featureViewModel = featureViewModel,
            ) { route ->
                SplitInstallHelper.updateAppInfo(context)

                if (appState.navController.graph.findNode(route.route) == null) {
                    appState.navController.graph.addDestination(
                        ComposeNavigator.Destination(
                            provider[ComposeNavigator::class],
                            (Class.forName(route.navigationRoute).kotlin.createInstance() as FeatureNavGraph)
                                .moduleScreenComposable(
                                    modifier = modifier,
                                    navController = appState.navController
                                )
                        ).apply {
                            this.route = route.route
                        })
                }

                appState.navigateToTopLevelDestination(route)

                /*context.startActivity(Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("app://${appRoute.packageName}")
                    `package` = context.packageName
                })*/
            }
        }

        featureViewModel.installedModules.map { it.toAppRoute() }.forEach { route ->
            val featureNavGraph =
                (Class.forName(route.navigationRoute).kotlin.createInstance() as FeatureNavGraph)
            composable(
                route = route.route,
                content = featureNavGraph.moduleScreenComposable(
                    modifier = modifier,
                    navController = appState.navController
                )
            )
        }
    }
}