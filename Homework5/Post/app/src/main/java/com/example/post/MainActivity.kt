package com.example.post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.post.adapters.PostAdapter
import com.example.post.utils.PostS_LIST
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(this,3)
        recyclerView.adapter =
            PostAdapter(PostS_LIST) {
                val intent = MainActivity2.newIntent(this, it)
                startActivity(intent)
            }
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.background = null

    }
}