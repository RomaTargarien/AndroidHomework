package com.example.post.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.post.utils.Comment
import com.example.post.R

class CommentAdapter(private val list: List<Comment>) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    class CommentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var comment_text_view = view.findViewById<TextView>(R.id.comment_textView)
        var comment_Autor = view.findViewById<TextView>(R.id.comment_Autor)
        var comment_Post_autor_ImageView = view.findViewById<ImageView>(R.id.Post_autor_imageView)
        fun bind (comment: Comment){
            comment_Autor.text = comment.comment_autor
            comment_text_view.text = comment.text_of_comment
            comment_Post_autor_ImageView.setImageResource(comment.Post_of_autor)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.comment_layout,parent,false)
        return CommentViewHolder(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(list[position])
    }
}