package com.yk.skill.androidskillplatform.normal.index;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.yk.skill.androidskillplatform.R;
import com.yk.skill.androidskillplatform.index.IndexActivity;
import com.yk.skill.androidskillplatform.listview.base.ListViewHolder;
import com.yk.skill.androidskillplatform.listview.base.MyBaseAdapter;
import com.yk.skill.androidskillplatform.normal.listview.ListViewActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuankang on 2017/8/4.
 */

public class NormalPresent {
    private  NormalFragmentDetail normalFragmentDetail;
    SparseArray<NormalIndexItemBean> lists = new SparseArray<>();
    Context mContext;
    public NormalPresent(NormalFragmentDetail normalFragmentDetail,Context context) {
        this.normalFragmentDetail = normalFragmentDetail;
        this.mContext = context;
    }

    public void initDatas() {
        for(int i=0;i<20;i++){
            NormalIndexItemBean bean = new NormalIndexItemBean();
            bean.setTitle("Title"+i);
            String content = "Content" + i;
            for(int j=0;j<10;j++){
                content = "Content" + j + "----->"+content;
            }
            bean.setContent(content);
            lists.put(i,bean);
        }

        BaseAdapter adapter = new MyBaseAdapter<NormalIndexItemBean>(lists,mContext,R.layout.item_normal_listview) {
            @Override
            public void converView(ListViewHolder holder, NormalIndexItemBean bean) {
                holder.setText(R.id.item_normal_list_title,bean.getTitle());
                holder.setText(R.id.item_normal_list_content,bean.getContent());
            }
        };
      /* List<String> mLs = new ArrayList<>();
        for(int i=0;i<20;i++){
            mLs.add("Content" + i);
        }
        BaseAdapter adapter = new MyBaseAdapter<String>(mLs,mContext,R.layout.test) {
            @Override
            public void converView(ListViewHolder holder, String bean) {
                holder.setText(R.id.test_lv,bean);
            }
        };*/
        normalFragmentDetail.setAdapter(adapter);
        normalFragmentDetail.setItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               // Toast.makeText(mContext,"dddddd"+i,Toast.LENGTH_LONG).show();
                gotoSelectPage(i);
            }
        });
        //normalFragmentDetail.setTitle("");
    }

    private void gotoSelectPage(int i) {
        Intent intent = new Intent();
        switch (i){
            case 1:
                intent = new Intent(mContext,ListViewActivity.class);
               // intent.setClass(mContext,ListViewActivity.class);
                break;
        }
        mContext.startActivity(intent);
    }
}
