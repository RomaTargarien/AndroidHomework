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
import com.example.colorist.ColorInterface
import com.example.colorist.R
import java.util.*

private var count = 0
private const val STRING_NEX_CHARS = "0123456789abcdf"

class ColoristFragment : Fragment() {

    private val random = Random()
    private lateinit var button_one: Button
    private lateinit var button_two: Button
    private lateinit var textView_count: TextView
    private lateinit var backgroung_view_group: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root =  inflater.inflate(R.layout.fragment_colorist, container, false)
        button_one = root.findViewById(R.id.buttom_one)
        button_two = root.findViewById(R.id.buttom_two)
        textView_count = root.findViewById(R.id.textView_count)
        backgroung_view_group = root.findViewById(R.id.fragment_colorist)
        textView_count.setText(count.toString())
        playColorist()
        return root
    }

    fun playColorist() {
        val trueColor = createColor()
        val falseColor = createColor()
        val randomButton = random.nextInt(2) + 1
        backgroung_view_group.setBackgroundColor(Color.parseColor(trueColor))
        if (randomButton == 1){
            button_one.setText(trueColor)
            button_two.setText(falseColor)
            button_one.setOnClickListener {
                textView_count.setText((++count).toString())
                playColorist()
            }
            button_two.setOnClickListener {
                textView_count.setText((--count).toString())
                playColorist()
            }
        }
        if (randomButton == 2){
            button_two.setText(trueColor)
            button_one.setText(falseColor)
            button_one.setOnClickListener {
                textView_count.setText((--count).toString())
                playColorist()
            }
            button_two.setOnClickListener {
                textView_count.setText((++count).toString())
                playColorist()
            }
        }
    }

    fun createColor(): String {
        var color = "#"
        for (i in 0..5){
            color += STRING_NEX_CHARS[random.nextInt(14)]
        }
        return color
    }
}