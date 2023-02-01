package com.frestoinc.sample.featuredelivery.feature.deviceb.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun DeviceBScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Text(text = "Hi im Device B Screen")
}