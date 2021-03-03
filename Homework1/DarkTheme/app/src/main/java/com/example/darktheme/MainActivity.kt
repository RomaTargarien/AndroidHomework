package com.example.darktheme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatDelegate

const val PREFS_NAME = "theme_prefs"
const val KEY_THEME = "prefs.theme"
const val THEME_LIGHT = 0
const val THEME_DARK = 1


class MainActivity : AppCompatActivity() {

    private val sharedPrefs by lazy {  getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initThemeListener()
    }
    private fun initThemeListener(){
        val themeGroup = findViewById<RadioGroup>(R.id.themeGroup)
        themeGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId){
                R.id.themeLight -> setTheme(AppCompatDelegate.MODE_NIGHT_YES, THEME_LIGHT )
                R.id.themeDark -> setTheme(AppCompatDelegate.MODE_NIGHT_NO, THEME_DARK )
            }
        }
    }
    fun setTheme(themeMode: Int, prefsMode: Int) {
        AppCompatDelegate.setDefaultNightMode(themeMode)
        saveTheme(prefsMode)
    }

    private fun saveTheme(theme: Int) = sharedPrefs.edit().putInt(KEY_THEME, theme).apply()


}