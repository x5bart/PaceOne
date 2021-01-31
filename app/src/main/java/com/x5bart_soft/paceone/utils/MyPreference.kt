package com.x5bart_soft.paceone.utils

import android.content.Context

private const val PREFERENCE_NAME = "SharedPreference"
private const val PREFERENCE_UNIT_SW = "UnitSw"
private const val LANGUAGE = "language"
private const val HEIGHT = "height"


class MyPreference() {
    private val context = App.context
    private val preference = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    private val editor = preference.edit()

    fun getUnitSw(): Boolean {
        return preference.getBoolean(
            PREFERENCE_UNIT_SW,
            false
        )
    }

    fun setUnitSw(sw: Boolean) {
        editor.putBoolean(PREFERENCE_UNIT_SW, sw)
        editor.apply()
    }

    fun getLanguage(): String? {
        return preference.getString(
            LANGUAGE, "notSave"
        )
    }

    fun setLanguage(language: String) {
        editor.putString(LANGUAGE, language)
        editor.apply()
    }

    fun setHeight(height: Int) {
        editor.putInt(HEIGHT, height)
        editor.apply()
    }

    fun getHeight(): Int {
        return preference.getInt(HEIGHT, 0)
    }

    fun setDate(calendar: String, value: Int) {
        editor.putInt(calendar, value)
        editor.apply()
    }

    fun getDate(calendar: String): Int {
        return preference.getInt(calendar, 0)

    }

    fun setBoolean(key: String, boolean: Boolean) {
        editor.putBoolean(key, boolean)
        editor.apply()
    }

    fun getBoolean(key: String): Boolean {
        return preference.getBoolean(key, false)
    }
}

