package com.frestoinc.sample.featuredelivery.core.domain.provider

import android.content.Intent

interface ActivityProvider {
    fun provideActivityIntent(): Intent
}
