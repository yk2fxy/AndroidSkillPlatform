package com.yk.skill.androidskillplatform.selfcreate.index;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.yk.skill.androidskillplatform.R;
import com.yk.skill.androidskillplatform.selfcreate.double_cache_canvas.DoubleCacheCanvasActivity;
import com.yk.skill.androidskillplatform.selfcreate.flower_curve.FlowerCurveActivity;
import com.yk.skill.androidskillplatform.selfcreate.mimesweeper.MimesweeperActivity;
import com.yk.skill.androidskillplatform.selfcreate.single_panel.SinglePanelActivity;
import com.yk.skill.androidskillplatform.selfcreate.toptitlebar.SelfTopTitleBarActivity;
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
        mLists.add("自定义顶部标题栏");
        mLists.add("扫雷");
        mLists.add("繁花曲线");
        mLists.add("双缓冲画布");
        mLists.add("签字面板");
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
                    case 2:
                        Intent intent2 = new Intent(getActivity(), SelfTopTitleBarActivity.class);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent mimeIntent = new Intent(getActivity(), MimesweeperActivity.class);
                        startActivity(mimeIntent);
                    case 4:
                        Intent fcIntent = new Intent(getActivity(), FlowerCurveActivity.class);
                        startActivity(fcIntent);
                    case 5:
                        Intent dccIntent = new Intent(getActivity(), DoubleCacheCanvasActivity.class);
                        startActivity(dccIntent);
                    case 6:
                        Intent spIntent = new Intent(getActivity(), SinglePanelActivity.class);
                        startActivity(spIntent);
                }
            }
        });
        return view;
    }
}
