plugins {
    id("featuredelivery.android.lib")
    id("featuredelivery.android.hilt")
}

android {
    namespace = "com.frestoinc.sample.featuredelivery.core.domain"
}

dependencies {
    api(libs.play.featuredelivery)
    api(libs.play.featuredelivery.ktx)
}