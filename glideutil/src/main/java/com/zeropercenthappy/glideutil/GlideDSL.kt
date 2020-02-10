package com.zeropercenthappy.glideutil

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.zeropercenthappy.utilslibrary.utils.FileUtils
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.File
import kotlin.coroutines.resume

/**
 * load image into ImageView
 *
 * required params:
 *
 * - imageView
 *
 * - url
 *
 * option params:
 *
 * - requestOptions
 *
 * - transitionOptions
 *
 * - thumbnail
 *
 * - errorBuilder
 *
 * - requestListener
 */
fun loadImage(init: GlideWrapper<Drawable>.() -> Unit) {
    val glideWrapper = GlideWrapper<Drawable>()
    glideWrapper.init()
    glideWrapper.apply {
        if (imageView == null) {
            return
        }
        // request builder
        val glideRequest = Glide.with(requireNotNull(imageView)).asDrawable().load(url)
        // request options
        if (requestOptions != null) {
            glideRequest.apply(requireNotNull(requestOptions))
        }
        // transition options
        if (transitionOptions != null) {
            glideRequest.transition(requireNotNull(transitionOptions))
        }
        // thumbnail
        if (thumbnail != null) {
            glideRequest.thumbnail(thumbnail)
        }
        // error builder
        if (errorBuilder != null) {
            glideRequest.error(errorBuilder)
        }
        // request listener
        if (requestListener != null) {
            glideRequest.listener(requestListener)
        }
        // load
        glideRequest.into(requireNotNull(imageView))
    }
}

/**
 * load image into ImageView
 *
 * required params:
 *
 * - imageView
 *
 * - url
 *
 * option params:
 *
 * - requestOptions
 *
 * - transitionOptions
 *
 * - thumbnail
 *
 * - errorBuilder
 *
 * - requestListener
 */
fun loadImageWithBitmap(init: GlideWrapper<Bitmap>.() -> Unit) {
    val glideWrapper = GlideWrapper<Bitmap>()
    glideWrapper.init()
    glideWrapper.apply {
        if (imageView == null) {
            return
        }
        // request builder
        val glideRequest = Glide.with(requireNotNull(imageView)).asBitmap().load(url)
        // request options
        if (requestOptions != null) {
            glideRequest.apply(requireNotNull(requestOptions))
        }
        // transition options
        if (transitionOptions != null) {
            glideRequest.transition(requireNotNull(transitionOptions))
        }
        // thumbnail
        if (thumbnail != null) {
            glideRequest.thumbnail(thumbnail)
        }
        // error builder
        if (errorBuilder != null) {
            glideRequest.error(errorBuilder)
        }
        // request listener
        if (requestListener != null) {
            glideRequest.listener(requestListener)
        }
        // load
        glideRequest.into(requireNotNull(imageView))
    }
}

/**
 * download image file
 *
 * required param:
 *
 * - context
 *
 * - storageFile
 *
 * - url
 *
 * option param:
 *
 * - requestListener
 */
suspend fun Context.downloadImage(init: GlideWrapper<File>.() -> Unit): Boolean =
    suspendCancellableCoroutine { continuation ->
        val glideWrapper = GlideWrapper<File>()
        glideWrapper.init()
        glideWrapper.apply {
            if (storageFile == null) {
                continuation.resume(false)
                return@suspendCancellableCoroutine
            }
            // request builder
            val glideRequest = Glide.with(this@downloadImage).asFile().load(url)
            // request listener
            if (requestListener != null) {
                glideRequest.listener(requestListener)
            }
            // download
            glideRequest.into(object : CustomTarget<File>() {
                override fun onLoadCleared(placeholder: Drawable?) {

                }

                override fun onLoadFailed(errorDrawable: Drawable?) {
                    continuation.resume(false)
                }

                override fun onResourceReady(resource: File, transition: Transition<in File>?) {
                    val result = FileUtils.copyFile(resource, requireNotNull(storageFile))
                    continuation.resume(result)
                }
            })
        }
    }