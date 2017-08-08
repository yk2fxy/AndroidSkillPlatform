package com.yk.skill.androidskillplatform.normal.index;

import android.widget.AdapterView;
import android.widget.BaseAdapter;


/**
 * Created by yuankang on 2017/8/4.
 */

public interface NormalFragmentDetail {
     void setAdapter(BaseAdapter myBaseAdapter);
    void setItemClickListener(AdapterView.OnItemClickListener onItemClickListener);
}
