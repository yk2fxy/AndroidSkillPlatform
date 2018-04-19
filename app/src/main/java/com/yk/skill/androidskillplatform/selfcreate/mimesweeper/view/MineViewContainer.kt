package com.yk.skill.androidskillplatform.selfcreate.mimesweeper.view

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.Toast
import com.yk.skill.androidskillplatform.R
import com.yk.skill.androidskillplatform.selfcreate.mimesweeper.utils.WindowDetailUtils

/**
 * Created by Administrator on 2018/1/31.
 */
class MineViewContainer:ViewGroup {
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var num = childCount
        var index = 0
        for(i in 0..(colNum-1)){
            for(j in 0..(rowNum-1)) {
                var v = getChildAt(index)
                index++

                //v.layout((i*blockWidth).toInt(), (j*blockWidth).toInt(), (i*blockWidth+blockWidth-1).toInt(), (j*blockWidth+blockWidth-1).toInt())
                var blockWInt = blockWidth.toInt()
                v.layout(i*blockWInt+1,j*blockWInt+1,i*blockWInt+blockWInt-1,j*blockWInt+blockWInt-1)
            }

        }
    }

    constructor(context: Context) : super(context){
        init(context,null,0)
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){
        init(context,attrs,0)
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        init(context,attrs,defStyleAttr)

    }

    private var rowNum: Int = 0

    private var colNum: Int = 0

    private var blockWidth: Float = 0f

    fun init(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int){

        var mineWidth = 100.0f
        var w = WindowDetailUtils.getWindowWidth(context)
        var h = WindowDetailUtils.getWindowHeight(context)
        var ta = context.obtainStyledAttributes(attributeSet,R.styleable.MimeBlockBase)
        colNum = ta.getInt(R.styleable.MimeBlockBase_colNum,0)
        rowNum = ta.getInt(R.styleable.MimeBlockBase_rowNum,0)
        var blockW = 0f
        var blockH = 0f
        blockW = w/colNum
        blockH = h/rowNum
        blockWidth = if(blockH>blockW){
            blockW
        }else{
            blockH
        }
        for(i in 0..(colNum-1)){
            for(j in 0..(rowNum-1)) {
                var view: MimeBlockBase = MimeBlockBase(context, blockWidth)
                addView(view)
                view.setOnClickListener {
                   // Toast.makeText(context, ">>>>>" + i+">>>>"+j, Toast.LENGTH_LONG).show
                    (it as MimeBlockBase).setView(i)
                }
            }
        }
    }
}