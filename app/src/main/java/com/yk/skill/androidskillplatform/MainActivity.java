package com.yk.skill.androidskillplatform;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.yk.skill.androidskillplatform.index.IndexActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
