package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class MainActivity : AppCompatActivity(),ToDoListFragment.Callbacks,GoalFragment.CallBackTolist {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment == null){
            val fragment = ToDoListFragment()
            supportFragmentManager.beginTransaction().add(R.id.fragment_container,fragment).commit()
        }
    }

    override fun onGoalSelected(goalUI: UUID) {
        val fragment = GoalFragment.newInstance(goalUI)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun callback(date: Date) {
        val fragment = ToDoListFragment.newInstance(date)
        supportFragmentManager.beginTransaction().add(R.id.fragment_container,fragment).commit()
    }
}