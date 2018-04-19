package com.yk.skill.androidskillplatform.selfcreate.single_panel;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.yk.skill.androidskillplatform.selfcreate.single_panel.view.SingleCacheCanvasView;
import com.yk.skill.androidskillplatform.selfcreate.single_panel.view.SinglePanelContainer;
import com.yk.skill.androidskillplatform.utils.WindowCalcUtils;

/**
 * Created by Administrator on 2018/3/18.
 */

public class SinglePanelWindow {

    private PopupWindow pw;
    private View view;

    public SinglePanelWindow create(Context context, View view){
        this.view = view;
        //获取屏幕的高宽
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics out = new DisplayMetrics();
        display.getMetrics(out);
        int width = WindowCalcUtils.getWindowsWidth(context);
        int height = WindowCalcUtils.getWindowsHeight(context)/2;
        //用popupwindow装载一个签字的自定义view
        pw = new PopupWindow(context);
        pw.setContentView(new SinglePanelContainer(context));
        this.setWidth(width);
        this.setHeight(height);
        this.showLoacation(Gravity.CENTER,0,0);
        return this;
    }
    //设置签字面板的宽，重新增加一个函数是为了增加可扩展性
    public SinglePanelWindow setWidth(int width){
        pw.setWidth(width);
        return this;
    }
    //设置签字面板的高，重新增加一个函数是为了增加可扩展性
    public SinglePanelWindow setHeight(int height){
        pw.setWidth(height);
        return this;
    }
    //设置签字面板的显示位置，重新增加一个函数是为了增加可扩展性
    public SinglePanelWindow showLoacation(int gravity,int x,int y){
        pw.showAtLocation(view,gravity,x,y);
        return this;
    }
}
