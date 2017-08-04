package com.yk.skill.androidskillplatform.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;
import java.util.Objects;

import static android.R.attr.id;

/**
 * Created by yuankang on 2017/8/4.
 */

public class MyBaseAdapter<T> extends BaseAdapter {
    List<T> datas = null;
    public MyBaseAdapter(List<T> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        return null;
    }
}
