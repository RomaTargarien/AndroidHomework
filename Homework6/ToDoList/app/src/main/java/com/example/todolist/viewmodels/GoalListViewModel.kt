package com.example.todolist.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.Goal
import com.example.todolist.GoalRepository
import java.util.*

class GoalListViewModel : ViewModel() {
    private val goalRepository = GoalRepository.get()
    fun addGoal(goal: Goal){
        goalRepository.addGoal(goal)
    }
    fun getGoalsListLiveData(date: Date): LiveData<MutableList<Goal>>{
        return goalRepository.getGoalsDate(date)
    }
}