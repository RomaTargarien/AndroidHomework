package com.example.gifs

import android.content.Context
import android.preference.PreferenceManager
import androidx.core.content.edit

private const val PREF_SEARCH_QUERY = "searchQuery"

object QueryPreferences {
    fun getSoredQuerry(context: Context): String{
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString(PREF_SEARCH_QUERY,"")!!
    }
    fun setStoredQuerry(context: Context, query: String){
        PreferenceManager.getDefaultSharedPreferences(context).edit{putString(PREF_SEARCH_QUERY,query)}
    }
}