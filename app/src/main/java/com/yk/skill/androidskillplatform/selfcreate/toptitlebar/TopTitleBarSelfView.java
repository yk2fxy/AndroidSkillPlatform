package com.yk.skill.androidskillplatform.selfcreate.toptitlebar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yk.skill.androidskillplatform.R;

/**
 * TODO: document your custom view class.
 */
public class TopTitleBarSelfView extends RelativeLayout {
    private String mCancelText,mTitleText,mOkText; // TODO: use a default from R.string...
    private int mCancelTextColor = Color.RED,mTitleTextColor=Color.BLACK,mOkTextColor=Color.BLUE; // TODO: use a default from R.color...
    private float mCancelTextSize = 26,mTitleTextSize=30,mOkTextSize = 26; // TODO: use a default from R.dimen...
    private Drawable mCancelBackground,mTitleBackground,mOkBackground;
    private Button mCancelBtn,mOkBtn;
    private TextView mTitleTv;
    TitleBarClickListener listener;

    public void setListener(TitleBarClickListener listener) {
        this.listener = listener;
    }

    public TopTitleBarSelfView(Context context) {
        super(context);
        init(null, 0);
    }
    public TopTitleBarSelfView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }
    public TopTitleBarSelfView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }
    private void init(AttributeSet attrs, int defStyle) {
        loadAttributes(attrs, defStyle);
        initChildrenView();
    }
    private void initChildrenView() {
        mCancelBtn = new Button(getContext());
        mOkBtn = new Button(getContext());
        mTitleTv = new TextView(getContext());

        mCancelBtn.setText(mCancelText);
        mCancelBtn.setTextSize(mCancelTextSize);
        mCancelBtn.setTextColor(mCancelTextColor);
        mCancelBtn.setBackground(mCancelBackground);
        RelativeLayout.LayoutParams cancelParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        cancelParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        cancelParams.topMargin = 10;
        addView(mCancelBtn,cancelParams);
        mCancelBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.cancel();
            }
        });

        mOkBtn.setText(mOkText);
        mOkBtn.setTextSize(mOkTextSize);
        mOkBtn.setTextColor(mOkTextColor);
        mOkBtn.setBackground(mOkBackground);
        RelativeLayout.LayoutParams okParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        okParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);
        okParams.topMargin = 10;
        okParams.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE);
        addView(mOkBtn,okParams);
        mOkBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.ok();
            }
        });

        mTitleTv.setText(mTitleText);
        mTitleTv.setTextSize(mTitleTextSize);
        mTitleTv.setTextColor(mTitleTextColor);
        mTitleTv.setBackground(mTitleBackground);
        LayoutParams titleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
        titleParams.addRule(RelativeLayout.ALIGN_PARENT_TOP,RelativeLayout.TRUE);
        addView(mTitleTv,titleParams);

    }

    private void loadAttributes(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.TopTitleBarSelfView, defStyle, 0);

        mCancelText = a.getString(R.styleable.TopTitleBarSelfView_cancelText);
        mCancelTextSize = a.getDimension(R.styleable.TopTitleBarSelfView_cancelTextSize,mCancelTextSize);
        mCancelTextColor = a.getColor(R.styleable.TopTitleBarSelfView_cancelTextColor,mCancelTextColor);
        mCancelBackground = a.getDrawable(R.styleable.TopTitleBarSelfView_cancelBackground);

        mOkText = a.getString(R.styleable.TopTitleBarSelfView_okText);
        mOkTextSize = a.getDimension(R.styleable.TopTitleBarSelfView_okTextSize,mOkTextSize);
        mOkTextColor = a.getColor(R.styleable.TopTitleBarSelfView_okTextColor,mOkTextColor);
        mOkBackground = a.getDrawable(R.styleable.TopTitleBarSelfView_oklBackground);

        mTitleText = a.getString(R.styleable.TopTitleBarSelfView_titleText);
        mTitleTextSize = a.getDimension(R.styleable.TopTitleBarSelfView_titleTextSize,mTitleTextSize);
        mTitleTextColor = a.getColor(R.styleable.TopTitleBarSelfView_titleTextColor,mTitleTextColor);
        mTitleBackground = a.getDrawable(R.styleable.TopTitleBarSelfView_titleBackground);

        a.recycle();
    }
}
