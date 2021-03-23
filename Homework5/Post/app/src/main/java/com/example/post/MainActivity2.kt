package com.example.post

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.post.adapters.CommentAdapter
import com.example.post.utils.Post

private const val TAG = "PostInfo"

class MainActivity2 : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var likes_textView: TextView
    private lateinit var recyclerView_comments: RecyclerView
    private lateinit var view_heart: View
    private var flag: Boolean = true

    companion object {
        fun newIntent(packageContext: Context, PostInfo: Post): Intent {
            return Intent(packageContext, MainActivity2::class.java).apply {
                putExtra(TAG,PostInfo)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        imageView = findViewById(R.id.imageView_2)
        likes_textView = findViewById(R.id.likes_textView)
        view_heart = findViewById(R.id.view_heart)
        recyclerView_comments = findViewById(R.id.recycler_view_comments)

        val post = (intent.getSerializableExtra(TAG) as Post)
        imageView.setImageResource(post.image)
        likes_textView.text = post.amountOfLikes.toString()

        recyclerView_comments.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        recyclerView_comments.adapter = post.comments?.let { CommentAdapter(it) }
        view_heart.setOnClickListener {
            if (flag){
                it.alpha = 1F
                likes_textView.text = (likes_textView.text.toString().toInt() + 1).toString()
                flag = false
            }
            else {
                it.alpha = 0.3F
                likes_textView.text = (likes_textView.text.toString().toInt() - 1).toString()
                flag = true
            }
        }
    }
}