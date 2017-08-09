package com.yk.skill.androidskillplatform.normal.listview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * TODO: document your custom view class.
 */
public class MySelfListView extends ListView implements AbsListView.OnScrollListener{
    Context context;
    boolean isLoadingMode;
    boolean isRefreshMode;
    private TextView footerView;
    private TextView headerView;

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
       // initFooterView(context);
    }

    private void initFooterView(Context context) {
        footerView = new TextView(context);
        footerView.setText("this is footer");
        footerView.setTextSize(50);
        footerView.setTextColor(Color.RED);
        addFooterView(footerView);
    }
    //

    private void initHeaderView(Context context) {
        headerView = new TextView(context);
        headerView.setText("this is header");
        headerView.setTextSize(50);
        headerView.setTextColor(Color.RED);
        headerView.measure(0,0);
        headerView.setPadding(0,-headerView.getMeasuredHeight(),0,0);
        addHeaderView(headerView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        float downY = 0;
        float curY = 0;
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                downY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                curY = ev.getRawY();
                if(getFirstVisiblePosition()==0&&(curY-downY)<0)
                return true;
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {
            if(scrollState == OnScrollListener.SCROLL_STATE_IDLE){
                if(getFirstVisiblePosition()==0&&!isRefreshMode){
                    isRefreshMode = true;
                    headerView.setPadding(0,0,0,0);
                   // Toast.makeText(context,"this is header",Toast.LENGTH_SHORT).show();
                    Log.i("onScrollStateChanged", "onScrollStateChanged: header");
                }else if(getLastVisiblePosition()==getCount()-1){
                   // Toast.makeText(context,"this is footer",Toast.LENGTH_SHORT).show();
                    Log.i("onScrollStateChanged", "onScrollStateChanged: footer");
                }
            }else {
                headerView.setPadding(0,-headerView.getMeasuredHeight(),0,0);
            }
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }
}
