package com.example.looperpager

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class MyViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {

    private lateinit var mTouchListener: MyViewPager.OnViewPagerTouchListener

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                if (::mTouchListener.isInitialized) {
                    mTouchListener.onPagerTouch(true)
                }
            }
            MotionEvent.ACTION_CANCEL -> {
                if (::mTouchListener.isInitialized) {
                    mTouchListener.onPagerTouch(false)
                }
            }
            MotionEvent.ACTION_UP -> {
                if (::mTouchListener.isInitialized) {
                    mTouchListener.onPagerTouch(false)
                }
            }
        }
        return super.onTouchEvent(ev)
    }

    fun setonViewPagerTouchListener(listener: OnViewPagerTouchListener) {
        this.mTouchListener = listener
    }

    interface OnViewPagerTouchListener {
        fun onPagerTouch(isTouch: Boolean)
    }
}