package com.yk.skill.androidskillplatform.other.tinker

import java.io.File

/**
 * Created by Administrator on 2018/1/5.
 */
class TinkerCalcModel {
    fun calc(one:Int,two:Int):Int{
        return one/two
    }
    //检测报是否存在
    fun testDir(path:String):Boolean{
        var file = File(path)
        return !file.exists()
    }
}