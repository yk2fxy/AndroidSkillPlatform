package com.yk.skill.androidskillplatform.normal.index;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yk.skill.androidskillplatform.R;
import com.yk.skill.androidskillplatform.listview.base.ListViewHolder;
import com.yk.skill.androidskillplatform.listview.base.MyBaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuankang on 2017/8/4.
 */

public class NormalPresent {
    private  NormalFragmentDetail normalFragmentDetail;
    List<String> lists = new ArrayList<>();
    Context mContext;
    public NormalPresent(NormalFragmentDetail normalFragmentDetail,Context context) {
        this.normalFragmentDetail = normalFragmentDetail;
        this.mContext = context;
    }

    public void initDatas() {
        for(int i=0;i<10;i++){
            lists.add("this is "+i+" line");
        }

        BaseAdapter adapter = new MyBaseAdapter<String>(lists,mContext,R.layout.item_normal_listview) {
            @Override
            public void converView(ListViewHolder holder, String text) {
                holder.setText(R.id.item_normal_listview_tv,text);
            }
        };
        normalFragmentDetail.setAdapter(adapter);
        TextView tv = new TextView(mContext);
        tv.setText("this is header");
        tv.setTextSize(39);
        tv.setTextColor(Color.RED);
        normalFragmentDetail.setListViewHeader(tv);
    }
}
