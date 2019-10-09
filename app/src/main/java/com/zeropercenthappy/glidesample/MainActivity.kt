package com.zeropercenthappy.glidesample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.zeropercenthappy.glideutil.downloadImage
import com.zeropercenthappy.glideutil.loadImage
import com.zeropercenthappy.utilslibrary.utils.CacheUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val mainScope = MainScope()
    //    private val imageUrl = "https://imgs.aixifan.com/2fJC0sJcLK-BZRZfa-Y3iqEz-Q7jmEv-6Rrmmq.jpg"
    private val imageUrl = "https://imgs.aixifan.com/AcFccYdFRE-ZbAB7n-n2iymm-aEBFbu-mqIRni.gif"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        load()
        download()
    }

    private fun load() {
        // options
        val options = RequestOptions().apply {
            placeholder(R.mipmap.ic_launcher)
            error(R.mipmap.ic_launcher_round)
            centerInside()
        }
        // load
        loadImage {
            context = this@MainActivity
            imageView = iv
            url = imageUrl
            requestOptions = options
            transitionOptions = DrawableTransitionOptions.withCrossFade(1500)
        }
    }

    private fun download() = mainScope.launch {
        val file = CacheUtils.createFormatedCacheFile(this@MainActivity, "gif") ?: return@launch
        val downloadResult = downloadImage {
            context = this@MainActivity
            url = imageUrl
            storageFile = file
        }
        val log = StringBuilder()
        log.append("下载")
        if (downloadResult) {
            log.append("成功")
        } else {
            log.append("失败")
        }
        log.append("\n").append("路径：").append(file.absolutePath)
        tv.text = log.toString()
    }

}
