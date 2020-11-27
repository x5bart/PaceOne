package com.x5bart_soft.paceone

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.widget.EditText
import android.widget.TextView
import java.math.RoundingMode

class PaceFunction {

    val pace = Pace

    fun readEt(view: EditText): String {
        var value = view.text.toString()
        if (value.isEmpty() || value == ".") {
            value = "0"
            pace.isInvalidInput = true
            return value
        } else {
            pace.isInvalidInput = false
            return value
        }
    }

    fun tempAll() {
        val tempM = pace.tempM
        val tempS = pace.tempS
        pace.tempAll = tempM * pace.MINUTE + tempS
    }

    fun timeAll() {
        val timeH = pace.timeH
        val timeM = pace.timeM
        val timeS = pace.timeS
        pace.timeAll = (((timeH * pace.MINUTE) + timeM) * pace.MINUTE) + timeS
    }

    fun tempToSpeed() {
        val tempAll = pace.tempAll.toDouble()
        val meterPerSecond = pace.KM / tempAll
        pace.speed =
            (meterPerSecond * pace.HOUR / pace.KM)
                .toBigDecimal()
                .setScale(2, RoundingMode.HALF_UP)
                .toDouble()
    }

    fun speedToTemp() {
        if (pace.speed < 1) pace.speed = 1.0
        val speed = pace.speed
        val tempAll = (pace.KM / (speed / pace.MSEC))
            .toBigDecimal()
            .setScale(0, RoundingMode.HALF_UP)
            .toInt()
        pace.tempAll = tempAll
        val tempM = tempAll / pace.MINUTE
        pace.tempM = tempM
        pace.tempS = tempAll - (tempM * pace.MINUTE)

    }

    fun speed() {
        val distance = pace.distance
        val distM = distance * pace.KM
        val timeAll = pace.timeAll
        val meterPerSocond = distM / timeAll
        pace.speed = ((meterPerSocond * pace.HOUR) / pace.KM)
            .toBigDecimal()
            .setScale(2, RoundingMode.HALF_UP)
            .toDouble()
        speedToTemp()

    }

    fun time() {
        val distance = pace.distance
        val tempAll = pace.tempAll
        val timeAll = (distance * tempAll ).toInt()
        val timeH = timeAll / pace.HOUR.toInt()
        val timeM = ((timeAll - (timeH * pace.HOUR)) / pace.MINUTE).toInt()
        val timeS = (timeAll - (timeH * pace.HOUR) - (timeM * pace.MINUTE)).toInt()
        pace.timeAll = timeAll
        pace.timeH = timeH
        pace.timeM = timeM
        pace.timeS = timeS

    }

    fun distance() {
        val time = pace.timeAll.toDouble()
        val temp = pace.tempAll.toDouble()
        val distance = (time / temp)
            .toBigDecimal()
            .setScale(3, RoundingMode.HALF_UP)
            .toDouble()
        pace.distance = distance
    }

    fun showVersion(view: TextView, context: Context) {
        val manager: PackageManager = context.packageManager
        val info: PackageInfo = manager.getPackageInfo(context.packageName, 0)
        val currentVersion = "Pace One v${info.versionName}"
        view.text = currentVersion
    }

//    fun convertorKmToMile() {
//        pace.speed = (pace.speed / (pace.MILEKM / pace.KM))
//            .toBigDecimal()
//            .setScale(2, RoundingMode.HALF_UP)
//            .toDouble()
//        pace.distance = (pace.distance / (pace.MILEKM / pace.KM))
//            .toBigDecimal()
//            .setScale(3, RoundingMode.HALF_UP)
//            .toDouble()
//    }
//
//    fun convertorMileToKm() {
//        pace.speed = (pace.speed * (pace.MILEKM / pace.KM))
//            .toBigDecimal()
//            .setScale(2, RoundingMode.HALF_UP)
//            .toDouble()
//        pace.distance = (pace.distance * (pace.MILEKM / pace.KM))
//            .toBigDecimal()
//            .setScale(3, RoundingMode.HALF_UP)
//            .toDouble()
//    }


    fun clear() {
        pace.tempM = 0
        pace.tempS = 0
        pace.tempAll = 0
        pace.speed = 0.0
        pace.timeH = 0
        pace.timeM = 0
        pace.timeS = 0
        pace.timeAll = 0
        pace.distance = 0.0
        pace.etID = ""
        pace.activeFunction = ""
        pace.splitIsEmpty = true
    }


}