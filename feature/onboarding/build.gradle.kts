plugins {
    id("featuredelivery.android.dynamic.feat.compose")
}
android {
    namespace = "com.frestoinc.sample.featuredelivery.feature.onboarding"
}

dependencies {
    implementation(project(":app"))
}