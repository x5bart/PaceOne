package com.x5bart_soft.paceone

import android.util.Log

object Wings {

    var isInvalidInput = false
    var etId = "all"

    var carSpeed = 0    //km/h
    var carTemp = 0     //sec/km

    var time = 0        //all second
    var timeH = 0       //hour
    var timeM = 0       //minute
    var timeS = 0       //second

    var runnerTempAll = 0
    var runnerTempM = 0
    var runnerTempS = 0
    var distance = 0.0

    var distanceBeforeLevel = 0.0
    var timePerLevel = 0

    var level = 1

    var splits = false
    var splitValue = 1.0
    var splitStrategy = 0.0
    var splitsIsEmpty = true


    fun lod() {
        Log.d(TAG, "etId = $etId")
        Log.d(TAG, "RunnerTempAll = $runnerTempAll")
        Log.d(TAG, "RunnerTempM = $runnerTempM")
        Log.d(TAG, "RunnerTempS = $runnerTempS")
        Log.d(TAG, "CarTemp = $carTemp")
        Log.d(TAG, "time = $time")
        Log.d(TAG, "timeH = $timeH")
        Log.d(TAG, "timeM = $timeM")
        Log.d(TAG, "timeS = $timeS")
        Log.d(TAG, "distance = $distance")
        Log.d(TAG, "distancePerLevel = $distanceBeforeLevel")
        Log.d(TAG, "level = $level")

    }
}
