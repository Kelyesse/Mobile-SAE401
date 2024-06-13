package com.example.mob_sae401

import android.content.Context

class PreferencesManager(private val context: Context) {
    private val sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    fun getData(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    fun saveData(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun removeData(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }
}




