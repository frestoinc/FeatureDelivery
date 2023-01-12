package com.frestoinc.sample.featuredelivery.feature.introduction

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.frestoinc.sample.featuredelivery.ui.theme.MyApplicationTheme

@Composable
fun IntroductionScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(Color.Red)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "This is a introduction screen",
            textAlign = TextAlign.Center,
            fontSize = 32.sp
        )
    }
}

@Preview("default", showBackground = true)
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DefaultPreview() {

    MyApplicationTheme {
        // A surface container using the 'background' color from the theme
        Scaffold {
            IntroductionScreen(
                modifier = Modifier.padding(it)
            )
        }
    }
}