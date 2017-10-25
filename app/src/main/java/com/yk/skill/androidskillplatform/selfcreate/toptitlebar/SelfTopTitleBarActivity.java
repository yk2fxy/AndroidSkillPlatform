package com.yk.skill.androidskillplatform.selfcreate.toptitlebar;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yk.skill.androidskillplatform.R;

/**
 * Created by Administrator on 2017/10/25.
 */

public class SelfTopTitleBarActivity extends Activity{
    Button cancelBtn;
    Button okBtn;
    TextView title;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toptitlebar_activity);
        cancelBtn = (Button) findViewById(R.id.title_cancel_action);
        okBtn = (Button) findViewById(R.id.title_ok_action);
        title = (TextView) findViewById(R.id.title_title_tv);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast("cancel");
            }
        });
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast("ok");
            }
        });
        TopTitleBarSelfView ttbsv = (TopTitleBarSelfView) findViewById(R.id.title_bar_topselfview);
        ttbsv.setListener(new TitleBarClickListener() {
            @Override
            public void cancel() {
                toast("取消");
            }

            @Override
            public void ok() {
                toast("完成");
            }
        });
    }
    public void toast(String text){
        Toast.makeText(SelfTopTitleBarActivity.this,text,Toast.LENGTH_SHORT).show();
    }
}
