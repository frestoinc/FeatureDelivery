import java.io.FileInputStream
import java.util.*

plugins {
    id("featuredelivery.android.app.compose")
    id("featuredelivery.android.hilt")
}

val keystorePropertiesFile = rootProject.file("keystore.properties")
val keystoreProperties = Properties()
keystoreProperties.load(FileInputStream(keystorePropertiesFile))

android {
    namespace = "com.frestoinc.sample.featuredelivery"
    dynamicFeatures += setOf(
        ":feature:onboarding",
        ":feature:devicea",
        ":feature:deviceb"
    )

    signingConfigs {
        create("release") {
            keyAlias = keystoreProperties["keyAlias"] as String
            keyPassword = keystoreProperties["keyPassword"] as String
            storeFile = file(keystoreProperties["storeFile"] as String)
            storePassword = keystoreProperties["storePassword"] as String
        }
    }
}

dependencies {
    api(libs.androidx.hilt.navigation.compose)
    api(libs.androidx.lifecycle.runtimeCompose)
}