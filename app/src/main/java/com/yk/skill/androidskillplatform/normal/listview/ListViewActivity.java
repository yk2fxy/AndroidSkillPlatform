package com.yk.skill.androidskillplatform.normal.listview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yk.skill.androidskillplatform.R;
import com.yk.skill.androidskillplatform.listview.base.ListViewHolder;
import com.yk.skill.androidskillplatform.listview.base.MyBaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/8/8.
 */

public class ListViewActivity extends Activity{
    @BindView(R.id.header_text)
    TextView title;
    @BindView(R.id.header_back_btn)
    Button backBtn;
    @BindView(R.id.header_html_root)
    LinearLayout rootLL;
    @BindView(R.id.header_html_address)
    TextView htmlAddress;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_my_self_list_view);
        ButterKnife.bind(this);
        ListView lv = (ListView) findViewById(R.id.push_refresh_listview);
        List<String> lists = new ArrayList<>();
        for(int i=0;i<100;i++){
            lists.add("this is "+i);
        }
        MyBaseAdapter<String> adapter = new MyBaseAdapter<String>(lists,this,R.layout.item_sample_my_self_listview) {
            @Override
            public void converView(ListViewHolder holder, String text) {
                holder.setText(R.id.item_sample_my_self_listview_tv,text);
            }
        };
        title.setText("ListView");
        backBtn.setVisibility(View.VISIBLE);
        rootLL.setVisibility(View.VISIBLE);
        htmlAddress.setText(Html.fromHtml("<a href='tel:18565554482'>打电话</a>,<a href='smsto:18565554482'>发短信</a>,<a href='mailto:584991843@qq.com'>发邮件</a>,<a href='http://www.baidu.com'>Go百度</a>"));
        htmlAddress.setMovementMethod(LinkMovementMethod.getInstance());
        lv.setAdapter(adapter);
    }
}
