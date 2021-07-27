package com.harismexis.creditscore.framework.extensions

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.res.ResourcesCompat

fun Context.getColorCompat(@ColorRes color: Int): Int  {
    return ResourcesCompat.getColor(this.resources, color, null)
}