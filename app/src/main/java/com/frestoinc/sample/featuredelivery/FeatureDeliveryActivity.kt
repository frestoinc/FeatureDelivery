package com.frestoinc.sample.featuredelivery

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.frestoinc.sample.featuredelivery.ui.theme.MyApplicationTheme

class FeatureDeliveryActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Scaffold {
                    Greeting("Android")
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello $name!", textAlign = TextAlign.Justify)
    }

    @Preview("default", showBackground = true)
    @Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
    @Composable
    fun DefaultPreview() {

        MyApplicationTheme {
            // A surface container using the 'background' color from the theme
            Scaffold {
                Greeting("Android")
            }
        }
    }
}