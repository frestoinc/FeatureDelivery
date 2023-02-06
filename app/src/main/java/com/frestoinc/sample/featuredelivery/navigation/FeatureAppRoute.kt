package com.frestoinc.sample.featuredelivery.navigation

import com.frestoinc.sample.featuredelivery.core.designsystem.navigator.FeatureNavGraph

/**
 * App route placeholder to hold the on-demand package navigation graph
 * that extend @see [FeatureNavGraph]
 *
 * @property route module name
 * @property navigationRoute module package extending [FeatureNavGraph]
 *
 */
enum class FeatureAppRoute(
    val route: String,
    val title: String,
    val navigationRoute: String
) {
    MAIN(
        route = "main",
        title = "Main",
        navigationRoute = ""
    ),
    ON_BOARDING(
        route = "onboarding",
        title = "On Boarding",
        navigationRoute = "com.frestoinc.sample.featuredelivery.feature.onboarding.navigation.OnBoardingNavGraph"
    ),
    DEVICE_A(
        route = "devicea",
        title = "Device A",
        navigationRoute = "com.frestoinc.sample.featuredelivery.feature.devicea.navigation.DeviceANavGraph"
    ),
    DEVICE_B(
        route = "deviceb",
        title = "Device B",
        navigationRoute = "com.frestoinc.sample.featuredelivery.feature.deviceb.navigation.DeviceBNavGraph"
    );

    companion object {

        fun String.toAppRoute(): FeatureAppRoute =
            values().find { it.route == this } ?: MAIN
    }
}