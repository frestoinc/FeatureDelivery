package com.frestoinc.sample.featuredelivery.core.designsystem.navigator

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions

interface FeatureNavGraph {

    val featureRoute: String

    fun navigateToFeatures(navController: NavHostController, navOptions: NavOptions? = null)

    fun moduleScreenComposable(
        modifier: Modifier,
        navController: NavHostController
    ): @Composable (NavBackStackEntry) -> Unit
}