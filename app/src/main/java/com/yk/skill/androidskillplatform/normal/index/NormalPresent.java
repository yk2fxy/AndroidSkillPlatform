package com.yk.skill.androidskillplatform.normal.index;

import android.content.Context;
import android.content.Intent;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.yk.skill.androidskillplatform.R;
import com.yk.skill.androidskillplatform.listview.base.ListViewHolder;
import com.yk.skill.androidskillplatform.listview.base.MyBaseAdapter;
import com.yk.skill.androidskillplatform.normal.animation.AnimationActivity;
import com.yk.skill.androidskillplatform.normal.bluetooth.BluetoothActivity;
import com.yk.skill.androidskillplatform.normal.listview.ListViewActivity;
import com.yk.skill.androidskillplatform.webview.WebViewActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuankang on 2017/8/4.
 */

public class NormalPresent {
    private static final String TAG = NormalPresent.class.getSimpleName();
    private  NormalFragmentDetail normalFragmentDetail;
    List<NormalIndexItemBean> lists = new ArrayList<>();
    Context mContext;
    public NormalPresent(NormalFragmentDetail normalFragmentDetail,Context context) {
        this.normalFragmentDetail = normalFragmentDetail;
        this.mContext = context;
    }

    public void initDatas() {
        /*for(int i=0;i<20;i++){
            NormalIndexItemBean bean = new NormalIndexItemBean();
            bean.setTitle("Title"+i);
            String content = "Content" + i;
            for(int j=0;j<10;j++){
                content = "Content" + j + "----->"+content;
            }
            bean.setContent(content);
            lists.add(bean);
        }*/
        NormalIndexItemBean listviewBean = new NormalIndexItemBean();
        listviewBean.setTitle("ListView");
        listviewBean.setContent("08-12 07:23:24.350 2379-22169/com.google.android.googlequicksearchbox:search I/AudioController: internalShutdown");
        lists.add(listviewBean);
        NormalIndexItemBean animationBean = new NormalIndexItemBean();
        animationBean.setTitle("Animation");
        animationBean.setContent("animation introduction");
        lists.add(animationBean);
        animationBean = new NormalIndexItemBean();
        animationBean.setTitle("Bluetooth");
        animationBean.setContent("蓝牙连接 introduction");
        lists.add(animationBean);
        NormalIndexItemBean webViewBean = new NormalIndexItemBean();
        webViewBean.setTitle("WebView");
        webViewBean.setContent("");
        lists.add(webViewBean);

        BaseAdapter adapter = new MyBaseAdapter<NormalIndexItemBean>(lists,mContext,R.layout.item_normal_listview) {
            @Override
            public void converView(final ListViewHolder holder, NormalIndexItemBean bean) {
                holder.setText(R.id.item_normal_list_title,bean.getTitle());
                holder.setText(R.id.item_normal_list_content,bean.getContent());
                holder.setOnClickListener(R.id.item_normal_list_more_img, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView tv =  holder.getView(R.id.item_normal_list_content);
                        /*if(tv.getLineCount()>1){
                            Log.i(TAG, "onClick: setconut==1");
                            tv.setMaxLines(1);
                        }else {
                            Log.i(TAG, "onClick: setcount==10");
                            tv.setMaxLines(10);}*/
                        tv.setMaxLines(10);
                        Log.i(TAG, "onClick: "+tv.getMeasuredHeight()+"---w="+tv.getMeasuredWidth());
                        Log.i(TAG, "onClick: text--->"+tv.getTextSize()+"---"+tv.getTextScaleX()+"----"+tv.getLineCount());
                        //Log.i(TAG, "onClick: "+tv.getFontFeatureSettings());
                    }
                });
            }
        };

        normalFragmentDetail.setAdapter(adapter);
        normalFragmentDetail.setItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                gotoSelectPage(i);
            }
        });
    }

    private void gotoSelectPage(int i) {
        Intent intent = new Intent();
        int count = normalFragmentDetail.getListViewHeaderCount();

        switch (i-count){
            case 0:
                intent = new Intent(mContext,ListViewActivity.class);
               // intent.setClass(mContext,ListViewActivity.class);
                break;
            case 1:
                intent = new Intent(mContext,AnimationActivity.class);
                break;
            case 2:
                intent = new Intent(mContext, BluetoothActivity.class);
                break;
            case 3:
                intent = new Intent(mContext, WebViewActivity.class);
                break;
            default:
                return;
        }
        mContext.startActivity(intent);
    }
}
