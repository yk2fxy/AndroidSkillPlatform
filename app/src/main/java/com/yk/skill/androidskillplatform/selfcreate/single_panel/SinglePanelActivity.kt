package com.yk.skill.androidskillplatform.selfcreate.single_panel

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.yk.skill.androidskillplatform.R

import kotlinx.android.synthetic.main.activity_single_panel.*

class SinglePanelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_panel)
        single_panel_btn_single.setOnClickListener {
            SinglePanelWindow().create(baseContext,it)
        }
    }
}
