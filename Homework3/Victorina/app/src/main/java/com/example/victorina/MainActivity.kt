package com.example.victorina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class Questions(){
    val QUESTION_1 = "Пушкин умер в 1937 году"
    val QUESTION_2 = "Луна - это спутник земли"
    val QUESTION_3 = "Динозавры умерли из-за наводнения"
    val QUESTION_4 = "Кенгуру - самое быстрое животное на планете"
    val QUESTION_5 = "Индийский океан самый большой"
    val QUESTION_6 = "Утром и вечером у человека разный рост"
    val QUESTION_7 = "Саламандры могут жить в огне"
    val QUESTION_8 = "Tемпература человека подняться выше 42 градусов"
    val QUESTION_9 = "Природный газ имеет запах"
    val QUESTION_10 = "Коала - медведь"

}

interface MyInterface{
    fun startQuestions()
    fun answerQuestion(position: Int?, result: Boolean?)
    fun finishQuestions(count: String)
}

class MainActivity : AppCompatActivity(), MyInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
    }

    private fun initFragment() {
        replaceFragment(BlankFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    override fun startQuestions() {
        replaceFragment(Question_Fragment.newInstance(Questions().QUESTION_1,1,false))
    }

    override fun answerQuestion(position: Int?, result: Boolean?) {
        when (position) {
            1 -> replaceFragment(Question_Fragment.newInstance(Questions().QUESTION_2, 2, true))
            2 -> replaceFragment(Question_Fragment.newInstance(Questions().QUESTION_3, 3, false))
            3 -> replaceFragment(Question_Fragment.newInstance(Questions().QUESTION_4, 4, false))
            4 -> replaceFragment(Question_Fragment.newInstance(Questions().QUESTION_5, 5, false))
            5 -> replaceFragment(Question_Fragment.newInstance(Questions().QUESTION_6, 6, true))
            6 -> replaceFragment(Question_Fragment.newInstance(Questions().QUESTION_7, 7, false))
            7 -> replaceFragment(Question_Fragment.newInstance(Questions().QUESTION_8, 8, true))
            8 -> replaceFragment(Question_Fragment.newInstance(Questions().QUESTION_9, 9, false))
            9 -> replaceFragment(Question_Fragment.newInstance(Questions().QUESTION_10, 10, false))
        }
    }

    override fun finishQuestions(count: String) {
        replaceFragment(ResultFragment.newInstance(count))
    }
}