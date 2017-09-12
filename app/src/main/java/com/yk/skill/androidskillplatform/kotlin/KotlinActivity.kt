package com.yk.skill.androidskillplatform.kotlin

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

import com.yk.skill.androidskillplatform.R
import kotlinx.android.synthetic.main.activity_kotlin.*

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        var p = Person(13,"zhangsan");
        var button = findViewById(R.id.button) as Button;

        button.setOnClickListener(View.OnClickListener {
            Toast.makeText(this,"ok",Toast.LENGTH_SHORT).show();
            animiation(this);
           // var intent = Intent
        })
        /*kotlin_show.setOnClickListener(View.OnClickListener {
            Toast.makeText(this,"kotlin_show",Toast.LENGTH_SHORT).show();
        })*/
        textView.text = p.toString();
        kotlin_show.setOnClickListener({
            var p2 = p.copy(p.age++);
            textView.text = p2.toString();
            toast("扩展函数",Toast.LENGTH_SHORT)
        });
    }

    fun Context.toast(message:String,duration:Int){
        Toast.makeText(this,message,duration).show();
    }
    private fun animiation(kotlinActivity: KotlinActivity) {
        var anim = AlphaAnimation(0.1f,1.0f);
        anim.duration = 3000;

        var text = findViewById(R.id.textView) as TextView;
        text.setText("this is kotlin");
        text.startAnimation(anim);
    }
}
