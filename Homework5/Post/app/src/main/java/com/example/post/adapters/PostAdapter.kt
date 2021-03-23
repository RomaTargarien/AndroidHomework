package com.example.post.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.post.utils.Post
import com.example.post.R

class PostAdapter(private val list: List<Post>, private val onClick:(Post)->Unit): RecyclerView.Adapter<PostAdapter.PostHolder>() {

    class PostHolder(view: View,private val onClick: (Post) ->Unit): RecyclerView.ViewHolder(view){
        var imageView: ImageView = view.findViewById(R.id.imageView_item)
        fun bind(image: Post) {
            imageView.setImageResource(image.image)
            imageView.setOnClickListener {
                onClick(image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_photo,parent,false)
        return PostHolder(view, onClick)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.bind(list[position])
    }
}