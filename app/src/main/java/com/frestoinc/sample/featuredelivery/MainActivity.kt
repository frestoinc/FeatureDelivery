package com.frestoinc.sample.featuredelivery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.frestoinc.sample.featuredelivery.core.data.analytics.EventAnalytics
import com.frestoinc.sample.featuredelivery.core.designsystem.ui.theme.MyApplicationTheme
import com.frestoinc.sample.featuredelivery.core.domain.network.NetworkMonitor
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    internal lateinit var networkMonitor: NetworkMonitor

    @Inject
    internal lateinit var eventAnalytics: EventAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContent {
            MyApplicationTheme {
                FeatureDeliveryApp(
                    networkMonitor = networkMonitor,
                    eventAnalytics = eventAnalytics
                )
            }
        }
    }
}