package com.frestoinc.sample.featuredelivery.core.domain.network

import kotlinx.coroutines.flow.Flow

interface NetworkMonitor {
    val isOnline: Flow<Boolean>
}