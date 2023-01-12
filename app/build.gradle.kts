plugins {
    id("featuredelivery.android.app")
    id("featuredelivery.android.app.compose")
}

android {
    namespace = "com.frestoinc.sample.featuredelivery"
}

dependencies {
    implementation(libs.androidx.activity.compose)

    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.extended)

    debugImplementation(libs.androidx.compose.ui.tooling)
}