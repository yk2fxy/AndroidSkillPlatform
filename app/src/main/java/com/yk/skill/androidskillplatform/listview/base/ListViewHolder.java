package com.yk.skill.androidskillplatform.listview.base;


import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by yuankang on 2017/8/7.
 */

public class ListViewHolder {
    private  View rootView;
    private SparseArray<View> views;
    private ListViewHolder(Context context, int layoutId, ViewGroup viewGroup) {
        rootView = LayoutInflater.from(context).inflate(layoutId,null,false);
        rootView.setTag(this);
        views = new SparseArray<>();
    }

    public static ListViewHolder get(Context context, View contentView, int layoutId, ViewGroup viewGroup){
        ListViewHolder holder = null;
        if(contentView==null){
            holder = new ListViewHolder( context,  layoutId,viewGroup);
        }else {
            holder = (ListViewHolder) contentView.getTag();
        }
        return  holder;
    }
    public View getRootView(){
        return rootView;
    }
    public  ListViewHolder setText(int viewId,String text){
        TextView tv =  getView(viewId);
        tv.setText(text);
        return this;
    }
    public <T extends View> T getView(int viewId){
        View view = views.get(viewId);
        if(view==null){
            view = rootView.findViewById(viewId);
        }
        return (T) view;
    }
}
