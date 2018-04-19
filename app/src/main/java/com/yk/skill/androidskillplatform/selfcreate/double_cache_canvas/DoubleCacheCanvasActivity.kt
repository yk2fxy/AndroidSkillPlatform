package com.yk.skill.androidskillplatform.selfcreate.double_cache_canvas

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.yk.skill.androidskillplatform.R
import kotlinx.android.synthetic.main.activity_double_cache_canvas.*

class DoubleCacheCanvasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_double_cache_canvas)
        double_cache_canvas_btn_save.setOnClickListener {
            double_cache_canvas_view.saveBmp()
        }
      /*  double_cache_canvas_btn_erase.setOnClickListener{
            *//*double_cache_canvas_view.paintStyle = if(double_cache_canvas_view.paintStyle == SingleCacheCanvasView.PAINT_STYLE_ERASE){
                SingleCacheCanvasView.PAINT_STYLE_PAINT
            }else{
                SingleCacheCanvasView.PAINT_STYLE_ERASE
            }*//*
        }*/
    }
}
