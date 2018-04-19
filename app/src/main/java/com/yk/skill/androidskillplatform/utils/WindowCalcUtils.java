package com.yk.skill.androidskillplatform.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by Administrator on 2018/3/22.
 * 用于跟屏幕操作相关的工具，比如获取屏幕的高宽等
 */
public class WindowCalcUtils {
    public static int getWindowsWidth(Context context){
        //获取屏幕的高宽
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics out = new DisplayMetrics();
        display.getMetrics(out);
        int width = out.widthPixels;
        return width;
    }
    public static int getWindowsHeight(Context context){
        //获取屏幕的高宽
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics out = new DisplayMetrics();
        display.getMetrics(out);
        int height = out.heightPixels;
        return height;
    }
}
