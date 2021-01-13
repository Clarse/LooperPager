package com.example.looperpager

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), MyViewPager.OnViewPagerTouchListener,
    ViewPager.OnPageChangeListener {

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
        looper_pager.setonViewPagerTouchListener(this)
        looper_pager.addOnPageChangeListener(this)
        //根据图片的数量去添加点的数量
        insertPoint()
        looper_pager.setCurrentItem(looperPagerAdapter.getDataRealSize() * 100, false)
    }

    private fun insertPoint() {
        repeat(sColors.size) {
            val point = View(this)
            val mLayoutParams = LinearLayout.LayoutParams(30, 30)
            mLayoutParams.leftMargin = 20
            point.background = ContextCompat.getDrawable(this, R.drawable.shape_point_normal)
            point.layoutParams = mLayoutParams
            points_container.addView(point)
        }
    }

    private inner class mLooperTask : Runnable {
        override fun run() {
            if (!mIsTouch) {
                //切换viewpager里的图片到下一个
                var currentItem = looper_pager.currentItem
                looper_pager.setCurrentItem(++currentItem, true)
            }
            handler.postDelayed(this, 3000)
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

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        //viewPager停下来以后选中的位置
        val realPosition: Int = if (looperPagerAdapter.getDataRealSize() != 0) {
            position % looperPagerAdapter.getDataRealSize()
        } else {
            0
        }
        setSelectPoint(realPosition)
    }

    private fun setSelectPoint(realPosition: Int) {
        repeat(points_container.childCount) {
            val point = points_container.getChildAt(it)
            if (it != realPosition) {
                //如果不等于就是未选中的
                point.background = ContextCompat.getDrawable(this, R.drawable.shape_point_normal)
            } else {
                point.background = ContextCompat.getDrawable(this, R.drawable.shape_point_selected)
            }
        }
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

}