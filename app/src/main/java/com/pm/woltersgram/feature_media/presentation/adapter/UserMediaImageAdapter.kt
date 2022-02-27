package com.pm.woltersgram.feature_media.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.pm.woltersgram.R
import com.pm.woltersgram.databinding.ItemMediaImageBinding

class UserMediaImageAdapter(private val imageUrls: List<String>) :
    RecyclerView.Adapter<UserMediaImageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserMediaImageAdapter.ViewHolder {
        val binding =
            ItemMediaImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserMediaImageAdapter.ViewHolder, position: Int) {
        imageUrls[position].let { imageUrl ->
            holder.mediaImage.load(imageUrl) {
                placeholder(R.drawable.ic_launcher_foreground)
            }
        }
    }

    override fun getItemCount(): Int {
        return imageUrls.size
    }

    inner class ViewHolder(binding: ItemMediaImageBinding) : RecyclerView.ViewHolder(binding.root) {
        internal val mediaImage: ImageView = binding.itemImage
    }
}