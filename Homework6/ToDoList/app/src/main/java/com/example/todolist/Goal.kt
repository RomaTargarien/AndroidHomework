package com.example.todolist

import android.icu.text.CaseMap
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Goal(@PrimaryKey val id: UUID=  UUID.randomUUID(),
                var title: String = "",
                var description: String = "",
                var isDone: Boolean = false,
                var date: Date = Date())