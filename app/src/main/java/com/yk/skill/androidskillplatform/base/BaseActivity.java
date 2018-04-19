package com.yk.skill.androidskillplatform.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by yuankang on 2017/9/20.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int id = getLayoutId();
        setContentView(id);
        initView();
    }

    protected abstract int getLayoutId();

    public abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
