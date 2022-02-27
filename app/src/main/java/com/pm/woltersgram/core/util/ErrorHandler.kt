package com.pm.woltersgram.core.util

import android.content.Context
import com.pm.woltersgram.R
import com.pm.woltersgram.core.domain.error.ErrorCodes

object ErrorHandler {

    fun getErrorMessage(context: Context, errorCode: Int?): String {
        return when (errorCode) {
            ErrorCodes.ERROR_GENERAL -> context.getString(R.string.error_general)
            ErrorCodes.ERROR_NO_INTERNET -> context.getString(R.string.error_no_internet)
            ErrorCodes.ERROR_TIME_OUT -> context.getString(R.string.error_timeout)
            else -> context.getString(R.string.error_general)
        }
    }
}