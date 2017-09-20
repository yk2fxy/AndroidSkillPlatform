package com.yk.skill.androidskillplatform.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yuankang on 2017/9/20.
 */

public abstract class BaseActivity extends AppCompatActivity {
    Unbinder mUnbinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int id = getLayoutId();
        setContentView(id);
        mUnbinder = ButterKnife.bind(this);
        initView();
    }

    protected abstract int getLayoutId();

    public abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
