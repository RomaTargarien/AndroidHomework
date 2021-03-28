package com.example.todolist.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todolist.Goal

@Database(entities = [Goal::class],version = 1)
@TypeConverters(GoalTypeConvector::class)
abstract class GoalDatabase : RoomDatabase(){
    abstract fun goalDao(): GoalDao
}