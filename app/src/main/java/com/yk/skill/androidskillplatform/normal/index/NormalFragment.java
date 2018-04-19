package com.yk.skill.androidskillplatform.normal.index;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yk.skill.androidskillplatform.R;
import com.yk.skill.androidskillplatform.base.BaseFragment;


public class NormalFragment extends BaseFragment implements NormalFragmentDetail{


   // @BindView(R.id.fragment_normal_lv)
    ListView mFragmentNormalLv;

    NormalPresent mNormalPresent = null;
    @Override
    public View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_normal, null, false);
        mFragmentNormalLv = (ListView) view.findViewById(R.id.fragment_normal_lv);
        mNormalPresent = new NormalPresent(this,getActivity());
        return view;
    }

    @Override
    public void initDatas() {
        super.initDatas();
        mNormalPresent.initDatas();
    }

    @Override
    public void setAdapter(BaseAdapter myBaseAdapter) {
        mFragmentNormalLv.setAdapter(myBaseAdapter);
    }

    @Override
    public void setItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        mFragmentNormalLv.setOnItemClickListener(onItemClickListener);
    }

    @Override
    public int getListViewHeaderCount() {
        return mFragmentNormalLv.getHeaderViewsCount();
    }

}
