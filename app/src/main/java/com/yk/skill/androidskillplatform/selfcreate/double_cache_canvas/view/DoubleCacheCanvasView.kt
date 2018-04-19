package com.yk.skill.androidskillplatform.selfcreate.double_cache_canvas.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * Created by Administrator on 2018/3/23.
 * 双缓冲画布的实现
 */
class DoubleCacheCanvasView:View {
    var cacheBmp:Bitmap? = null
    var cachePaint:Paint? = null
    var cachaCanvas:Canvas? = null
    var cachePaintStyle:Int = 0//用于设置缓冲画布的画笔颜色的判断标志
    constructor(context: Context?) : super(context){
        initView(context,null,0)
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        initView(context,attrs,0)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        initView(context,attrs,defStyleAttr)
    }
    private fun initView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) {

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN->{}
            MotionEvent.ACTION_MOVE->{}
            MotionEvent.ACTION_UP->{}
        }
        return super.onTouchEvent(event)
    }
}