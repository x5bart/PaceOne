package com.x5bart_soft.paceone

import android.content.Context

private const val PREFERENCE_NAME = "SharedPreference"
private const val PREFERENCE_UNIT_SW = "UnitSw"

class MyPreference(context: Context) {

    private val preference = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    private val editor = preference.edit()

    fun getUnitSw(): Boolean {
        return preference.getBoolean(
            PREFERENCE_UNIT_SW,
            false
        )
    }

    fun setUnitSw(sw:Boolean) {
        editor.putBoolean(PREFERENCE_UNIT_SW, sw)
        editor.apply()
    }
}