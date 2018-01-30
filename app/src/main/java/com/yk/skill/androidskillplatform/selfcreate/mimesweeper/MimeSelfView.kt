package com.yk.skill.androidskillplatform.selfcreate.mimesweeper

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager

import com.yk.skill.androidskillplatform.R
import java.util.*

/**
 * TODO: document your custom view class.
 */
class MimeSelfView : View {
    /**
    1.获取列数和行数，然后通过列数和行数将view进行拆分；
    2.定义三种画笔，分别用来定义---初始颜色（未挖颜色）R、标记为地雷颜色G、已挖颜色B
     */
    private lateinit var mInitPaint: Paint//初始颜色的画笔
    private lateinit var mMarkPaint: Paint //标记为地雷颜色的画笔
    private lateinit var mNormalPaint: Paint//已挖颜色的画笔
    private var mColNUm: Int = 10//列数
    private var mRowNUm: Int = 10//行数
    private var mWidth: Int = 0//屏幕宽度
    private var mHeight: Int = 0//屏幕高度
    private var mFieldWidth: Int = 0//地雷区域宽度
    private var mFieldHeight: Int = 0//地雷区域高度
    private lateinit var mimeArray: Array<IntArray>

    constructor(context: Context) : super(context) {
        init(null, 0, context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0, context)

    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs, defStyle, context)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var wMode = MeasureSpec.getMode(widthMeasureSpec)
        var hMode = MeasureSpec.getMode(heightMeasureSpec)
        /*when(wMode){
            MeasureSpec.AT_MOST->mWidth = MeasureSpec.getSize(widthMeasureSpec)
            MeasureSpec.EXACTLY->mHeight = MeasureSpec.getSize(widthMeasureSpec)
        }*/
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int, context: Context) {
        var ta: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.MimeSelfView)
        mColNUm = ta.getInt(R.styleable.MimeSelfView_mimeCol, 10)
        mRowNUm = ta.getInt(R.styleable.MimeSelfView_mimeRow, 10)
        mimeArray = Array(mRowNUm) { IntArray(mColNUm) }
        randomArray()
        getDisRect(context)
        getFieldRect()
        initPaint()
    }

    private fun randomArray() {
        var random = Random()
        for (i in 0..(mimeArray.size-1)){
            for (j in 0..(mimeArray[i].size-1)){
                mimeArray[i][j] = random.nextInt(3)
            }
        }
    }

    private fun getFieldRect() {
        mFieldWidth = (mWidth - (mColNUm+10)) / mColNUm
        mFieldHeight = (mHeight - (mRowNUm+10)) / mRowNUm
    }

    private fun getDisRect(context: Context) {
        var wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        var display = wm.defaultDisplay
        var out = DisplayMetrics()
        display.getRealMetrics(out)
        val outRect = Rect()
        getWindowVisibleDisplayFrame(outRect)
        var h = outRect.height()
        // var p: Point = Point(0, 0)
        mWidth = out.widthPixels
        mHeight = h

    }

    /**
     * 初始化画笔
     */
    private fun initPaint() {
        mInitPaint = Paint()
        mNormalPaint = Paint()
        mMarkPaint = Paint()
        setPaint(mInitPaint, Color.RED)
        setPaint(mNormalPaint, Color.GREEN)
        setPaint(mMarkPaint, Color.BLUE)
    }

    private fun setPaint(paint: Paint, color: Int) {
        paint.color = color
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 2f
        paint.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //canvas.drawRect(0f, 0f, 100f, 100f, mMarkPaint)
       // mNormalPaint.textSize = 100f;
        //mFieldWidth = 100
        // mFieldHeight = 100
       // var paint = Paint()
        for (i in 0..(mimeArray.size-1)) {
            for (j in (0..mimeArray[i].size-1)) {
                when (mimeArray[i][j]) {
                    0 -> canvas.drawRect((j * mFieldWidth).toFloat() + 1, (i * mFieldHeight).toFloat() + 1, ((mFieldWidth + j * mFieldWidth).toFloat()), ((mFieldHeight + i * mFieldHeight).toFloat()), mInitPaint)
                    1 -> canvas.drawRect((j * mFieldWidth).toFloat() + 1, (i * mFieldHeight).toFloat() + 1, ((mFieldWidth + j * mFieldWidth).toFloat()), ((mFieldHeight + i * mFieldHeight).toFloat()), mMarkPaint)
                    2 -> canvas.drawRect((j * mFieldWidth).toFloat() + 1, (i * mFieldHeight).toFloat() + 1, ((mFieldWidth + j * mFieldWidth).toFloat()), ((mFieldHeight + i * mFieldHeight).toFloat()), mNormalPaint)
                    else -> canvas.drawRect((j * mFieldWidth).toFloat() + 1, (i * mFieldHeight).toFloat() + 1, ((mFieldWidth + j * mFieldWidth).toFloat()), ((mFieldHeight + i * mFieldHeight).toFloat()), mInitPaint)
                }
                //canvas.drawRect((j * mFieldWidth).toFloat() + 1, (i * mFieldHeight).toFloat() + 1, ((mFieldWidth + j * mFieldWidth).toFloat()), ((mFieldHeight + i * mFieldHeight).toFloat()), mInitPaint)
            }
        }
    }
}
