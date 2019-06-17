package com.zeropercenthappy.glideutil

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import java.io.File
import kotlin.concurrent.thread

object GlideUtils {

    @JvmStatic
    fun loadImage(context: Context, url: String, imageView: ImageView) {
        GlideApp.with(context)
            .load(url)
            .into(imageView)
    }

    @JvmStatic
    fun loadImage(context: Context, url: String, imageView: ImageView, placeholder: Int) {
        GlideApp.with(context)
            .load(url)
            .placeholder(placeholder)
            .error(placeholder)
            .into(imageView)
    }

    @JvmStatic
    fun loadImage(context: Context, url: String, imageView: ImageView, placeholder: Drawable) {
        GlideApp.with(context)
            .load(url)
            .placeholder(placeholder)
            .error(placeholder)
            .into(imageView)
    }

    @JvmStatic
    fun loadImage(context: Context, file: File, imageView: ImageView) {
        GlideApp.with(context)
            .load(file)
            .into(imageView)
    }

    @JvmStatic
    fun loadImage(context: Context, file: File, imageView: ImageView, placeholder: Int) {
        GlideApp.with(context)
            .load(file)
            .placeholder(placeholder)
            .error(placeholder)
            .into(imageView)
    }

    @JvmStatic
    fun loadImage(context: Context, file: File, imageView: ImageView, placeholder: Drawable) {
        GlideApp.with(context)
            .load(file)
            .placeholder(placeholder)
            .error(placeholder)
            .into(imageView)
    }

    @JvmStatic
    fun loadImage(context: Context, res: Int, imageView: ImageView) {
        GlideApp.with(context)
            .load(res)
            .into(imageView)
    }

    @JvmStatic
    fun loadImage(context: Context, res: Int, imageView: ImageView, placeholder: Int) {
        GlideApp.with(context)
            .load(res)
            .placeholder(placeholder)
            .error(placeholder)
            .into(imageView)
    }

    @JvmStatic
    fun loadImage(context: Context, res: Int, imageView: ImageView, placeholder: Drawable) {
        GlideApp.with(context)
            .load(res)
            .placeholder(placeholder)
            .error(placeholder)
            .into(imageView)
    }

    @JvmStatic
    fun clearCache(context: Context) {
        thread {
            GlideApp.get(context).clearDiskCache()
        }
    }
}