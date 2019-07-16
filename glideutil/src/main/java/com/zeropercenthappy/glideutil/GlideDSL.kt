package com.zeropercenthappy.glideutil

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.widget.ImageView

class GlideWrapper {
    var url: Any? = null
    var imageView: ImageView? = null
    var placeHolder: Any = Unit
    var errorHolder: Any = Unit
    var circleCrop: Boolean = false
}

@SuppressLint("CheckResult")
fun loadImage(init: GlideWrapper.() -> Unit) {
    val glideWrapper = GlideWrapper()
    glideWrapper.init()
    glideWrapper.apply {
        // imageView
        if (imageView == null) {
            return
        }
        // glideRequest
        val glideRequest = GlideApp.with(requireNotNull(imageView)).load(url)
        // placeHolder
        if (placeHolder is Int) {
            glideRequest.placeholder(placeHolder as Int)
        } else if (placeHolder is Drawable) {
            glideRequest.placeholder(placeHolder as Drawable)
        }
        // errorHolder
        if (errorHolder is Int) {
            glideRequest.error(errorHolder as Int)
        } else if (errorHolder is Drawable) {
            glideRequest.error(errorHolder as Drawable)
        }
        // circleCrop
        if (circleCrop) {
            glideRequest.circleCrop()
        }
        // load
        glideRequest.into(requireNotNull(imageView))
    }
}