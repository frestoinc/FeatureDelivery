/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.frestoinc.sample.featuredelivery

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import java.io.File

internal fun Project.buildComposeMetricsParameters(): List<String> {
    val metricParameters = mutableListOf<String>()
    val enableMetricsProvider = project.providers.gradleProperty("enableComposeCompilerMetrics")
    val enableMetrics = (enableMetricsProvider.orNull == "true")
    if (enableMetrics) {
        val metricsFolder = File(project.buildDir, "compose-metrics")
        metricParameters.add("-P")
        metricParameters.add(
            "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" + metricsFolder.absolutePath
        )
    }

    val enableReportsProvider = project.providers.gradleProperty("enableComposeCompilerReports")
    val enableReports = (enableReportsProvider.orNull == "true")
    if (enableReports) {
        val reportsFolder = File(project.buildDir, "compose-reports")
        metricParameters.add("-P")
        metricParameters.add(
            "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" + reportsFolder.absolutePath
        )
    }
    metricParameters.addAll(
        listOf(
            "-opt-in=androidx.compose.material.ExperimentalMaterialApi",
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
            "-opt-in=androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi",
            "-opt-in=androidx.paging.ExperimentalPagingApi",
            "-opt-in=androidx.compose.ui.ExperimentalComposeUiApi",
            "-opt-in=androidx.compose.foundation.layout.ExperimentalLayoutApi"
        )

    )
    return metricParameters.toList()
}

internal fun CommonExtension<*, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}

internal fun Project.getLibsExtension() = extensions
    .getByType<VersionCatalogsExtension>()
    .named("libs")

internal fun Project.plugin(key: String): String = getLibsExtension()
    .findPlugin(key)
    .get()
    .get()
    .pluginId


internal val Project.ID_ANDROID_APPLICATION get() = plugin("android.application")
internal val Project.ID_ANDROID_LIBRARY get() = plugin("android.library")
internal val Project.ID_KOTLIN_ANDROID get() = plugin("kotlin.android")
internal val Project.ID_KOTLIN_KAPT get() = plugin("kotlin.kapt")
internal val Project.ID_HILT_ANDROID get() = plugin("hilt.android")

internal fun Project.version(key: String): String = getLibsExtension()
    .findVersion(key)
    .get()
    .toString()


internal fun Project.versionInt(key: String): Int = version(key).toInt()

internal val Project.COMPOSE_VERSION get() = version("androidxComposeCompiler")
internal val Project.ANDROID_COMPILE_SDK_VERSION get() = versionInt("android.compileSdk")
internal val Project.ANDROID_MIN_SDK_VERSION get() = versionInt("android.minSdk")
internal val Project.ANDROID_TARGET_SDK_VERSION get() = ANDROID_COMPILE_SDK_VERSION

internal fun Project.library(key: String): String = getLibsExtension()
    .findLibrary(key)
    .get()
    .get()
    .toString()

internal val Project.LIBRARY_HILT_ANDROID
    get() = library("hilt-android")
internal val Project.LIBRARY_HILT_KAPT
    get() = library("hilt-compiler")
internal val Project.LIBRARY_HILT_KAPT_TEST
    get() = library("hilt-compiler")
internal val Project.LIBRARY_TIMBER
    get() = library("timber")
internal val Project.LIBRARY_JUNIT
    get() = library("junit4")
internal val Project.LIBRARY_JUNIT_ESPRESSO
    get() = listOf(
        library("androidx-test-junit"),
        library("androidx-test-espresso-core")
    )
internal val Project.LIBRARY_COMPOSE_BOM
    get() = library("androidx-compose-bom")