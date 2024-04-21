package com.imprarce.android.testtasktickets.utils

import android.content.Context

object PreferenceHelper {
    private const val PREFERENCE_NAME = "MyPreferences"
    private const val KEY_LAST_INPUT = "lastInput"

    fun saveLastInput(context: Context, input: String) {
        val sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(KEY_LAST_INPUT, input).apply()
    }

    fun getLastInput(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_LAST_INPUT, null)
    }
}