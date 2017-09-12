package com.yk.skill.androidskillplatform.normal.bluetooth

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.yk.skill.androidskillplatform.R
import com.yk.skill.androidskillplatform.R.layout.activity_bluetooth;
import kotlinx.android.synthetic.main.index_header_layout.*

class BluetoothActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bluetooth)
        header_text.text = "蓝牙";

    }
}
