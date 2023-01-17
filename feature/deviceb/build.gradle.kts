plugins {
    id("featuredelivery.android.dynamic.feat.compose")
}
android {
    namespace = "com.frestoinc.sample.featuredelivery.feature.deviceb"
}

dependencies {
    implementation(project(":app"))
}