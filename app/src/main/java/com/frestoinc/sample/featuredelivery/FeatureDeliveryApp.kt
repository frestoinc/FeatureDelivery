package com.frestoinc.sample.featuredelivery

import android.app.Application
import android.content.Context
import com.google.android.play.core.splitcompat.SplitCompat
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FeatureDeliveryApp : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        SplitCompat.install(this)
    }
}