package com.yk.skill.androidskillplatform.normal;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yk.skill.androidskillplatform.R;
import com.yk.skill.androidskillplatform.base.BaseFragment;
import com.yk.skill.androidskillplatform.listview.MyBaseAdapter;

import butterknife.BindView;

public class NormalFragment extends BaseFragment implements NormalFragmentDetail{


    @BindView(R.id.fragment_normal_lv)
    ListView mFragmentNormalLv;

    NormalPresent mNormalPresent = new NormalPresent(this);
    @Override
    public View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_normal, null, false);
        return view;
    }

    @Override
    public void initDatas() {
        super.initDatas();
        mNormalPresent.initDatas();
    }

    @Override
    public void setAdapter(MyBaseAdapter myBaseAdapter) {
        mFragmentNormalLv.setAdapter(myBaseAdapter);
    }
}
