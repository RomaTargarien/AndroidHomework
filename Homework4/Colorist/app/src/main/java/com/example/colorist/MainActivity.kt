package com.example.colorist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.colorist.fragments.ChoosingColorFragment
import com.example.colorist.fragments.ColoristFragment
import com.example.colorist.fragments.ColorsLearnFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

interface ColorInterface{
    fun openColor(colorView: ColorView)
}

class MainActivity : AppCompatActivity(),ColorInterface {

    private lateinit var bottom_navigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val colorsLearnFragment = ColorsLearnFragment()
        val coloristFragment = ColoristFragment()
        bottom_navigation = findViewById(R.id.bottom_navigation)

        makeCurrentFragment(colorsLearnFragment)
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.learn_colors -> makeCurrentFragment(colorsLearnFragment)
                R.id.play_colorist -> makeCurrentFragment(coloristFragment)
            }
            true
        }
    }

    fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }

    override fun openColor(colorView: ColorView) {
        makeCurrentFragment(ChoosingColorFragment.newInstance(colorView))
    }
}