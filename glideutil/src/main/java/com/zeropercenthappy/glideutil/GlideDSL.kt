package com.zeropercenthappy.glideutil

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.TransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.zeropercenthappy.utilslibrary.utils.FileUtils
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.File
import kotlin.coroutines.resume

class GlideWrapper<T> {
    var context: Context? = null
    var url: Any? = null
    var imageView: ImageView? = null
    var requestOptions: RequestOptions? = null
    var transitionOptions: TransitionOptions<*, T>? = null
    var thumbnail: RequestBuilder<T>? = null
    var errorBuilder: RequestBuilder<T>? = null
    var requestListener: RequestListener<T>? = null
    var storageFile: File? = null
}

/**
 * load image into ImageView
 *
 * required params:
 *
 * - context
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
        if (context == null || imageView == null) {
            return
        }
        // request builder
        val glideRequest = Glide.with(requireNotNull(context)).asDrawable().load(url)
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
 * - context
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
        if (context == null || imageView == null) {
            return
        }
        // request builder
        val glideRequest = Glide.with(requireNotNull(context)).asBitmap().load(url)
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
suspend fun downloadImage(init: GlideWrapper<File>.() -> Unit): Boolean = suspendCancellableCoroutine { continuation ->
    val glideWrapper = GlideWrapper<File>()
    glideWrapper.init()
    glideWrapper.apply {
        if (context == null || storageFile == null) {
            continuation.resume(false)
            return@suspendCancellableCoroutine
        }
        // request builder
        val glideRequest = Glide.with(requireNotNull(context)).asFile().load(url)
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