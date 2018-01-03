package com.yk.skill.androidskillplatform.selfcreate.flowgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/11/2.
 */

public class FlowGroupLayoutView extends ViewGroup {
    public FlowGroupLayoutView(Context context) {
        super(context);
    }

    public FlowGroupLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowGroupLayoutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        measureChildren(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int maxWidth;
        int lineWidth=0,lineHeight=0;
        int layoutHeight;
        int heightBuf = 0;
        int count = getChildCount();
        maxWidth = getMeasuredWidth();
        for (int i=0;i<count;i++){
            View childen = getChildAt(i);
            int width = childen.getMeasuredWidth();
            int height = childen.getMeasuredHeight();
            if((lineWidth+width)>=maxWidth){
                lineHeight = heightBuf;
                lineWidth = 0;
            }
            childen.layout(l+lineWidth,t+lineHeight,l+lineWidth+width,t+lineHeight+height);
            lineWidth += width;
            heightBuf = Math.max(heightBuf,height);

        }
    }
}
