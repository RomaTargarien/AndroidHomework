package com.example.colorist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class ColorsAdapter(private val colors: List<ColorView>,
                    private val onClick: (ColorView) -> Unit)
    : RecyclerView.Adapter<ColorsAdapter.ColorViewHolder>() {

    class ColorViewHolder(view: View, private val onClick: (ColorView) -> Unit) : RecyclerView.ViewHolder(view){
        var root: View = view.findViewById(R.id.color)
        var text: TextView = view.findViewById(R.id.text_color)
        fun bind (color : ColorView){
            root.setBackgroundColor(color.color)
            text.setText(color.name)
            root.setOnClickListener {
                onClick(color)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_color, parent, false)
        return ColorViewHolder(view,onClick)
    }

    override fun getItemCount(): Int {
        return colors.size
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bind(colors[position])
    }
}