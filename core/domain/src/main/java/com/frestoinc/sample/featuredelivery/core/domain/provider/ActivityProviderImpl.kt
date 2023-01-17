package com.frestoinc.sample.featuredelivery.core.domain.provider

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity

class ActivityProviderImpl(
    private val context: Context,
    private val activity: ComponentActivity
) : ActivityProvider {
    override fun provideActivityIntent(): Intent =
        Intent(context, activity::class.java)

}
