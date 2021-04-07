package com.example.gifs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.gifs.api.GifItem

class GifsAdapter(val listGifs: List<GifItem>,private var onClick: (String) -> Unit) : RecyclerView.Adapter<GifsAdapter.GifsViewHolder>()
{
    class GifsViewHolder(view: View,private var onClick: (String) -> Unit) : RecyclerView.ViewHolder(view){
        private var gifItemImageView: ImageView = view.findViewById(R.id.gif_item)
        fun bind(gifItem: GifItem){
            setGif(gifItem.url)
            gifItemImageView.setOnClickListener {
                onClick(gifItem.url)
            }
        }

        private fun setGif(url: String) {
            Glide
                    .with(itemView)
                    .load(url)
                    .apply(RequestOptions()
                            .placeholder(R.drawable.placeholder)
                            .diskCacheStrategy(DiskCacheStrategy.NONE))
                    .into(gifItemImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_gif_item, parent,false)
        return GifsViewHolder(view,onClick)
    }

    override fun getItemCount(): Int = listGifs.size

    override fun onBindViewHolder(holder: GifsViewHolder, position: Int) {
        holder.bind(listGifs[position])
    }
}