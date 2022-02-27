package com.pm.woltersgram.feature_media.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.pm.woltersgram.core.util.formatDate
import com.pm.woltersgram.databinding.ItemUserMediaBinding
import com.pm.woltersgram.feature_media.domain.model.media.ChildrenData
import com.pm.woltersgram.feature_media.domain.model.media.MediaData

class UserMediaAdapter internal constructor() :
    ListAdapter<MediaData, UserMediaAdapter.ViewHolder>(PostDiffCallback) {

    companion object {
        const val TAG = "UserMediaAdapter"
        const val TIMESTAMP_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemUserMediaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).apply {
            with(holder.binding) {
                itemMediaUsername.text = username
                itemMediaDate.text =
                    timestamp?.formatDate(TIMESTAMP_DATE_FORMAT)

                when (mediaType) {
                    MediaData.MediaType.CAROUSEL_ALBUM -> {
                        itemCarouselAlbum(this@apply.children)
                    }
                    MediaData.MediaType.IMAGE -> {
                        itemImage(this@apply.mediaUrl)
                    }
                    else -> Log.d(TAG, "Not supported Media Type.")
                }
            }

        }
    }

    private fun ItemUserMediaBinding.itemCarouselAlbum(children: List<ChildrenData>?) {
        val imageUrls = children?.map { it.mediaUrl.orEmpty() } ?: arrayListOf()
        itemMediaImageViewpager.offscreenPageLimit = 1
        itemMediaImageViewpager.adapter =
            UserMediaImageAdapter(imageUrls = imageUrls)
        itemMediaImageIndicator.visibility = View.VISIBLE
        // Connecting the ViewPager2 with the Indicator
        TabLayoutMediator(
            itemMediaImageIndicator,
            itemMediaImageViewpager
        ) { tab: TabLayout.Tab,
            _: Int ->
            // Disable direct click on the Indicator
            tab.view.isClickable = false
        }.attach()
    }

    private fun ItemUserMediaBinding.itemImage(mediaUrl: String?) {
        itemMediaImageIndicator.visibility = View.GONE
        itemMediaImageViewpager.adapter =
            UserMediaImageAdapter(imageUrls = arrayListOf(mediaUrl.orEmpty()))
    }

    inner class ViewHolder(val binding: ItemUserMediaBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}

object PostDiffCallback : DiffUtil.ItemCallback<MediaData>() {

    override fun areItemsTheSame(oldItem: MediaData, newItem: MediaData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MediaData, newItem: MediaData): Boolean {
        return oldItem.id == newItem.id
    }
}