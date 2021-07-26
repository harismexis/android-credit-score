package com.harismexis.creditscore.framework.extensions

fun Throwable.msg(): String {
    var errorMsg = "$this, null error message"
    this.message?.let {
        errorMsg = it
    }
    return errorMsg
}
