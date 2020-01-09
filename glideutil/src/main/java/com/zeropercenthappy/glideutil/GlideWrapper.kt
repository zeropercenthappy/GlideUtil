package com.zeropercenthappy.glideutil

import android.widget.ImageView
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.TransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import java.io.File

class GlideWrapper<T> {
    var url: Any? = null
    var imageView: ImageView? = null
    var requestOptions: RequestOptions? = null
    var transitionOptions: TransitionOptions<*, T>? = null
    var thumbnail: RequestBuilder<T>? = null
    var errorBuilder: RequestBuilder<T>? = null
    var requestListener: RequestListener<T>? = null
    var storageFile: File? = null
}