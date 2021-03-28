package com.example.todolist

import android.app.Application

class GoalIntentApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        GoalRepository.inititialize(this)
    }
}