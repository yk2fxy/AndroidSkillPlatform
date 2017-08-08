package com.yk.skill.androidskillplatform.normal.listview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.yk.skill.androidskillplatform.R;

/**
 * TODO: document your custom view class.
 */
public class MySelfListView extends ListView implements AbsListView.OnScrollListener{
    Context context;
    public MySelfListView(Context context) {
        super(context);
        init(null, 0,context);
    }

    public MySelfListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0,context);
    }

    public MySelfListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle,context);
    }

    private void init(AttributeSet attrs, int defStyle,Context context) {
        setOnScrollListener(this);
        initHeaderView(context);
        initFooterView(context);
    }

    private void initFooterView(Context context) {
        TextView tv = new TextView(context);
        tv.setText("this is footer");
        addFooterView(tv);
    }

    private void initHeaderView(Context context) {
        TextView tv = new TextView(context);
        tv.setText("this is header");
        addHeaderView(tv);
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }
}
