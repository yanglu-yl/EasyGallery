package com.example.hidebottom.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hidebottom.R
import com.example.hidebottom.bean.Hit
import kotlinx.android.synthetic.main.pager_photo_view.view.*

/**
 * <pre>
 *     author : admin
 *     e-mail : xxx@xx
 *     time   : 2021/10/09
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class PagerPhotoListAdapter :
    ListAdapter<Hit, PagerPhotoListAdapter.PagerPhotoListViewHolder>(DIFFCALLBACK) {

    inner class PagerPhotoListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerPhotoListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.pager_photo_view, parent, false)
        return PagerPhotoListViewHolder(view)
    }

    override fun onBindViewHolder(holder: PagerPhotoListViewHolder, position: Int) {
        Glide.with(holder.itemView).load(getItem(position).largeImageURL)
            .placeholder(R.drawable.ic_photo_gray_24).into(holder.itemView.imageView as ImageView)
    }

    object DIFFCALLBACK : DiffUtil.ItemCallback<Hit>() {
        override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem == newItem
        }
    }
}