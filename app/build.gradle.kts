plugins {
    id("featuredelivery.android.app.compose")
    id("featuredelivery.android.hilt")
    id("featuredelivery.firebase")
}

android {
    namespace = "com.frestoinc.sample.featuredelivery"
    dynamicFeatures += setOf(
        ":feature:onboarding",
        ":feature:devicea",
        ":feature:deviceb"
    )
}

dependencies {
    api(libs.androidx.hilt.navigation.compose)
    api(libs.androidx.lifecycle.runtimeCompose)
}