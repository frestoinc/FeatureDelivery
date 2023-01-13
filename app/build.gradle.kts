plugins {
    id("featuredelivery.android.app.compose")
    id("featuredelivery.android.hilt")
}

android {
    namespace = "com.frestoinc.sample.featuredelivery"
    dynamicFeatures += setOf(":feature:introduction")
}

dependencies {
    implementation(libs.androidx.activity.compose)
}