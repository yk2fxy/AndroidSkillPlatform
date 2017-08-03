package com.yk.skill.androidskillplatform.index;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yk.skill.androidskillplatform.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IndexActivity extends AppCompatActivity implements IndexViewDetail {

    @BindView(R.id.header_text)
    TextView mHeaderText;
    @BindView(R.id.index_fl_container)
    FrameLayout mIndexFlContainer;
    IndexPresent mIndexPresent = new IndexPresent(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //动画相关
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Transition transition = TransitionInflater.from(this).inflateTransition(android.R.transition.explode);
        getWindow().setEnterTransition(transition);
        setContentView(R.layout.activity_index);
        ButterKnife.bind(this);
    }

    @Override
    public void setTitle(String title) {

    }
}
