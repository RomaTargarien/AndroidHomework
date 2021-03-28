package com.example.todolist.adapters

import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

class DateAdapter(var date: List<LocalDate>,private val onClick:(Date)-> Unit) : RecyclerView.Adapter<DateAdapter.DateViewHolder>() {

    class DateViewHolder(view: View, private val onClick:(Date)-> Unit) : RecyclerView.ViewHolder(view){
        private val date_month: TextView = view.findViewById(R.id.date_month)
        private val date_day: TextView = view.findViewById(R.id.date_day)
        private val date_day_of_week: TextView = view.findViewById(R.id.date_day_of_week)
        private val card_date: CardView = view.findViewById(R.id.card_date)
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(localdate: LocalDate){
            date_month.setText(localdate.month.toString())
            date_day_of_week.setText(localdate.dayOfWeek.toString())
            date_day.setText(localdate.dayOfMonth.toString())
            card_date.setOnClickListener {
                val date = Date.from(localdate.atStartOfDay(ZoneId.systemDefault()).toInstant())
                onClick(date)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.to_do_date_item,parent,false)
        return DateViewHolder(view,onClick)
    }

    override fun getItemCount() = date.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        holder.bind(date[position])
    }
}