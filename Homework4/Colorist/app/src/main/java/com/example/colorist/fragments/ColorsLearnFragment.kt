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


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ColorsLearnFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_colors_learn, container,false)
        root.findViewById<RecyclerView>(R.id.recycler_view).layoutManager =
            LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        root.findViewById<RecyclerView>(R.id.recycler_view).adapter = ColorsAdapter(ColorsForm().colorsList){
            (requireActivity() as MyInterface).openColor(it)
        }
        return root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ColorsLearnFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}