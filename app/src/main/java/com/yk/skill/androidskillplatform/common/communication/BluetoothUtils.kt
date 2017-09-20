package com.yk.skill.androidskillplatform.common.communication

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Context

/**
 * Created by Administrator on 2017/9/8.
 */
class BluetoothUtils{
    lateinit var mContext:Context;
    fun open(context:Context):Boolean{
        mContext = context;
        var adapter = BluetoothAdapter.getDefaultAdapter();
        var bondedDevices = adapter.bondedDevices as Set<BluetoothDevice>
        for (b in bondedDevices){

        }
        return false;
    }
}