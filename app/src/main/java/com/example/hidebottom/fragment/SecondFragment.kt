package com.example.hidebottom.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.hidebottom.R
import com.example.hidebottom.adapter.PagerPhotoListAdapter
import com.example.hidebottom.bean.Hit

class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager2 = view.findViewById<ViewPager2>(R.id.viewPager)
        val textView = view.findViewById<TextView>(R.id.photoTag)
        val left = view.findViewById<Button>(R.id.left)
        val right = view.findViewById<Button>(R.id.right)
        val photoList = arguments?.getParcelableArrayList<Hit>("PHOTO_LIST")

        PagerPhotoListAdapter().apply {
            viewPager2.adapter = this
            submitList(photoList)
        }

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                textView.text = "${position + 1}/${photoList?.size}"
            }
        })

        //viewPager2.isUserInputEnabled = false               //viewpager是否可以滑动

        /*left.setOnClickListener {
            viewPager2.setCurrentItem(viewPager2.currentItem - 1, true)
        }
        right.setOnClickListener {
            viewPager2.setCurrentItem(viewPager2.currentItem + 2, true)
        }*/

        viewPager2.setCurrentItem(arguments?.getInt("PHOTO_POS") ?: 0, false)
    }

}