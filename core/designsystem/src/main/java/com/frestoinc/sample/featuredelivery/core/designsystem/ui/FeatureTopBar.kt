package com.frestoinc.sample.featuredelivery.core.designsystem.ui

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.frestoinc.sample.featuredelivery.core.designsystem.extension.BaseLocaleText

@Composable
fun FeatureTopBar(
    modifier: Modifier = Modifier,
    title: String,
    shouldShowBackButton: Boolean = false,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    onBackClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        colors = colors,
        navigationIcon = {
            if (shouldShowBackButton) {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Filled.ArrowBack, "")
                }
            }
        },
        title = {
            Text(
                modifier = modifier,
                text = title
            )
        }
    )
}

@Preview("default", showSystemUi = true)
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
private fun FeatureTopBarPreview() {
    FeatureTopBar(
        title = BaseLocaleText.FromResource(android.R.string.untitled).asString()
    )
}

@Preview("default", showSystemUi = true)
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
private fun FeatureTopBarWithBackBtnPreview() {
    FeatureTopBar(
        title = BaseLocaleText.FromResource(android.R.string.untitled).asString(),
        shouldShowBackButton = true
    )
}