package com.frestoinc.sample.featuredelivery.navigation

import androidx.annotation.Keep
import com.frestoinc.sample.featuredelivery.core.designsystem.navigator.FeatureNavGraph

/**
 * App route placeholder to hold the on-demand package navigation graph
 * that extend @see [FeatureNavGraph]
 *
 * @property route module name
 * @property navigationRoute module package extending [FeatureNavGraph]
 *
 */
@Keep
enum class FeatureAppRoute(
    val route: String,
    val title: String,
    val packageName: String,
    val navigationRoute: String
) {
    MAIN(
        route = "main",
        title = "Main",
        packageName = "",
        navigationRoute = ""
    ),
    ON_BOARDING(
        route = "onboarding",
        title = "On Boarding",
        packageName = "com.frestoinc.sample.featuredelivery.feature.onboarding",
        navigationRoute = "com.frestoinc.sample.featuredelivery.feature.onboarding.navigation.OnBoardingNavGraph"
    ),
    DEVICE_A(
        route = "devicea",
        title = "Device A",
        packageName = "com.frestoinc.sample.featuredelivery.feature.devicea",
        navigationRoute = "com.frestoinc.sample.featuredelivery.feature.devicea.navigation.DeviceANavGraph"
    ),
    DEVICE_B(
        route = "deviceb",
        title = "Device B",
        packageName = "com.frestoinc.sample.featuredelivery.feature.deviceb",
        navigationRoute = "com.frestoinc.sample.featuredelivery.feature.deviceb.navigation.DeviceBNavGraph"
    );

    companion object {

        val featureDestinations: List<FeatureAppRoute> =
            values().filter { it != MAIN }

        fun String.toAppRoute(): FeatureAppRoute =
            values().find { it.route == this } ?: MAIN


    }
}