package com.yk.skill.androidskillplatform.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * 此app所有用到fragment的基类，用于处理一些fragment公共的操作，简化子fragment的代码
 * Created by yuankang on 2017/8/4.
 */

public abstract class BaseFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //获取子view的初始化界面
        View rootView = initView();
        //绑定butterknife框架
        //初始化数据
        initDatas();
        return rootView;
    }

    /**
     * 初始化子fragment的数据
     * 此方法不是必须重写，只有子fragment中需要有数据初始化时才需要重写
     */
    public void initDatas() {

    }

    /**
     * 初始化子fragment的view
     * 此方法必须重写，并返回子fragment的初始化view
     * @return
     */
    public abstract View initView();

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在fragment被销毁是解除绑定buterknife
    }
}
