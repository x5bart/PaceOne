package com.x5bart_soft.paceone.data

import android.util.Log
import android.widget.EditText
import com.x5bart_soft.paceone.TAG
import java.math.RoundingMode
import java.util.*

object Person {

    private var height = 0
    private var strideLength = 0

    private var calendar: Calendar = Calendar.getInstance()
    private var mYear = calendar.get(Calendar.YEAR)
    private var mMonth = calendar.get(Calendar.MONTH)
    private var mDay = calendar.get(Calendar.DAY_OF_MONTH)
    var isBirthday = false

    private var mMaxHr = 0
    private var age = 0

    fun getHeight(): Int {
        return height
    }

    fun setHeight(height: Int) {
        this.height = height
    }

    @JvmName("getStrideLength1")
    fun getStrideLength(): Int {
        val cof = 0.65
        val height = height
        strideLength = (height * cof).toBigDecimal().setScale(0, RoundingMode.HALF_UP).toInt()
        return strideLength
    }

    fun getDay(): Int {
        return mDay
    }

    fun getMonth(): Int {
        return mMonth
    }

    fun getYear(): Int {
        return mYear
    }

    fun setDay(day: Int) {
        mDay = day
    }

    fun setMonth(month: Int) {
        mMonth = month
    }

    fun setYear(year: Int) {
        mYear = year
    }

    private fun maxHr() {
        age()
        mMaxHr = 220 - age
    }

    private fun age() {
        val d = calendar.get(Calendar.DAY_OF_MONTH) - mDay
        val m = calendar.get(Calendar.MONTH) - mMonth
        val y = calendar.get(Calendar.YEAR) - mYear
        age = if (m < 0 || (m <= 0 && d < 0)) y - 1
        else y
    }

    fun getMaxHr(): Int {
        maxHr()
        return mMaxHr
    }

    fun getZone(min: Double, max: Double): String {
        Log.d(TAG, "min=$min max=$max")
        maxHr()
        val minHr = (mMaxHr * min).toBigDecimal().setScale(0, RoundingMode.HALF_UP).toInt()
        val maxHr = (mMaxHr * max).toBigDecimal().setScale(0, RoundingMode.HALF_UP).toInt()
        Log.d(TAG, "minHR = $minHr maxHr = $maxHr")

        return when {
            (minHr == 0) -> "<$maxHr"
            (maxHr == 0) -> ">$minHr"
            else -> "$minHr-$maxHr"
        }
    }


}