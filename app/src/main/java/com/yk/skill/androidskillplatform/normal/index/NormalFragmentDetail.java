package com.yk.skill.androidskillplatform.normal.index;

import android.view.View;
import android.widget.BaseAdapter;

import com.yk.skill.androidskillplatform.listview.base.MyBaseAdapter;

/**
 * Created by yuankang on 2017/8/4.
 */

public interface NormalFragmentDetail {
    public void setAdapter(BaseAdapter myBaseAdapter);
    public void setListViewHeader(View view);
}
