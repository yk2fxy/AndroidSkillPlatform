package com.yk.skill.androidskillplatform.selfcreate.speedshow;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.SeekBar;

import com.yk.skill.androidskillplatform.R;

/**
 * Created by Administrator on 2017/8/16.
 */

public class SpeedShowActivity extends Activity {
    private final static  String TAG = SpeedShowActivity.class.getSimpleName();
    SpeedShowView ssv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speed_show_view);
        SeekBar mSeekBar = (SeekBar) findViewById(R.id.self_create_speed_seekbar);
        ssv = (SpeedShowView) findViewById(R.id.self_create_speed_speed_show_view);
        int max = ssv.getMaxSpeed();
        mSeekBar.setMax(max);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i(TAG, "onProgressChanged: progress = "+ progress);
                ssv.setSpeed(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
