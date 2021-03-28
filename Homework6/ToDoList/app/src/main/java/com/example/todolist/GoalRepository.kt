package com.example.todolist

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.todolist.database.GoalDatabase
import java.lang.IllegalArgumentException
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "goal-database"

class GoalRepository private constructor(context: Context) {

    private val database: GoalDatabase = Room.databaseBuilder(
        context.applicationContext,
        GoalDatabase::class.java,
        DATABASE_NAME).build()
    private val executor = Executors.newSingleThreadExecutor()
    private val goalDao =database.goalDao()
    fun getGoalsDate(date: Date): LiveData<MutableList<Goal>> = goalDao.getGoalsDates(date)
    fun deleteGoal(goal: Goal){
        executor.execute{
            goalDao.delete(goal)
        }
    }
    fun addGoal(goal: Goal){
        executor.execute{
            goalDao.addGoal(goal)
        }
    }
    fun getGoal(id: UUID): LiveData<Goal?> = goalDao.getGoal(id)
    fun updateGoal(goal: Goal){
        executor.execute{
            goalDao.updateGoal(goal)
        }
    }

    companion object{
        private var INSTANCE: GoalRepository? = null
        fun inititialize(context: Context){
            if (INSTANCE == null){
                INSTANCE = GoalRepository(context)
            }
        }
        fun get(): GoalRepository{
            return INSTANCE?: throw IllegalArgumentException("CrimeRepository must be initialized")
        }
    }
}