package com.yk.skill.androidskillplatform.base;

import android.os.Bundle;
import android.sax.RootElement;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yuankang on 2017/8/4.
 */

public abstract class BaseFragment extends Fragment {
    Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = initView();
        unbinder = ButterKnife.bind(rootView);
        initDatas();
        return rootView;
    }

    public void initDatas() {

    }

    public abstract View initView();

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
