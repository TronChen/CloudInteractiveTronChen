package com.tron.cloudinteractivetronchen.second



import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tron.cloudinteractivetronchen.data.CloudRepository
import com.tron.cloudinteractivetronchen.data.Photo
import com.tron.cloudinteractivetronchen.databinding.ItemListPhotoBinding
import com.tron.cloudinteractivetronchen.networks.LoadApiStatus


class SecondAdapter(private val itemClickListener: PhotoOnItemClickListener, private val viewModel: SecondViewModel)
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

                if (viewModel.photoListMap[position] == null){
                    holder.binding.imageView.setImageBitmap(null)
                    viewModel.retrieveBitmapFromCache(photo, position - 1)
                    viewModel._status.value = LoadApiStatus.LOADING
                }else{
                    holder.binding.imageView.setImageBitmap(viewModel.photoListMap[position - 1])
                    viewModel._status.value = LoadApiStatus.DONE
                }

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
            Log.e("Bitmap", photo.bitmap.toString())
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




