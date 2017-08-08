package com.yk.skill.androidskillplatform.normal.listview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.yk.skill.androidskillplatform.R;

/**
 * Created by Administrator on 2017/8/8.
 */

public class ListViewActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_my_self_list_view);
    }
}
