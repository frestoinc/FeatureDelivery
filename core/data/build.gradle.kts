plugins {
    id("featuredelivery.android.lib")
}

android {
    namespace = "com.frestoinc.sample.featuredelivery.core.data"
}

dependencies {

    implementation(libs.androidx.datastore)
}