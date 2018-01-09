package com.yk.skill.androidskillplatform.other.index;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yk.skill.androidskillplatform.R;
import com.yk.skill.androidskillplatform.base.BaseFragment;
import com.yk.skill.androidskillplatform.other.tinker.TinkerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */

public class OtherFragment extends BaseFragment {
    public List<String> mLists = new ArrayList<>();
    @Override
    public View initView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_other_index,null);
        RecyclerView recycleListView = (RecyclerView) view.findViewById(R.id.fragment_other_index_rlv);
       // recycleListView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(),2,LinearLayoutManager.VERTICAL,false);
        recycleListView.setLayoutManager(mLayoutManager);
        recycleListView.setAdapter(new MyRecyclerAdapter());
        recycleListView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        mLists.add("tinker");
        for(int i=0;i<20;i++){
            mLists.add("aaaaa"+i);
        }

        return view;
    }
    class MyRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder>{

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_other_index,null);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText(mLists.get(position));
        }

        @Override
        public int getItemCount() {
            return mLists.size();
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv = new TextView(getActivity());
        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.item_other_index_tv);
            tv.setOnClickListener(view -> {
                Intent intent = new Intent(getActivity(), TinkerActivity.class);
                startActivity(intent);
            });
        }
    }
}
