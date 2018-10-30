package com.neobis.israil.myapplicationfinal.ui.posts

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.neobis.israil.myapplicationfinal.R
import com.neobis.israil.myapplicationfinal.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val url = "https://o.kg/ru/chastnym-klientam/"
        webView.loadUrl(url)
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                progressBar.visibility = View.GONE
                super.onPageFinished(view, url)
            }
        }
    }
}