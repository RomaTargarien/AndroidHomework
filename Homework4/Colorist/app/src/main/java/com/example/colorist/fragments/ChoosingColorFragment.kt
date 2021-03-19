package com.example.colorist.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.colorist.ColorView
import com.example.colorist.R
import java.io.Serializable


private const val ARG_PARAM1 = "param1"


class ChoosingColorFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: ColorView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.get(ARG_PARAM1) as ColorView?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =  inflater.inflate(R.layout.fragment_choosing_color, container, false)
        root.findViewById<ViewGroup>(R.id.choosing_color_fragment).setBackgroundColor(param1?.color!!)
        root.findViewById<TextView>(R.id.choosing_color_text).setText(param1?.name)
        return root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: ColorView) =
            ChoosingColorFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}