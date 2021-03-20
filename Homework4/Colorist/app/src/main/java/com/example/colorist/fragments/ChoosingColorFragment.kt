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


private const val PARAM_COLORVIEW = "param_colorView"

class ChoosingColorFragment : Fragment() {

    private var colorView: ColorView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            colorView = it.get(PARAM_COLORVIEW) as ColorView?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =  inflater.inflate(R.layout.fragment_choosing_color, container, false)
        root.findViewById<ViewGroup>(R.id.choosing_color_fragment).setBackgroundColor(colorView?.color!!)
        root.findViewById<TextView>(R.id.choosing_color_text).setText(colorView?.name)
        return root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: ColorView) =
            ChoosingColorFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(PARAM_COLORVIEW, param1)
                }
            }
    }
}