package com.yk.skill.androidskillplatform.normal.listview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.yk.skill.androidskillplatform.R;

/**
 * TODO: document your custom view class.
 */
public class MySelfListView extends ListView implements AbsListView.OnScrollListener{
    public final static String TAG = MySelfListView.class.getSimpleName();
    Context context;
    boolean isLoadingMode;
    boolean isRefreshMode;
    private TextView footerView;
    private View headerView;
    private TextView headerTextView;
    private ImageView headerImageView;
    private Animation upAnimation;
    private boolean isRefreshing = false;

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
        //initFooterView(context);
        initAnimalSatus(context);
    }

    private void initAnimalSatus(Context context) {
        Log.i(TAG, "initAnimalSatus: "+ Animation.RELATIVE_TO_SELF);
        upAnimation = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        upAnimation.setRepeatMode(Animation.RESTART);
        //upAnimation.setFillAfter(true);
        upAnimation.setRepeatCount(Animation.INFINITE);
        upAnimation.setDuration(200);

        //headerImageView.startAnimation(upAnimation);
        ObjectAnimator objectAnimator = new ObjectAnimator();
    }
    public void setHeaderView(View view){

    }
    public void setFooterView(View view){

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
        headerView = LayoutInflater.from(context).inflate(R.layout.self_listview_header,null);
        headerImageView = (ImageView) headerView.findViewById(R.id.self_listview_header_img);
        headerImageView.setVisibility(View.GONE);
        headerTextView = (TextView) headerView.findViewById(R.id.self_listview_header_title);
        headerTextView.setVisibility(View.GONE);
       // headerImageView.measure(0,0);
        headerView.measure(0,0);
        headerView.setPadding(0,-headerView.getMeasuredHeight(),0,0);
        addHeaderView(headerView);
    }
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    headerImageView.clearAnimation();
                    headerView.measure(0,0);
                    headerView.setPadding(0,-headerView.getMeasuredHeight(),0,0);
                    headerView.setVisibility(View.GONE);
                    break;
            }
        }
    };
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
                if(getFirstVisiblePosition()==0&&(curY-downY)<0) {
                    if(isRefreshMode){
                        Log.i(TAG, "onTouchEvent: enter fresh");
                        headerImageView.setVisibility(View.VISIBLE);
                        headerImageView.startAnimation(upAnimation);
                    }else {
                        Log.i(TAG, "onTouchEvent: retrun true");
                        return true;
                    }
                }else{
                    if(isRefreshMode){
                        isRefreshMode = false;
                        Log.i(TAG, "onTouchEvent: enter fresh");
                        headerImageView.setVisibility(View.VISIBLE);
                        headerImageView.startAnimation(upAnimation);
                        headerTextView.setText("正在刷新");
                        isRefreshing = true;
                        setDataRefreshCompleteListener(new DataRefreshCompleteListener() {
                            @Override
                            public void complete() {
                                mHandler.sendEmptyMessage(0);

                                Log.i(TAG, "DataRefreshCompleteListener: complete");
                            }
                        });
                    }
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    private void setDataRefreshCompleteListener(final DataRefreshCompleteListener dataRefreshCompleteListener) {
        Log.i(TAG, "setDataRefreshCompleteListener: enter");
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                dataRefreshCompleteListener.complete();
            }
        }.start();
        Log.i(TAG, "setDataRefreshCompleteListener: complete");
    }

    public interface DataRefreshCompleteListener{
        public void complete();
    }
    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {
            if(scrollState == OnScrollListener.SCROLL_STATE_IDLE){
                if(getFirstVisiblePosition()==0&&!isRefreshMode){
                    isRefreshMode = true;
                    headerView.setPadding(0,0,0,0);
                    headerView.setVisibility(View.VISIBLE);
                    headerTextView.setVisibility(View.VISIBLE);
                    headerTextView.setText("下拉刷新");
                   // Toast.makeText(context,"this is header",Toast.LENGTH_SHORT).show();
                    Log.i("onScrollStateChanged", "onScrollStateChanged: header");
                }else if(getLastVisiblePosition()==getCount()-1){
                   // Toast.makeText(context,"this is footer",Toast.LENGTH_SHORT).show();
                    Log.i("onScrollStateChanged", "onScrollStateChanged: footer");
                }
            }else {

                //headerView.setPadding(0,-headerView.getMeasuredHeight(),0,0);
            }
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }
}
