plugins {
    id("featuredelivery.android.app.compose")
    id("featuredelivery.android.hilt")
}

android {
    namespace = "com.frestoinc.sample.featuredelivery"
    dynamicFeatures += setOf(
        ":feature:introduction",
        ":feature:devicea",
        ":feature:deviceb"
    )
}

dependencies {
    api(project(":core:data"))
    api(project(":core:domain"))
    implementation(libs.androidx.hilt.navigation.compose)
}