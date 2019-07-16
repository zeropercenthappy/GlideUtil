package com.zeropercenthappy.glideutil

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import kotlin.concurrent.thread

object GlideUtils {

    @SuppressLint("CheckResult")
    @JvmOverloads
    @JvmStatic
    fun loadImage(url: Any, imageView: ImageView, placeHolder: Any? = null, errorHolder: Any? = null) {
        val glideRequest = GlideApp.with(imageView).load(url)
        if (placeHolder is Int) {
            glideRequest.placeholder(placeHolder)
        } else if (placeHolder is Drawable) {
            glideRequest.placeholder(placeHolder)
        }
        if (errorHolder is Int) {
            glideRequest.error(errorHolder)
        } else if (errorHolder is Drawable) {
            glideRequest.error(errorHolder)
        }
        glideRequest.into(imageView)
    }

    @JvmStatic
    fun clearCache(context: Context) {
        thread {
            GlideApp.get(context).clearDiskCache()
        }
    }
}