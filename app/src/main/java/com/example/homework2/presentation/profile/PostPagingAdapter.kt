package com.example.homework2.presentation.profile

import android.icu.text.SimpleDateFormat
import android.opengl.Visibility
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.homework2.data.model.Post
import com.example.homework2.databinding.ViewCardBinding
import java.util.Date
import javax.inject.Inject

class PostPagingAdapter @Inject constructor() :
    PagingDataAdapter<Post, PostPagingAdapter.DataViewHolder>(diffUtilCallback) {

    private var onClick: (Post) -> Unit = {}
    fun setCallback(callback: (Post) -> Unit) {
        this.onClick = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ViewCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class DataViewHolder(
        private val binding: ViewCardBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Post) {
            with(binding) {
                textViewPostText.text = item.text
                textViewFavoriteNumber.text = item.likes.likesCount.toString()
                textViewName.text = item.owner.username
                imageViewRounded.load(item.owner.avatarUrl)
                shapeableImageViewFavorite.setOnClickListener {
                    onClick.invoke(item)
                }
                try {
                    imageViewPostImage.load(item.images[0].sizes[0].url)
                } catch (e: Exception) {
                    imageViewPostImage.visibility = View.GONE
                    Log.i(e.message, item.images.size.toString())
                }


                textViewDate.text =
                    SimpleDateFormat("MMMM d,yyyy H:mm:s").format(Date(item.dateCreated))
                root.setOnClickListener {
                    onClick.invoke(item)
                }
            }

        }
    }
}

private val diffUtilCallback = object : DiffUtil.ItemCallback<Post>() {

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }
}