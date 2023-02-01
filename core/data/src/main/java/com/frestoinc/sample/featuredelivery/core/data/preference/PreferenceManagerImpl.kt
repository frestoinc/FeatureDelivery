package com.frestoinc.sample.featuredelivery.core.data.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.frestoinc.sample.featuredelivery.core.data.serialize
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : PreferenceManager {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preference_pb")

    override val onBoardingEnabled: Flow<Boolean> =
        read(PreferenceConstants.ON_BOARDING_ENABLED, false)

    override suspend fun setOnBoardingEnabled(value: Boolean) {
        write(
            key = PreferenceConstants.ON_BOARDING_ENABLED,
            value = value
        )
    }

    override val deviceAEnabled: Flow<Boolean> =
        read(PreferenceConstants.DEVICE_A_ENABLED, false)

    override suspend fun setDeviceAEnabled(value: Boolean) {
        write(
            key = PreferenceConstants.DEVICE_A_ENABLED,
            value = value
        )
    }

    override val deviceBEnabled: Flow<Boolean> =
        read(PreferenceConstants.DEVICE_B_ENABLED, false)

    override suspend fun setDeviceBEnabled(value: Boolean) {
        write(
            key = PreferenceConstants.DEVICE_B_ENABLED,
            value = value
        )
    }

    private inline fun <reified T> read(key: Preferences.Key<T>, defaultValue: T): Flow<T> =
        context.dataStore.data.map { pref -> pref[key] ?: defaultValue }

    private suspend inline fun <reified T, reified U> write(
        key: Preferences.Key<T>,
        value: U
    ) = runCatching {
        context.dataStore.edit {
            it[key] = when (T::class) {
                String::class -> value.serialize()
                else -> value
            } as T
        }
    }
}