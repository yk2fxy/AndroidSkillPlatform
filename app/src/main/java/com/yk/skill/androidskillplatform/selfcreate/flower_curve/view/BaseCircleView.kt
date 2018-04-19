package com.yk.skill.androidskillplatform.selfcreate.flower_curve.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * Created by Administrator on 2018/3/13.
 *
 * 1.	创建一个自定义的view，这个view最基础的功能就是实现大齿轮和小齿轮的绘制；
 * 2.	view还需要实现小齿轮上运动点的产生
 * 3.	view还需要实现小齿轮在大齿轮上转动，然后子尺上运动点产生的轨迹的绘制，运动轨迹会知道大齿轮所在的view上
 */
class BaseCircleView : View {
    var circleTypeFlag:Int = 0//设置圆形的类型：0：空心圆  1：实心圆
    var cacheCanvas = Canvas() //存放一个缓冲的画布
    var cacheBmp:Bitmap
    constructor(context: Context?) : super(context){
        cacheBmp = Bitmap.createBitmap(800,800,Bitmap.Config.ALPHA_8)
        cacheCanvas.setBitmap(cacheBmp)
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        cacheBmp = Bitmap.createBitmap(800,800,Bitmap.Config.ALPHA_8)
        cacheCanvas.setBitmap(cacheBmp)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        cacheBmp = Bitmap.createBitmap(800,800,Bitmap.Config.ALPHA_8)
        cacheCanvas.setBitmap(cacheBmp)
    }

    fun drawPoint(){
        var paint = Paint()
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 50f
        cacheCanvas.drawPoint(20f,20f,paint)
    }
    fun drawCircle(){
        var paint = Paint()
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 50f
        cacheCanvas.drawCircle(200f,200f,100f,paint)
    }
    fun drawRect(){
        var paint = Paint()
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 50f
        cacheCanvas.drawRect(200f,200f,400f,400f,paint)
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawCircle()
        canvas?.drawBitmap(cacheBmp,0f,0f,Paint())
        drawRect()
        canvas?.drawBitmap(cacheBmp,0f,0f,Paint())
    }
}