package com.example.todolist.adapters


import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.Goal
import com.example.todolist.GoalRepository
import com.example.todolist.R
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class ToDoListAdapter(var list: MutableList<Goal>,private val onClick:(Goal) -> Unit) : RecyclerView.Adapter<ToDoListAdapter.ToDoListViewHolder>() {

    class ToDoListViewHolder(view: View,private val onClick:(Goal) -> Unit) : RecyclerView.ViewHolder(view) {
        private val tittle: TextView = view.findViewById(R.id.title)
        private val description: TextView = view.findViewById(R.id.description)
        private val date: TextView = view.findViewById(R.id.date)
        private val goal_card: CardView = view.findViewById(R.id.goal_card)
        private val done_imageView: ImageView = view.findViewById(R.id.done_imageView)
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(goal: Goal){
            tittle.setText(goal.title)
            description.setText(goal.description)
            date.setText(convertDate(goal.date))
            done_imageView.visibility = if (goal.isDone){
                View.VISIBLE
            } else {
                View.INVISIBLE
            }

            goal_card.setOnClickListener {
                onClick(goal)
            }
        }
        @RequiresApi(Build.VERSION_CODES.O)
        private fun convertDate(date: Date): String{
            val apiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val timestampAsDateString = DateTimeFormatter.ISO_INSTANT
                    .format(Instant.ofEpochSecond(date.time/1000))
            val localdate = LocalDate.parse(timestampAsDateString,apiFormat)
            return "${localdate.dayOfMonth} ${localdate.month}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.to_do_thing_item, parent, false)
        return ToDoListViewHolder(view,onClick)
    }

    override fun getItemCount() = list.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {
        holder.bind(list[position])
    }
    fun removeItem(position: Int) {
        val repository = GoalRepository.get()
        repository.deleteGoal(list[position])
        list.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, list.size)
    }
}