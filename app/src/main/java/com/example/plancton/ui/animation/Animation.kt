package com.example.plancton.ui.animation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View

fun View.shake(offsetPercent: Float = 0.05f, beatDuration: Long = 100) {
    val offset = this.width * offsetPercent
    val right = ObjectAnimator.ofFloat(this, View.TRANSLATION_X, offset)
        .setDuration(beatDuration)
    val left = ObjectAnimator.ofFloat(this, View.TRANSLATION_X, -offset)
        .setDuration(beatDuration)
    val center = ObjectAnimator.ofFloat(this, View.TRANSLATION_X, 0f)
        .setDuration(beatDuration)
    val animSet = AnimatorSet()
    animSet
        .play(left)
        .before(center)
        .after(right)
    animSet
        .start()
}