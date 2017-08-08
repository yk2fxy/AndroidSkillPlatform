package com.yk.skill.androidskillplatform.index;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yk.skill.androidskillplatform.R;
import com.yk.skill.androidskillplatform.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

public class IndexActivity extends AppCompatActivity implements IndexViewDetail {

    @BindView(R.id.header_text)
    TextView mHeaderText;
    IndexPresent mIndexPresent = new IndexPresent(this);
    @BindView(R.id.index_act_rg)
    RadioGroup mIndexActRg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //动画相关
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Transition transition = TransitionInflater.from(this).inflateTransition(android.R.transition.explode);
        transition.setDuration(3000);
        getWindow().setEnterTransition(transition);
        setContentView(R.layout.activity_index);
        ButterKnife.bind(this);
        mIndexPresent.init();
        mIndexActRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup,int i) {
                mIndexPresent.onCheckecChanged(i);
            }
        });

    }

    @Override
    public void setTitle(String title) {
        mHeaderText.setText(title);
    }

    @Override
    public void setViewPage(int position) {
    }

    @Override
    public void showFragment(BaseFragment baseFragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.index_fl_container,baseFragment);
        ft.commit();
    }
}
