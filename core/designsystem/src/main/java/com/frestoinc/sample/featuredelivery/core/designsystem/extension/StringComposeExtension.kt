package com.frestoinc.sample.featuredelivery.core.designsystem.extension

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class BaseLocaleText {
    data class FromString(val value: String) : BaseLocaleText()
    class FromResource(
        @StringRes val id: Int,
        vararg val args: Any
    ) : BaseLocaleText()

    @Composable
    fun asString(): String =
        when (this) {
            is FromString -> value
            is FromResource -> stringResource(id, args)
        }

    override fun toString(): String =
        when (this) {
            is FromString -> value
            is FromResource -> id.toString()
        }
}