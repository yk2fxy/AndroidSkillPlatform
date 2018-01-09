package com.yk.skill.androidskillplatform;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

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
        getPermision();
        goToIndex();
    }

    private void getPermision() {
        int p = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if(p== PackageManager.PERMISSION_DENIED){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Toast.makeText(MainActivity.this,"no permision",Toast.LENGTH_LONG).show();
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},10);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Toast.makeText(MainActivity.this,"request ok",Toast.LENGTH_LONG).show();
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
