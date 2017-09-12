package com.yk.skill.androidskillplatform.normal.animation;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yk.skill.androidskillplatform.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/10.
 */

public class AnimationActivity extends Activity{
    @BindView(R.id.act_normal_anim_alpha_btn)
    Button alphaAnimBtn;
    @BindView(R.id.act_normal_anim_show_text)
    TextView showText;
    @BindView(R.id.header_back_btn)
    Button backBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_animation);
        TextView tv = (TextView) findViewById(R.id.header_text);
        tv.setText("This is Animation");
        //findViewById(R.id.act_normal_anim_alpha_btn);
        ButterKnife.bind(this);
        backBtn.setVisibility(View.VISIBLE);

    }
    @OnClick(R.id.header_back_btn)
    public void clickBackBtn(){
        this.finish();
    }
    @OnClick(R.id.act_normal_anim_alpha_btn)
    public  void clickAlpha(){
        startAnimation(new AlphaAnimation(1.0f,0.3f));

    }
    @OnClick(R.id.act_normal_anim_rotate_btn)
    public void clickRotate(){
        startAnimation(new RotateAnimation(0,360, RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f));

    }
    @OnClick(R.id.act_normal_anim_trans_btn)
    public void clickTrans(){
        startAnimation(new TranslateAnimation(0,300,0,600));
    }

    private void startAnimation(Animation animation) {
        animation.setDuration(3000);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        showText.startAnimation(animation);
    }
}
