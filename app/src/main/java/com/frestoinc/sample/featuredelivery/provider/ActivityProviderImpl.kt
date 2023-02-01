package com.frestoinc.sample.featuredelivery.provider

import android.content.Context
import android.content.Intent
import com.frestoinc.sample.featuredelivery.MainActivity
import com.frestoinc.sample.featuredelivery.core.domain.provider.ActivityProvider

class ActivityProviderImpl(
    private val appContext: Context
) : ActivityProvider {

    override fun provideActivityIntent(): Intent =
        Intent(appContext, MainActivity::class.java)
}