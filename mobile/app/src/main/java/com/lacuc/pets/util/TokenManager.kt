package com.lacuc.pets.util

import android.content.Context
import android.content.SharedPreferences
import com.lacuc.pets.di.ApplicationContext
import javax.inject.Inject

class TokenManager @Inject constructor(@ApplicationContext context: Context) {
    private val pref: SharedPreferences =
        context.getSharedPreferences("com.lacuc.pets.auth", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        val edit = pref.edit()
        edit.putString("accessToken", token)
        edit.apply()
    }

    fun getToken(): String? = pref.getString("accessToken", null)
}