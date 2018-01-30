package com.yk.skill.androidskillplatform.selfcreate.mimesweeper.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View

/**
 * Created by Administrator on 2018/1/21.
 */

abstract class MimeBlockBase : View {
    private var borderLen:Int = 0
        set(value) {
            field = value
        }
    private  var blockLeft: Int = 0
        set(value) {
            field = value
        }
    private  var blockTop: Int = 0
        set(value) {
            field = value
        }

    constructor(context: Context) : super(context) {
        init(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {

    }

    private var paint: Paint? = null


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(blockLeft.toFloat(), blockTop.toFloat(), (blockLeft+borderLen).toFloat(), (blockTop+borderLen).toFloat(),paint)
    }
    abstract fun setPaint(paint:Paint)
    abstract fun setDrawable(mDrawable: Drawable)

}
