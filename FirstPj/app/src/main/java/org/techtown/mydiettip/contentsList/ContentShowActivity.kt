package org.techtown.mydiettip.contentsList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Toast
import org.techtown.mydiettip.R

class ContentShowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_show)
        val getUrl = intent.getStringExtra("url")
        val webView : WebView = findViewById(R.id.webview)
        webView.loadUrl(getUrl.toString())
    }
}