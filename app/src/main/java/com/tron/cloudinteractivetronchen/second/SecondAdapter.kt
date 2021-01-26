package com.tron.cloudinteractivetronchen.second

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tron.cloudinteractivetronchen.data.Photo
import com.tron.cloudinteractivetronchen.databinding.ItemListPhotoBinding

class SecondAdapter(private val itemClickListener: PhotoOnItemClickListener)
    : ListAdapter<Photo, RecyclerView.ViewHolder>(PhotoDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PhotoViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is PhotoViewHolder ->{
                val photo = item as Photo
                holder.bind(photo)
                holder.binding.imageView.setOnClickListener {
                    itemClickListener.onItemClicked(photo)
                }
            }
        }

    }

    class PhotoDiffCallback : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }
    }

    class PhotoViewHolder(val binding: ItemListPhotoBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: Photo) {
            binding.photo = photo
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): PhotoViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemListPhotoBinding.inflate(layoutInflater, parent, false)
                return PhotoViewHolder(binding)
            }
        }
    }

    class PhotoOnItemClickListener(val clickListener: (photo: Photo) -> Unit ){
        fun onItemClicked(photo: Photo) = clickListener(photo)
    }
}