package com.yk.skill.androidskillplatform.listview.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yk.skill.androidskillplatform.R;

import java.util.List;


/**
 * Created by yuankang on 2017/8/4.
 */

public abstract class MyBaseAdapter<T> extends BaseAdapter {
    List<T> datas = null;
    Context mContext;
    int mLayoutId;
    public MyBaseAdapter(List<T> datas, Context context, int layoutId) {
        this.datas = datas;
        this.mContext = context;
        this.mLayoutId = layoutId;
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

        ListViewHolder holder = ListViewHolder.get(mContext,view, mLayoutId,viewGroup);
        converView(holder,(T)datas.get(i));
        return holder.getRootView();
    }
    class ViewHolder{
        View view;
    }
    public abstract void converView(ListViewHolder holder, T text) ;

}
