package com.example.looperpager

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), MyViewPager.OnViewPagerTouchListener {

    private val looperPagerAdapter = LooperPagerAdapter()
    private val handler = Handler()
    private var mIsTouch = false

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
        looper_pager.setCurrentItem(looperPagerAdapter.getDataRealSize() * 100, false)
        looper_pager.setonViewPagerTouchListener(this)
    }

    private inner class mLooperTask : Runnable {
        override fun run() {
            if (!mIsTouch) {
                //切换viewpager里的图片到下一个
                var currentItem = looper_pager.currentItem
                looper_pager.setCurrentItem(++currentItem, true)
            }
            handler.postDelayed(this, 1000)
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        //当这个界面绑定到窗口的时候
        handler.post(mLooperTask())
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        handler.removeCallbacks(mLooperTask())
    }

    override fun onPagerTouch(isTouch: Boolean) {
        mIsTouch = isTouch
    }

}