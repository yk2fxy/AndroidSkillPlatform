package com.yk.skill.androidskillplatform.normal.animation;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import com.yk.skill.androidskillplatform.R;
/**
 * Created by Administrator on 2017/8/10.
 */

public class AnimationActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_animation);
        TextView tv = (TextView) findViewById(R.id.header_text);
        tv.setText("This is Animation");
        //findViewById(R.id.act_normal_anim_alpha_btn)

    }

    private void startAnimation(Animation animation) {
        animation.setDuration(3000);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
       // showText.startAnimation(animation);
    }
}
