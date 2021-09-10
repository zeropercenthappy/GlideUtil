package com.zeropercenthappy.glidesample

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
            placeholder(ColorDrawable(resources.getColor(android.R.color.holo_green_dark)))
            error(ColorDrawable(resources.getColor(android.R.color.holo_red_dark)))
            centerInside()
        }
        // load
        loadImage {
            imageView = iv
            url = imageUrl
            requestOptions = options
        }
    }

    private fun download() = mainScope.launch {
        val file = CacheUtils.createFormatCacheFile(this@MainActivity, "gif", true) ?: return@launch
        val downloadResult = downloadImage {
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
