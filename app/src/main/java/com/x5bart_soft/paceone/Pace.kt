package com.x5bart_soft.paceone

import android.util.Log

object Pace {

    var tempM = 0
    var tempS = 0
    var tempAll = 0 // sec
    var speed = 0.0
    var timeH = 0
    var timeM = 0
    var timeS = 0
    var timeAll = 0 //sec
    var distance = 0.0
    var etID = ""
    var activeFunction = ""
    var calculateDialog = ""
    var isInvalidInput = false
    var activeGroup = ""


    val MINUTE = 60 //60 sec
    val HOUR = 3600.00 // 3600 sec
    val KM = 1000 //1000 m

    //    val MILEKM = 1609.344 // 1609.344 m
//    val MILEF = 1760// 1760 fut
//    var unit = if (Config.FLAG_MILE_TO_KM) MILEF else KM
    var MSEC = (HOUR / KM)// 3600sec/km

    fun lod() {
        Log.d(TAG, "tempM = $tempM")
        Log.d(TAG, "tempS = $tempS")
        Log.d(TAG, "tempAll = $tempAll")
        Log.d(TAG, "speed = $speed")
        Log.d(TAG, "timeH = $timeH")
        Log.d(TAG, "timeM = $timeM")
        Log.d(TAG, "timeS = $timeS")
        Log.d(TAG, "timeAll = $timeAll")
        Log.d(TAG, "distance = $distance")
    }

}