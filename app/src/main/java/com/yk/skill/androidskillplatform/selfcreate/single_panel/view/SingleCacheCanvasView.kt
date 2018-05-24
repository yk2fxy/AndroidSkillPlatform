package com.yk.skill.androidskillplatform.selfcreate.single_panel.view

import android.content.Context
import android.graphics.*
import android.os.Environment
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.PopupWindow
import com.yk.skill.androidskillplatform.selfcreate.single_panel.listener.CheckIsSingleListener
import java.io.File
import java.io.FileOutputStream
/**
 * Created by Administrator on 2018/3/15.
 */
class SingleCacheCanvasView : View {
    var cacheBmpWidth = 0
    var cacheBmpHeight = 0
    var path:Path = Path()//触摸产生的路径
    var cacheBmp:Bitmap? = null//缓冲canvas的bitmap
    var cacheCanvas: Canvas? = null//缓冲canvas
    var cachePaint:Paint? = null//缓冲canvas画轨迹时的所使用画笔
    private lateinit var checkIsLiSingleLister: CheckIsSingleListener//检查是否正在签字的回调
    constructor(context: Context?) : super(context){
        initView(context,null,0)
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        initView(context,attrs,0)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        initView(context,attrs,defStyleAttr)
    }
    fun buildWidth(width:Int):SingleCacheCanvasView{
        this.cacheBmpWidth = width
        return this
    }
    fun buildHeight(height:Int):SingleCacheCanvasView{
        this.cacheBmpHeight = height
        return this
    }
    /**
     * 初始化一些参数，比如缓冲画笔、画布canvas、bitmap
     */
    private fun initView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) {
        cachePaint = Paint()
        cachePaint?.strokeWidth = 10f
        cachePaint?.color = Color.BLUE
        cachePaint?.style = Paint.Style.STROKE
    }
    /**
     * 在canvas上绘制view展现出来的轨迹
     */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var paint = Paint()
        paint.strokeWidth = 10f
        paint.style = Paint.Style.STROKE
        paint.color = Color.GREEN
        canvas?.drawBitmap(cacheBmp,0f,0f,paint)
    }
    //保存轨迹图片
    fun saveBmp(){
        var out = FileOutputStream(Environment.getExternalStorageDirectory().absoluteFile.toString()+File.separator+"test.png")
        cacheBmp?.compress(Bitmap.CompressFormat.PNG,0,out)
    }
    //清除轨迹，思路---重新创建缓冲画布，以及将path路径重置
    fun clearBmp(){
        cacheBmp = Bitmap.createBitmap(cacheBmpWidth,cacheBmpHeight,Bitmap.Config.ARGB_8888)
        cacheBmp?.eraseColor(Color.WHITE)
        cacheCanvas = Canvas(cacheBmp)
        var paint = Paint()
        paint.color = Color.WHITE
        paint.style = Paint.Style.FILL
        cacheCanvas?.drawRect(0f,0f,cacheBmpWidth.toFloat(),cacheBmpHeight.toFloat(),paint)
        path.reset()
        invalidate()
    }
    /**
     * 不是必须的，这里是设置缓冲canvas的大小（设置的bitmap位图大小就是canvas的大小）
     * 如果必须计算view的大小才能设置缓冲canvas的大小，建议把将缓冲canvas的bitmap放在这初始化，并将bitmap设置到缓冲canvas上，因为initView方法在onMeasure方法前执行
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        cacheBmp = Bitmap.createBitmap(cacheBmpWidth,cacheBmpHeight,Bitmap.Config.ARGB_8888)
        cacheBmp?.eraseColor(Color.WHITE)
        cacheCanvas = Canvas(cacheBmp)
        var paint = Paint()
        paint.color = Color.WHITE
        paint.style = Paint.Style.FILL
        cacheCanvas?.drawRect(0f,0f,cacheBmpWidth.toFloat(),cacheBmpHeight.toFloat(),paint)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
    //触摸事件产生的轨迹绘制
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var x = event?.x
        var y = event?.y
        when(event?.action){
            MotionEvent.ACTION_DOWN->{
                x = event?.x
                y = event?.y
                path.moveTo(x!!,y!!)
                //手指按下，确定为开始签字，开始签字是把按钮隐藏
                checkIsLiSingleLister.startSingle()
            }
            MotionEvent.ACTION_MOVE->{
                x = event?.x
                y = event?.y
                path.lineTo(x!!,y!!)
            }
            MotionEvent.ACTION_UP->{
                x = event?.x
                y = event?.y
                path.lineTo(x!!,y!!)
                //手指抬起，确定为结束签字，结束签字把按钮显示出来
                checkIsLiSingleLister.endSingle()
            }
        }
        cacheCanvas?.drawPath(path,cachePaint)
        invalidate()
        return true
    }
    //
    fun addCheckIsSingleListener(listener: CheckIsSingleListener){
        this.checkIsLiSingleLister = listener
    }
}