package com.harismexis.creditscore.setup.util

import androidx.annotation.StringRes
import androidx.test.platform.app.InstrumentationRegistry

fun getString(@StringRes id: Int): String {
    return InstrumentationRegistry.getInstrumentation()
        .targetContext.getString(id)
}

fun getStringFormatted(@StringRes id: Int, toInsert: String): String {
    return InstrumentationRegistry.getInstrumentation()
        .targetContext.getString(id, toInsert)
}