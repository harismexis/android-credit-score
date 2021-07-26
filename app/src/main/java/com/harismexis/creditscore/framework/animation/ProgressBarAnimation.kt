package com.harismexis.creditscore.framework.animation

import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ProgressBar
import android.widget.TextView
import java.lang.ref.WeakReference

class ProgressBarAnimation(
    private val progressBar: WeakReference<ProgressBar>,
    private val textView: WeakReference<TextView>,
    private val from: Float,
    private val to: Float
) : Animation() {

    override fun applyTransformation(
        interpolatedTime: Float,
        trans: Transformation
    ) {
        super.applyTransformation(interpolatedTime, trans)
        val value = (from + (to - from) * interpolatedTime).toInt()
        progressBar.get()?.progress = value
        textView.get()?.text = value.toString()
    }

}