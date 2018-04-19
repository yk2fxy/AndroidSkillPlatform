package com.yk.skill.androidskillplatform.selfcreate.single_panel.view

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import com.yk.skill.androidskillplatform.selfcreate.single_panel.listener.CheckIsSingleListener
import com.yk.skill.androidskillplatform.utils.WindowCalcUtils

/**
 * Created by Administrator on 2018/3/22.
 */
class SinglePanelContainer:ViewGroup {
     constructor(context: Context?) : super(context){
         initContainer(context,null,0)
     }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        initContainer(context,attrs,0)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        initContainer(context,attrs,defStyleAttr)
    }

    private lateinit var cancelButton: Button//取消按钮，用于清除签字面板上的签字轨迹
    private lateinit var okButton: Button//确认按钮，用于保存签字面板上的签字轨迹到图片
    private lateinit var startAnimatorOK:ObjectAnimator//确认按钮的动画
    private lateinit var startAnimatorCancel:ObjectAnimator//取消按钮的动画
    /**
     * 初始化容器，包括签字面板的高宽，以及添加签字面板的事件监听
     */
    private fun initContainer(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) {
        //初始化，将自定的签字笔放进来
        var singleCacheCanvasView = SingleCacheCanvasView(context)

        singleCacheCanvasView.buildHeight(WindowCalcUtils.getWindowsHeight(context)/2)
        singleCacheCanvasView.buildWidth(WindowCalcUtils.getWindowsWidth(context))
        singleCacheCanvasView.addCheckIsSingleListener(object : CheckIsSingleListener {
            override fun startSingle() {
                if(startAnimatorOK.isRunning){
                    startAnimatorOK.cancel()
                }
                if(startAnimatorCancel.isRunning){
                    startAnimatorCancel.cancel()
                }
                startAnimatorOK.start()
                startAnimatorCancel.start()
            }
            override fun endSingle() {
                startAnimatorCancel.reverse()
                startAnimatorOK.reverse()
            }
        })
        //将签字面板的view添加到容器中
        addView(singleCacheCanvasView)
        cancelButton = Button(context)
        cancelButton.width = 200
        cancelButton.height = 100
        cancelButton.text = "cancel"
        cancelButton.setOnClickListener {
            singleCacheCanvasView.clearBmp()
        }
        //添加取消按钮到容器中
        addView(cancelButton)
        okButton = Button(context)
        okButton.width = 200
        okButton.height = 100
        okButton.text = "ok"
        okButton.setOnClickListener {
            singleCacheCanvasView.saveBmp()
        }
        //添加确认按钮到容器中
        addView(okButton)
        initAmimation()
    }

    /**
     * 动画的初始化
     */
    private fun initAmimation() {
        startAnimatorOK = ObjectAnimator.ofFloat(okButton, "alpha", 1f, 0f)
        startAnimatorOK.duration = 2000
        startAnimatorCancel = ObjectAnimator.ofFloat(cancelButton, "alpha", 1f, 0f)
        startAnimatorCancel.duration = 2000
    }

    //让子view能调用onMeasure方法
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        for (i in 0..(childCount-1)){
            var view = getChildAt(i)
            measureChild(view,widthMeasureSpec,heightMeasureSpec)
            Log.e("SPC","width="+view.width+"`````height="+view.height)
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        //因为各个view放置到容器中的顺序和数量都是固定好的，所有这里就直接通过getChildAt去取，不判断容器中view的数量
        var view = getChildAt(0)
        var w = (view as SingleCacheCanvasView).cacheBmpWidth
        var h = (view as SingleCacheCanvasView).cacheBmpHeight
        view.layout(0,0,view.measuredWidth+l,view.measuredHeight+t)
        view = getChildAt(1)
        view.layout(0,0+h-view.measuredHeight,l+view.measuredWidth,h)
        view = getChildAt(2)
        view.layout(0+w-view.measuredWidth,0+h-view.measuredHeight,l+w, h)
    }

}