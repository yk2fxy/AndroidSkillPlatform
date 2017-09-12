package com.yk.skill.androidskillplatform.selfcreate.index;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.yk.skill.androidskillplatform.R;
import com.yk.skill.androidskillplatform.selfcreate.speedshow.SpeedShowActivity;
import com.yk.skill.androidskillplatform.listview.base.ListViewHolder;
import com.yk.skill.androidskillplatform.listview.base.MyBaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */

public class SelfCreateFragment extends com.yk.skill.androidskillplatform.base.BaseFragment {
    @Override
    public View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_normal,null);
        ListView mListView = (ListView) view.findViewById(R.id.fragment_normal_lv);
        List<String> mLists = new ArrayList<>();

        mLists.add("自定义码表盘");
        mListView.setAdapter(new MyBaseAdapter<String>(mLists,getActivity(),R.layout.item_normal_listview) {
            @Override
            public void converView(ListViewHolder holder, String text) {
                holder.setText(R.id.item_normal_list_title,text);
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),"position="+position,Toast.LENGTH_SHORT).show();
                switch (position){
                    case 1:
                        Intent intent = new Intent(getActivity(), SpeedShowActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
        return view;
    }
}
