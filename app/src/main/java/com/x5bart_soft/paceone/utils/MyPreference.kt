package com.x5bart_soft.paceone.utils

import android.content.Context
import org.intellij.lang.annotations.Language

private const val PREFERENCE_NAME = "SharedPreference"
private const val PREFERENCE_UNIT_SW = "UnitSw"
private const val LANGUAGE = "language"

class MyPreference(context: Context) {

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
}