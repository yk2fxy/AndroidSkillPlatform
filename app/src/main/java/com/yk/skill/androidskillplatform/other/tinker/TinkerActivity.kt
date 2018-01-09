package com.yk.skill.androidskillplatform.other.tinker

import android.os.Bundle
import android.os.Environment
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.tencent.tinker.lib.tinker.TinkerInstaller
import com.yk.skill.androidskillplatform.R

import kotlinx.android.synthetic.main.activity_tinker.*
import kotlinx.android.synthetic.main.content_tinker.*

class TinkerActivity : AppCompatActivity(),ITinkerView {
    var mTinkerPresent: TinkerPresent? = null;
    override fun setCalcResult(result: Int) {
        tinker_calc_result.text = result.toString()
    }

    override fun getOne(): Int {
       // var t = tinker_calc_one.text.toString();
        return tinker_calc_one.text.toString().toInt();
    }

    override fun getTwo(): Int {
        return tinker_calc_two.text.toString().toInt()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tinker)
        setSupportActionBar(toolbar)
        mTinkerPresent = TinkerPresent(this,applicationContext)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        tinker_calc_btn.setOnClickListener {
            mTinkerPresent?.calc();
        }
        //加
        tinker_load_patched_add_btn.setOnClickListener {
           mTinkerPresent?.loadAddPatch();
            //TinkerInstaller.onReceiveUpgradePatch(applicationContext,Environment.getExternalStorageDirectory().absolutePath+"/patch_signed_7zip.apk")
        }
        //除
        tinker_load_patched_except_btn.setOnClickListener {
            mTinkerPresent?.loadExceptPatch()
        }
        //乘
        tinker_load_patched_mult_btn.setOnClickListener {
            mTinkerPresent?.loadMultPatch()
        }
        //减
        tinker_load_patched_sub_btn.setOnClickListener {
            mTinkerPresent?.loadSubPatch()
        }
    }

}
