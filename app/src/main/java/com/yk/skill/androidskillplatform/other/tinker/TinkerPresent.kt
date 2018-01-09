package com.yk.skill.androidskillplatform.other.tinker

import android.content.Context
import android.os.Environment
import android.util.Log
import android.widget.Toast
import com.tencent.tinker.lib.tinker.TinkerInstaller
import java.io.File

/**
 * Created by Administrator on 2018/1/5.
 */
class TinkerPresent{

    var tinkerCalc: TinkerCalcModel? = null
    private lateinit var tinkerView: ITinkerView
    private var context: Context? = null;
    private var patchPkgPath:String? = null;

    constructor(tinkerView: ITinkerView,context: Context){
        this.tinkerView = tinkerView
        tinkerCalc = TinkerCalcModel()
        this.context = context
        patchPkgPath = Environment.getExternalStorageDirectory().path + File.separator+"skill"
    }
    //计算，用于测试tinker的热更新
    fun calc(){
        tinkerView.setCalcResult(tinkerCalc?.calc(tinkerView.getOne(),tinkerView.getTwo())!!)
    }

    //读取热更新的包--加
    fun loadAddPatch() {
        if(tinkerCalc?.testDir(this!!.patchPkgPath!!)!!){
            return
        }
        var file = File(patchPkgPath+"/add.apk")
        if(file.exists()){
            Log.i("tinker","oik");
           // Toast.makeText(context,"ok",Toast.LENGTH_LONG).show()
        }
        if(context==null){
            Log.i("tinker","context is null")
        }
        TinkerInstaller.onReceiveUpgradePatch(context,patchPkgPath+"/add.apk")
    }
    //读取热更新的包--减
    fun loadSubPatch(){
        if(tinkerCalc?.testDir(this!!.patchPkgPath!!)!!){
            Toast.makeText(context,"wrong",Toast.LENGTH_LONG).show()
            return
        }
        TinkerInstaller.onReceiveUpgradePatch(context,patchPkgPath+"/sub.apk")
    }
    //读取热更新的包--乘
    fun loadMultPatch(){
        if(tinkerCalc?.testDir(this!!.patchPkgPath!!)!!){
            return
        }
        TinkerInstaller.onReceiveUpgradePatch(context,patchPkgPath+"/mult.apk")
    }
    //读取热更新的包--除
    fun loadExceptPatch(){
        if(tinkerCalc?.testDir(this!!.patchPkgPath!!)!!){
            return
        }
        TinkerInstaller.onReceiveUpgradePatch(context,patchPkgPath+"/except.apk")

    }

}