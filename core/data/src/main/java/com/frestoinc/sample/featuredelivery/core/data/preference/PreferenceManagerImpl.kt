package com.frestoinc.sample.featuredelivery.core.data.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.frestoinc.sample.featuredelivery.core.data.serialize
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : PreferenceManager {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preference_pb")

    override suspend fun isInstallTimeEnabled(): Boolean =
        extract(PreferenceConstants.INSTALL_TIME_ENABLED) ?: false

    override suspend fun setInstallTimeEnabled(value: Boolean) {
        write(
            key = PreferenceConstants.INSTALL_TIME_ENABLED,
            value = value
        )
    }

    override suspend fun isOnDemandEnabled(): Boolean =
        extract(PreferenceConstants.ON_DEMAND_ENABLED) ?: false

    override suspend fun setOnDemandEnabled(value: Boolean) {
        write(
            key = PreferenceConstants.ON_DEMAND_ENABLED,
            value = value
        )
    }

    private suspend fun <T> extract(key: Preferences.Key<T>): T? =
        runCatching {
            context.dataStore.data.first()[key]
        }.getOrNull()

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