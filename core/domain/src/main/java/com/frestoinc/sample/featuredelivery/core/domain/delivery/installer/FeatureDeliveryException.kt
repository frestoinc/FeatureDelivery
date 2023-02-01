package com.frestoinc.sample.featuredelivery.core.domain.delivery.installer

import com.frestoinc.sample.featuredelivery.core.domain.delivery.installer.FeatureDeliveryExceptionCode.Companion.byCode

/**
 *  int NO_ERROR = 0;
int ACTIVE_SESSIONS_LIMIT_EXCEEDED = -1;
int MODULE_UNAVAILABLE = -2;
int INVALID_REQUEST = -3;
int SESSION_NOT_FOUND = -4;
int API_NOT_AVAILABLE = -5;
int NETWORK_ERROR = -6;
int ACCESS_DENIED = -7;
int INCOMPATIBLE_WITH_EXISTING_SESSION = -8;
/** @deprecated */
@Deprecated
int SERVICE_DIED = -9;
int INSUFFICIENT_STORAGE = -10;
int SPLITCOMPAT_VERIFICATION_ERROR = -11;
int SPLITCOMPAT_EMULATION_ERROR = -12;
int SPLITCOMPAT_COPY_ERROR = -13;
int PLAY_STORE_NOT_FOUND = -14;
int APP_NOT_OWNED = -15;
int INTERNAL_ERROR = -100;
 */
class FeatureDeliveryException(
    errorCode: Int
) : Exception() {
    override val message = "Error returns ${errorCode.toErrorMessage()}"

    private fun Int.toErrorMessage(): String =
        this.byCode().name
}

enum class FeatureDeliveryExceptionCode(private val code: Int) {
    NO_ERROR(0),
    ACTIVE_SESSIONS_LIMIT_EXCEEDED(-1),
    MODULE_UNAVAILABLE(-2),
    INVALID_REQUEST(-3),
    SESSION_NOT_FOUND(-4),
    API_NOT_AVAILABLE(-5),
    NETWORK_ERROR(-6),
    ACCESS_DENIED(-7),
    INCOMPATIBLE_WITH_EXISTING_SESSION(-8),
    SERVICE_DIED(-9),
    INSUFFICIENT_STORAGE(-10),
    SPLITCOMPAT_VERIFICATION_ERROR(-11),
    SPLITCOMPAT_EMULATION_ERROR(-12),
    SPLITCOMPAT_COPY_ERROR(-13),
    PLAY_STORE_NOT_FOUND(-14),
    APP_NOT_OWNED(-15),
    INTERNAL_ERROR(-100);

    companion object {

        fun Int.byCode(): FeatureDeliveryExceptionCode =
            values().firstOrNull { it.code == this } ?: INTERNAL_ERROR
    }
}