package com.example.gifs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.Script
import android.util.Log
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val isFragmentContainerEmpty = savedInstanceState == null
        if (isFragmentContainerEmpty){
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer,GifsListFragment())
                .commit()
        }
    }
}