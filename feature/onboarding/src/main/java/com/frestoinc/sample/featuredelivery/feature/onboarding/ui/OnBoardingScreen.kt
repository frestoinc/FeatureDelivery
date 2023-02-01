package com.frestoinc.sample.featuredelivery.feature.onboarding.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        /* if (featureList.isEmpty()) {
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
                 *//*items(featureList) { entity ->
                    FeatureBtn(
                        modifier = modifier,
                        entity = entity,
                        onClick = onFeatureClick
                    )
                }*//*
            }
        }*/

    }
}

/*
@Composable
internal fun FeatureBtn(
    modifier: Modifier = Modifier,
    entity: FeatureEntity,
    onClick: (FeatureStatus) -> Unit = {}
) {
    Button(
        modifier = modifier,
        onClick = { onClick(entity.status) }
    ) {
        Text(text = "Status of ${entity.set.title}: ${entity.status.name}")
    }
}

@Preview("default", showBackground = true)
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun OnBoardingScreenEmptyPreview() {
    MyApplicationTheme {
        Scaffold {
            OnBoardingScreen(
                modifier = Modifier.padding(it)
            )
        }
    }
}

@Preview("default", showBackground = true)
@Preview("dark theme", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun OnBoardingScreenListPreview() {
    MyApplicationTheme {
        Scaffold {
            OnBoardingScreen(
                modifier = Modifier.padding(it),
                featureList = listOf(
                    FeatureEntity(
                        FeatureSet.OnBoarding,
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
}*/
