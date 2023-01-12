plugins {
    id("featuredelivery.android.dynamic.feature")
}
android {
    namespace = "com.frestoinc.sample.featuredelivery.feature.introduction"

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(project(":app"))
}