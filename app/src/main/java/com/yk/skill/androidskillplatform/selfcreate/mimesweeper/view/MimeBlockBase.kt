package com.yk.skill.androidskillplatform.selfcreate.mimesweeper.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.Toast

/**
 * Created by Administrator on 2018/1/21.
 */

class MimeBlockBase : View {

    constructor(context: Context,mineWidth:Float) : super(context) {
        this.mineWidth = mineWidth
        init(context, null, 0)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
       // paint = setPaint()
        paint = Paint()
        paint?.color = Color.RED
        paint?.style = Paint.Style.FILL_AND_STROKE
        paint?.strokeWidth = 10f
    }

    private var paint: Paint? = null


    private var mineWidth: Float = 0f

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(0f, 0f, mineWidth-1, mineWidth-1,paint)
    }
    fun setView(x:Int){
        Toast.makeText(context,"landmine>>>"+x, Toast.LENGTH_SHORT).show()
        //paint = Paint()
        paint?.color = Color.BLUE
        paint?.style = Paint.Style.FILL_AND_STROKE
        paint?.strokeWidth = 10f
        invalidate()
    }

}
