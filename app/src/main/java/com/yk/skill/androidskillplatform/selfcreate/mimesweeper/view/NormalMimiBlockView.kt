package com.yk.skill.androidskillplatform.selfcreate.mimesweeper.view

import android.content.Context
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import java.util.jar.Attributes

/**
 * Created by Administrator on 2018/1/23.
 */
class NormalMimiBlockView:MimeBlockBase {


    constructor(context:Context):super(context)
    constructor(context:Context,attrs:AttributeSet):super(context,attrs)
    constructor(context: Context,attrs: AttributeSet,defStyleAttr: Int):super(context,attrs,defStyleAttr)

    override fun setDrawable(mDrawable: Drawable) {
    }
    override fun setPaint(paint: Paint) {

    }

}