package com.yk.skill.androidskillplatform.normal.listview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.widget.ListView;

import com.yk.skill.androidskillplatform.R;
import com.yk.skill.androidskillplatform.listview.base.ListViewHolder;
import com.yk.skill.androidskillplatform.listview.base.MyBaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/8.
 */

public class ListViewActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_my_self_list_view);
        ButterKnife.bind(this);
        ListView lv = (ListView) findViewById(R.id.push_refresh_listview);
        SparseArray<String> lists = new SparseArray<>();
        for(int i=0;i<100;i++){
            lists.put(i,"this is "+i);
        }
        MyBaseAdapter<String> adapter = new MyBaseAdapter<String>(lists,this,R.layout.item_sample_my_self_listview) {
            @Override
            public void converView(ListViewHolder holder, String text) {
                holder.setText(R.id.item_sample_my_self_listview_tv,text);
            }
        };
        lv.setAdapter(adapter);
    }
}
