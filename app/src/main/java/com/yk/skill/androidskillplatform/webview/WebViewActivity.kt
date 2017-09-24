package com.yk.skill.androidskillplatform.webview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

import com.yk.skill.androidskillplatform.R
import kotlinx.android.synthetic.main.activity_web_view.*
import java.net.URL

class WebViewActivity : AppCompatActivity() {
    open val TAG = WebViewActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        var webSetting = webview_container.settings;
        var client = MyClient();
        webview_container.loadUrl("file:///android_asset/test.html");
        webSetting.javaScriptEnabled = true;
        webview_container.addJavascriptInterface(JavaScriptBridge(this),"bridge")
        webview_container.setWebViewClient(client)

    }
    class MyClient: WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url)
            return true
        }
    }
     fun log(text:String){
        Log.i(TAG,text)
    }
}
