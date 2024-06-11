package com.example.kotlin_demo.utils

import android.content.Context
import android.content.SharedPreferences

data class UserStore(val _id: String, val username: String, val token: String)
class UserManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveUser(_id: String, username: String, token: String) {
        val editor = prefs.edit()
        editor.putString("_id", _id)
        editor.putString("username", username)
        editor.putString("token", token)
        editor.apply()
    }

    fun getUser(): UserStore? {
        val username = prefs.getString("username", null)
        val _id = prefs.getString("_id", null)
        val token = prefs.getString("token", null)
        return if (username != null && token != null && _id !=null) {
            UserStore(_id, username, token)
        } else {
            null
        }
    }
}