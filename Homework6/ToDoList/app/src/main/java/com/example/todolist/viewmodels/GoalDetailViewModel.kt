package com.example.todolist.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.todolist.Goal
import com.example.todolist.GoalRepository
import java.util.*

class GoalDetailViewModel: ViewModel() {
    private val goalRepository = GoalRepository.get()
    private val goalIdLiveData = MutableLiveData<UUID>()

    var goalLiveData: LiveData<Goal?> = Transformations.switchMap(goalIdLiveData){
        goalId -> goalRepository.getGoal(goalId)
    }
    fun loadGoal(goalId: UUID){
        goalIdLiveData.value = goalId
    }
    fun saveGoal(goal: Goal){
        goalRepository.updateGoal(goal)
    }

}