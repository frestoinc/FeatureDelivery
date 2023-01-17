package com.frestoinc.sample.featuredelivery.core.domain.delivery.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class FeatureDeliveryNotification

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class FeatureDeliveryNotificationChannelIdString

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class FeatureDeliveryNotificationChannelIdInt

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class FeatureDeliveryNotificationChannel