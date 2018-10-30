package com.neobis.israil.myapplicationfinal.ui.photos

import android.os.Bundle
import com.bumptech.glide.Glide
import com.neobis.israil.myapplicationfinal.R
import com.neobis.israil.myapplicationfinal.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_size_photo.*

class FullScreen : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_size_photo)

        toolbar?.setNavigationIcon(R.drawable.left_arrow)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val url = intent.getStringExtra("url")
        Glide.with(this).load(url).into(imageView)
    }
}