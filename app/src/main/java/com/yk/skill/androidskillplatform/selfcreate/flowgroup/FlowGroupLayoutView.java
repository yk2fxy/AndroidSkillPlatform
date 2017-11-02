package com.yk.skill.androidskillplatform.selfcreate.flowgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/11/2.
 */

public class FlowGroupLayoutView extends ViewGroup {
    int width,height;
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
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
