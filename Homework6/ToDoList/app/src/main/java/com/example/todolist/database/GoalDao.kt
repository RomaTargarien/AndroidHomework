package com.example.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todolist.Goal
import java.util.*


@Dao
interface GoalDao {
    @Query("SELECT * FROM Goal WHERE date = (:date)")
    fun getGoalsDates(date: Date): LiveData<MutableList<Goal>>
    @Query("SELECT * FROM Goal WHERE id = (:id)")
    fun getGoal(id: UUID): LiveData<Goal?>
    @Insert
    fun addGoal(goal: Goal)
    @Update
    fun updateGoal(goal: Goal)
    @Delete
    fun delete(goal: Goal)
}