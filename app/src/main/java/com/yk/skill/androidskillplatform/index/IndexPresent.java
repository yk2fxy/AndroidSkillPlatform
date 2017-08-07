package com.yk.skill.androidskillplatform.index;

import com.yk.skill.androidskillplatform.R;
import com.yk.skill.androidskillplatform.Third.index.ThirdFragment;
import com.yk.skill.androidskillplatform.base.BaseFragment;
import com.yk.skill.androidskillplatform.normal.index.NormalFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuankang on 2017/8/3.
 */

public class IndexPresent  {
    private IndexViewDetail indexViewDetail;
    List<BaseFragment> mBaseFragments = new ArrayList<>();
    public IndexPresent(IndexViewDetail indexViewDetail) {
        this.indexViewDetail = indexViewDetail;
    }

    public void init() {
        indexViewDetail.setTitle("Index Page");
        mBaseFragments.add(new NormalFragment());
        mBaseFragments.add(new ThirdFragment());
        onCheckecChanged(0);
    }

    public void onCheckecChanged(int i) {
        int pos = 0;
        switch (i){
            case R.id.index_act_rb_normal:
                pos = 0;
                break;
            case R.id.index_act_rb_third:
                pos = 1;
                break;
            case R.id.index_act_rb_self:
                pos = 2;
                break;
            case R.id.index_act_rb_other:
                pos = 3;
                break;
        }
        indexViewDetail.showFragment(mBaseFragments.get(pos));
    }
}
