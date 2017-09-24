package com.yk.skill.androidskillplatform.webview

import android.content.Context
import android.webkit.JavascriptInterface
import android.widget.Toast

/**
 * Created by Administrator on 2017/9/24.
 */

class JavaScriptBridge(internal var context: Context) {
    @JavascriptInterface
    fun show(text:String) {
        Toast.makeText(context,text,Toast.LENGTH_LONG).show();
    }
}
