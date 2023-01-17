plugins {
    id("featuredelivery.android.dynamic.feat.compose")
}
android {
    namespace = "com.frestoinc.sample.featuredelivery.feature.introduction"
}

dependencies {
    implementation(project(":app"))
}