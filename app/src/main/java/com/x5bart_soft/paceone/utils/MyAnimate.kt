package com.x5bart_soft.paceone.utils

import android.graphics.drawable.Animatable2
import android.graphics.drawable.Drawable
import android.widget.ImageView

class MyAnimate {
    fun animate(view: ImageView) {
        val image = (view.background as Animatable2)
        image.registerAnimationCallback(object : Animatable2.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable?) {
                image.start()
            }
        })
        image.start()
    }

}