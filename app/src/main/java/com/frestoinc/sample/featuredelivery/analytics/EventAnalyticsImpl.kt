package com.frestoinc.sample.featuredelivery.analytics

import com.frestoinc.sample.featuredelivery.core.data.BuildConfig
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventAnalyticsImpl @Inject constructor(
    private val analytics: FirebaseAnalytics,
    private val crashlytics: FirebaseCrashlytics
) : EventAnalytics {

    override fun logEvent(event: String, param: String) {
        if (BuildConfig.DEBUG) {
            Timber.e("$event: $param")
        } else {
            analytics.logEvent(event) {
                param(FirebaseAnalytics.Param.CONTENT, param)
            }
        }
    }

    override fun logCrash(exception: Exception) {
        if (BuildConfig.DEBUG) {
            Timber.e(exception)
        } else {
            crashlytics.recordException(exception)
        }

    }
}