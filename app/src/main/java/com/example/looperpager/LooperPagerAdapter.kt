package com.example.looperpager

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter

class LooperPagerAdapter : PagerAdapter() {

    private lateinit var mColors: MutableList<Int>

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(container.context)
        imageView.setBackgroundColor(mColors[position])
        container.addView(imageView)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

    override fun getCount(): Int {
        if (::mColors.isInitialized) {
            return mColors.size
        }
        return 0
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    fun setData(sColors: MutableList<Int>) {
        this.mColors = sColors
    }
}