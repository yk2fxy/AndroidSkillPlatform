package com.yk.skill.androidskillplatform.normal;

import com.yk.skill.androidskillplatform.listview.MyBaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuankang on 2017/8/4.
 */

public class NormalPresent {
    private  NormalFragmentDetail normalFragmentDetail;
    List<String> lists = new ArrayList<>();

    public NormalPresent(NormalFragmentDetail normalFragmentDetail) {
        this.normalFragmentDetail = normalFragmentDetail;
    }

    public void initDatas() {
        for(int i=0;i<10;i++){
            lists.add("this is "+i+" line");
        }
        normalFragmentDetail.setAdapter(new MyBaseAdapter());
    }
}
