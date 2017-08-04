package com.yk.skill.androidskillplatform.index;

import com.yk.skill.androidskillplatform.base.BaseFragment;

/**
 * Created by yuankang on 2017/8/3.
 * index界面操作相关接口
 */

public interface IndexViewDetail {
    public void setTitle(String title);

    void setViewPage(int position);

    void showFragment(BaseFragment baseFragment);
}
