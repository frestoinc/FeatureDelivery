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

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Project
import java.io.FileInputStream
import java.util.*

internal fun Project.configureAndroidApplication(
    extension: ApplicationExtension,
) {

    val keystoreProperties = Properties().apply {
        load(FileInputStream(rootProject.file("keystore.properties")))
    }

    extension.apply {
        defaultConfig {
            versionCode = APP_VERSION_BUILD
            versionName = APP_VERSION_CODE
            targetSdk = ANDROID_TARGET_SDK_VERSION
            setProperty("archivesBaseName", "${APP_VERSION_NAME}-v${versionName}(${versionCode})")
        }

        signingConfigs {
            create("release") {
                keyAlias = keystoreProperties["keyAlias"] as String
                keyPassword = keystoreProperties["keyPassword"] as String
                storeFile = file(keystoreProperties["storeFile"] as String)
                storePassword = keystoreProperties["storePassword"] as String
            }
        }

        buildTypes {
            release {
                isShrinkResources = true
                isMinifyEnabled = true
                isDebuggable = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
                signingConfig = signingConfigs.getByName("release")
            }
        }
    }
}