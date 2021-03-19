package com.example.colorist.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.colorist.MyInterface
import com.example.colorist.R
import java.util.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

var count = 0

class ColoristFragment : Fragment() {
    
    private var param1: String? = null
    private var param2: String? = null
    private val random = Random()
    private lateinit var button_one: Button
    private lateinit var button_two: Button
    private lateinit var count_textView: TextView

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
        val root =  inflater.inflate(R.layout.fragment_colorist, container, false)
        val trueColor = Color.argb(255,random.nextInt(256),random.nextInt(256),random.nextInt(256))
        val falseColor = Color.argb(255,random.nextInt(256),random.nextInt(256),random.nextInt(256))
        root.findViewById<ViewGroup>(R.id.fragment_colorist).setBackgroundColor(trueColor)
        val randomButton = random.nextInt(2) + 1
        button_one = root.findViewById(R.id.buttom1)
        button_two = root.findViewById(R.id.buttom2)
        count_textView = root.findViewById(R.id.textView_count)
        count_textView.setText(count.toString())
        setText(randomButton,trueColor,falseColor)
        return root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ColoristFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun setText(randomButton: Int, trueColor: Int, falseColor: Int) {
        if (randomButton == 1){
            Log.d("Hear","hear")
            button_one.setText(trueColor.toString())
            button_two.setText(falseColor.toString())
            button_one.setOnClickListener {
                count_textView.setText((++count).toString())
                (requireActivity() as MyInterface).makeCurrentFragment(ColoristFragment())
            }
            button_two.setOnClickListener {
                count_textView.setText((--count).toString())
                (requireActivity() as MyInterface).makeCurrentFragment(ColoristFragment())
            }
        }
        if (randomButton == 2){
            button_two.setText(trueColor.toString())
            button_one.setText(falseColor.toString())
            button_one.setOnClickListener {
                count_textView.setText((--count).toString())
                (requireActivity() as MyInterface).makeCurrentFragment(ColoristFragment())
            }
            button_two.setOnClickListener {
                count_textView.setText((++count).toString())
                (requireActivity() as MyInterface).makeCurrentFragment(ColoristFragment())
            }
        }
    }
}