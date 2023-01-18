package com.frestoinc.sample.featuredelivery.feature.introduction

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.frestoinc.sample.featuredelivery.core.domain.delivery.FeatureEntity
import com.frestoinc.sample.featuredelivery.core.domain.delivery.FeatureSet
import com.frestoinc.sample.featuredelivery.core.domain.delivery.FeatureStatus
import com.frestoinc.sample.featuredelivery.ui.theme.MyApplicationTheme

@Composable
fun IntroductionScreen(
    modifier: Modifier = Modifier,
    featureList: List<FeatureEntity> = listOf(),
    onFeatureClick: (FeatureStatus) -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        if (featureList.isEmpty()) {
            Text(
                modifier = modifier
                    .align(Alignment.Center),
                text = "No features available",
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )
        } else {
            LazyColumn(
                modifier = modifier
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(featureList) { entity ->
                    FeatureBtn(
                        modifier = modifier,
                        entity = entity,
                        onClick = onFeatureClick
                    )
                }
            }
        }

    }
}

@Composable
internal fun FeatureBtn(
    modifier: Modifier = Modifier,
    entity: FeatureEntity,
    onClick: (FeatureStatus) -> Unit = {}
) {
    OutlinedButton(
        modifier = modifier,
        onClick = { onClick(entity.status) }
    ) {
        Text(text = "Status of ${entity.set.title}: ${entity.status.name}")
    }
}

@Preview("default", showBackground = true)
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun IntroductionScreenEmptyPreview() {
    MyApplicationTheme {
        // A surface container using the 'background' color from the theme
        Scaffold {
            IntroductionScreen(
                modifier = Modifier.padding(it)
            )
        }
    }
}

@Preview("default", showBackground = true)
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun IntroductionScreenListPreview() {
    MyApplicationTheme {
        // A surface container using the 'background' color from the theme
        Scaffold {
            IntroductionScreen(
                modifier = Modifier.padding(it),
                featureList = listOf(
                    FeatureEntity(
                        FeatureSet.INTRODUCTION,
                        FeatureStatus.NOT_DOWNLOADED
                    ),
                    FeatureEntity(
                        FeatureSet.DEVICE_A,
                        FeatureStatus.DOWNLOADING
                    ),
                    FeatureEntity(
                        FeatureSet.DEVICE_B,
                        FeatureStatus.DOWNLOADED
                    ),
                )
            )
        }
    }
}