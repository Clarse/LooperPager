package com.example.looperpager

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val looperPagerAdapter = LooperPagerAdapter()

    companion object {
        private val sColors = mutableListOf<Int>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val random = Random()
        //准备数据
        for (index in 0 until 5) {
            sColors.add(
                Color.argb(
                    random.nextInt(255),
                    random.nextInt(255),
                    random.nextInt(255),
                    random.nextInt(255)
                )
            )
        }

        //给适配器设置数据
        looperPagerAdapter.setData(sColors)
        looper_pager.adapter = looperPagerAdapter
    }
}