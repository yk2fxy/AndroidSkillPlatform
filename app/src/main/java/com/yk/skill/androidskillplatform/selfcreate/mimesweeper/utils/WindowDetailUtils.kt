package com.yk.skill.androidskillplatform.selfcreate.mimesweeper.utils

import android.content.Context
import android.util.DisplayMetrics
import android.view.Display
import android.view.WindowManager

/**
 * Created by Administrator on 2018/2/4.
 */
object WindowDetailUtils {
    fun getWindowWidth(context:Context):Float{
        var wm:WindowManager= context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        var diaplay:Display = wm.defaultDisplay
        var out = DisplayMetrics()
        diaplay.getRealMetrics(out)
        return out.widthPixels.toFloat()
    }
    fun getWindowHeight(context:Context):Float{
        var wm:WindowManager= context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        var diaplay:Display = wm.defaultDisplay
        var out = DisplayMetrics()
        diaplay.getRealMetrics(out)
        return out.heightPixels.toFloat()
    }
}