package com.example.sherbek791.screens.home.adapter

import android.content.Context

class SharedPreferencesHelper {
    private val PREF_NAME = "MY_PREFERENCES"

    fun savedLikeState(context: Context, itemId : String, state : Boolean){
        val sharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(itemId,state)
        editor.apply()
    }

    fun getLikeState(context: Context,itemId: String) : Boolean{
        val sharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(itemId,false)
    }
}