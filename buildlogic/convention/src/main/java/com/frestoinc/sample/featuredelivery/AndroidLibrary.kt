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

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project

@Suppress("UnstableApiUsage")
internal fun Project.configureAndroidLibrary(
    extension: LibraryExtension,
) {
    extension.apply {
        defaultConfig {
            targetSdk = ANDROID_TARGET_SDK_VERSION
            consumerProguardFiles("consumer-rules.pro")
        }

        buildTypes {
            release {
                isMinifyEnabled = true
                consumerProguardFile("consumer-rules.pro")
            }
        }
    }
}