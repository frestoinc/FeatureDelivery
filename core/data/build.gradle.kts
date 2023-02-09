plugins {
    id("featuredelivery.android.lib")
    id("featuredelivery.android.hilt")
    id("featuredelivery.firebase")
}

android {
    namespace = "com.frestoinc.sample.featuredelivery.core.data"
}

dependencies {
    implementation(libs.datastore.preferences)
    api(libs.moshi.kotlin)
}