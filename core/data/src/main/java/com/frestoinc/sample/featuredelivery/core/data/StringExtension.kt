package com.frestoinc.sample.featuredelivery.core.data

import com.squareup.moshi.Moshi

inline fun String?.ifNullOrEmpty(defaultValue: () -> String): String =
    if (isNullOrEmpty()) defaultValue() else this

inline fun <reified T> String.toModel(): T? =
    runCatching {
        Moshi.Builder().build().adapter(T::class.java).fromJson(this)
    }.getOrNull()

inline fun <reified T> T.serialize(): String =
    runCatching {
        Moshi.Builder().build().adapter(T::class.java).toJson(this)
    }.getOrNull() ?: ""

