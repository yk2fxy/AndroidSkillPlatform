package com.yk.skill.androidskillplatform.Third.index;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.yk.skill.androidskillplatform.base.BaseFragment;

/**
 * Created by yuankang on 2017/8/4.
 */

public class ThirdFragment extends BaseFragment {
    @Override
    public View initView() {
        TextView tv = new TextView(getActivity());
        tv.setText("third");

        tv.setTextColor(Color.RED);
        return tv;
    }
}
