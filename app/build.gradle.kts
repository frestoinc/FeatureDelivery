plugins {
    id("featuredelivery.android.app")
}

android {
    namespace = "com.frestoinc.sample.featuredelivery"
    dynamicFeatures += setOf(":feature:introduction")
}

dependencies {
    implementation(libs.androidx.activity.compose)

    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.extended)

    debugImplementation(libs.androidx.compose.ui.tooling)
}