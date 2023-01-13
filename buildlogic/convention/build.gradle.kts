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

plugins {
    `kotlin-dsl`
}

group = "com.frestoinc.sample.featuredelivery.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "featuredelivery.android.app"
            implementationClass = "AndroidAppPlugin"
        }
        register("androidLibrary") {
            id = "featuredelivery.android.lib"
            implementationClass = "AndroidLibPlugin"
        }
        register("androidLibCompose") {
            id = "featuredelivery.android.lib.compose"
            implementationClass = "AndroidLibComposePlugin"
        }
        register("androidDynamicFeat") {
            id = "featuredelivery.android.dynamic.feat"
            implementationClass = "AndroidDynamicFeatPlugin"
        }
        register("androidDynamicFeatCompose") {
            id = "featuredelivery.android.dynamic.feat.compose"
            implementationClass = "AndroidDynamicFeatComposePlugin"
        }
        register("androidHilt") {
            id = "featuredelivery.android.hilt"
            implementationClass = "AndroidHiltPlugin"
        }
    }
}
