package com.example.post

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.post.adapters.PostAdapter
import com.example.post.utils.PostS_LIST
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.internal.NavigationMenu

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar


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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }
}