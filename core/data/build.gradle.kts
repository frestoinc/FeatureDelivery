plugins {
    id("featuredelivery.android.lib")
    id("featuredelivery.android.hilt")
}

android {
    namespace = "com.frestoinc.sample.featuredelivery.core.data"
}

dependencies {
    implementation(libs.datastore.preferences)
    implementation(libs.moshi.kotlin)
}