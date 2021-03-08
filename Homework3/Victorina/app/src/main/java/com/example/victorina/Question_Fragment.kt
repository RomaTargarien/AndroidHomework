package com.example.victorina

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.PointerIcon
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import java.text.FieldPosition

private const val TEXT = "param1"
private const val POSITION = "param2"
private const val RESULT = "param3"
private var count = 0;

class Question_Fragment : Fragment() {

    private var text: String? = null
    private var position: Int? = null
    private var result: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            text = it.getString(TEXT)
            result = it.getBoolean(RESULT)
            position = it.getInt(POSITION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_question_, container,false)
        root.findViewById<TextView>(R.id.textViewQuestion).text = text
        return root
    }

    override fun onStart() {
        super.onStart()
        view?.findViewById<Button>(R.id.buttonYes)?.setOnClickListener {
            (requireActivity() as MyInterface).answerQuestion(position,result)
            if (result == true){
                count++
            }
            if (position == 10){
                (requireActivity() as MyInterface).finishQuestions(count.toString())
            }
        }
        view?.findViewById<Button>(R.id.buttonNo)?.setOnClickListener {
            (requireActivity() as MyInterface).answerQuestion(position,result)
            if (result == false){
                count++
            }
            if (position == 10){
                (requireActivity() as MyInterface).finishQuestions(count.toString())
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(text: String,position: Int,result: Boolean) =
            Question_Fragment().apply {
                arguments = Bundle().apply {
                    putBoolean(RESULT,result)
                    putInt(POSITION, position)
                    putString(TEXT, text)
                }
            }
    }
}