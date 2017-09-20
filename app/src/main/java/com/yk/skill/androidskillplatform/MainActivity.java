package com.yk.skill.androidskillplatform;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.yk.skill.androidskillplatform.index.IndexActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //tinker
        TinkerInstaller.onReceiveUpgradePatch(this, Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"patch.apk");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        goToIndex();
    }

    private void goToIndex() {

        Intent intent = new Intent(MainActivity.this, IndexActivity.class);
        startActivity(intent);
        finish();
        //activity动画跳转的几种方法
        //http://blog.csdn.net/qq_23547831/article/details/51821159

        overridePendingTransition(R.anim.slide_in_left_self, R.anim.slide_in_left_self);
        //overridePendingTransition(R.anim.slide_in_left_self,android.R.anim.slide_in_left);
    }
}
