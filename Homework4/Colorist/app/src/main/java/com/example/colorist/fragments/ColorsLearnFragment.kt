package com.example.colorist.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.colorist.*

class ColorsLearnFragment : Fragment() {

    private lateinit var recycler_view_colors: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_colors_learn, container,false)
        recycler_view_colors = root.findViewById(R.id.recycler_view_colors)
        recycler_view_colors.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        recycler_view_colors.adapter = ColorsAdapter(ColorView.COLORS_LIST){
            (requireActivity() as ColorInterface).openColor(it)
        }
        return root
    }
}

