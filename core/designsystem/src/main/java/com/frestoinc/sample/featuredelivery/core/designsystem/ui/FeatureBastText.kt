package com.frestoinc.sample.featuredelivery.core.designsystem.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun FeatureBastText(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        maxLines = 2,
        overflow = TextOverflow.Visible,
        textAlign = TextAlign.Center
    )
}