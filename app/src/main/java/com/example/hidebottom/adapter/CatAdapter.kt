package com.example.hidebottom.adapter

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.hidebottom.R
import com.example.hidebottom.bean.Hit
import io.supercharge.shimmerlayout.ShimmerLayout
import kotlinx.android.synthetic.main.gallery_cell.view.*

/**
 * <pre>
 *     author : admin
 *     e-mail : xxx@xx
 *     time   : 2021/09/30
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class CatAdapter : PagingDataAdapter<Hit, CatAdapter.CatyViewHolder>(DIFFCALLBACK) {

    inner class CatyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var shimmerLayout = view.findViewById<ShimmerLayout>(R.id.shimLayout)
        var imageView = view.findViewById<ImageView>(R.id.imge)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gallery_cell, parent, false)
        val holder = CatyViewHolder(view)
        holder.itemView.setOnClickListener {
            Bundle().apply {
                putParcelableArrayList("PHOTO_LIST", ArrayList(snapshot()))
                putInt("PHOTO_POS", holder.absoluteAdapterPosition)
                holder.itemView.findNavController()
                    .navigate(R.id.action_firstFragment_to_secondFragment, this)
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: CatyViewHolder, position: Int) {
        holder.shimmerLayout.apply {
            setShimmerColor(0x55FFFFFF)
            setShimmerAngle(0)
            startShimmerAnimation()
        }
        holder.itemView.imge.layoutParams.height = getItem(position)!!.webformatHeight
        Glide.with(holder.itemView)
            .load(getItem(position)?.webformatURL)
            .placeholder(R.drawable.ic_photo_gray_24)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false.also { holder.shimmerLayout?.stopShimmerAnimation() }
                }

            })
            .into(holder.imageView)
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