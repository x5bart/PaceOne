package com.x5bart_soft.paceone

import android.app.Activity
import android.util.Log
import android.widget.EditText
import java.math.RoundingMode

class WingsFunction {

    private val wingsObject = Wings
    private val TIMELEVELSEC = 1800


    val distancePerLevel1 = 7.004
    val distancePerLevel2 = 7.500
    val distancePerLevel3 = 8.000
    val distancePerLevel4 = 8.491
    val distancePerLevel5 = 9.000
    val distancePerLevel6 = 10.976
    val distancePerLevel7 = 13.043
    val distancePerLevel8 = 15.000
    val distancePerLevel9 = 16.981

    val distanceLevel1 = distancePerLevel1
    val distanceLevel2 = distancePerLevel2 + distanceLevel1
    val distanceLevel3 = distancePerLevel3 + distanceLevel2
    val distanceLevel4 = distancePerLevel4 + distanceLevel3
    val distanceLevel5 = distancePerLevel5 + distanceLevel4
    val distanceLevel6 = distancePerLevel6 + distanceLevel5
    val distanceLevel7 = distancePerLevel7 + distanceLevel6
    val distanceLevel8 = distancePerLevel8 + distanceLevel7
    val distanceLevel9 = distancePerLevel9 + distanceLevel8

    val speedLevel1 = 14
    val speedLevel2 = 15
    val speedLevel3 = 16
    val speedLevel4 = 17
    val speedLevel5 = 18
    val speedLevel6 = 22
    val speedLevel7 = 26
    val speedLevel8 = 30
    val speedLevel9 = 34

    val tempLevel1 = 257
    val tempLevel2 = 240
    val tempLevel3 = 225
    val tempLevel4 = 212
    val tempLevel5 = 200
    val tempLevel6 = 164
    val tempLevel7 = 138
    val tempLevel8 = 120
    val tempLevel9 = 106


    fun readEt(view: EditText): String {
        var value = view.text.toString()
        if (value.isEmpty() || value == ".") {
            value = "0"
            wingsObject.isInvalidInput = true
            return value
        } else {
            wingsObject.isInvalidInput = false
            return value
        }
    }

    fun timeAll() {
        val timeH = wingsObject.timeH
        val timeM = wingsObject.timeM
        val timeS = wingsObject.timeS
        if (timeM > 59 || timeS > 59) wingsObject.etId = "all"
        wingsObject.time = (((timeH * 60) + timeM) * 60) + timeS
    }

    fun timeSeg() {
        val timeAll = wingsObject.time
        val timeH = timeAll / 3600
        val timeM = (timeAll - (timeH * 3600)) / 60
        val timeS = timeAll - (timeH * 3600) - (timeM * 60)
        wingsObject.timeH = timeH
        wingsObject.timeM = timeM
        wingsObject.timeS = timeS
    }

    fun tempRunner() {
        val tempM = wingsObject.runnerTempM * 60
        val tempS = wingsObject.runnerTempS
        wingsObject.runnerTempAll = tempM + tempS
    }

    fun getLevel(level: Int) {
        var carSpeed = 0
        var carTemp = 0
        var distancePerLevel = 0.0
        if (level > 0) {
            when (level) {
                //before
                0 -> {
                    carSpeed = 0
                    carTemp = 0
                    distancePerLevel = 0.0


                }
                //after 30:00
                1 -> {
                    carSpeed = speedLevel1
                    carTemp = tempLevel1
                    distancePerLevel = 0.0
                }
                //after 1:00:00
                2 -> {
                    carSpeed = speedLevel2
                    carTemp = tempLevel2
                    distancePerLevel = distanceLevel1
                }
                //after 1:30:00
                3 -> {
                    carSpeed = speedLevel3
                    carTemp = tempLevel3
                    distancePerLevel = distanceLevel2
                }
                //after 2:00:00
                4 -> {
                    carSpeed = speedLevel4
                    carTemp = tempLevel4
                    distancePerLevel = distanceLevel3
                }
                //after 2:30:00
                5 -> {
                    carSpeed = speedLevel5
                    carTemp = tempLevel5
                    distancePerLevel = distanceLevel4
                }
                //after 3:00:00
                6 -> {
                    carSpeed = speedLevel6
                    carTemp = tempLevel6
                    distancePerLevel = distanceLevel5
                }
                //after 3:30:00
                7 -> {
                    carSpeed = speedLevel7
                    carTemp = tempLevel7
                    distancePerLevel = distanceLevel6
                }
                //after 4:00:00
                8 -> {
                    carSpeed = speedLevel8
                    carTemp = tempLevel8
                    distancePerLevel = distanceLevel7
                }
                //after 4:30:00
                9 -> {
                    carSpeed = speedLevel9
                    carTemp = tempLevel9
                    distancePerLevel = distanceLevel8
                }
            }
        }
        wingsObject.level = level
        wingsObject.carTemp = carTemp
        wingsObject.carSpeed = carSpeed
        wingsObject.distanceBeforeLevel = distancePerLevel
    }

    private fun fillCarSpeed() {
        val time = wingsObject.time
        var level = time / TIMELEVELSEC

        var timePerLevel = 0
        when {
            level < 1 -> timePerLevel = time
            level <= 9 -> timePerLevel = time % TIMELEVELSEC
            level > 9 -> timePerLevel = time - (9 * TIMELEVELSEC)
        }
        if (level > 9) level = 9
        getLevel(level)

        wingsObject.timePerLevel = timePerLevel

    }

    private fun fillDistance() {
        var distance = wingsObject.distance
        if (distance > 3322.4) distance = 3322.4
        wingsObject.distance = distance
        var level = 0
        when {
            distance < distanceLevel1 -> level = 1
            distance >= distanceLevel1 && distance < distanceLevel2 -> level = 2
            distance >= distanceLevel2 && distance < distanceLevel3 -> level = 3
            distance >= distanceLevel3 && distance < distanceLevel4 -> level = 4
            distance >= distanceLevel4 && distance < distanceLevel5 -> level = 5
            distance >= distanceLevel5 && distance < distanceLevel6 -> level = 6
            distance >= distanceLevel6 && distance < distanceLevel7 -> level = 7
            distance >= distanceLevel7 && distance < distanceLevel8 -> level = 8
            distance >= distanceLevel8 -> level = 9

        }
        getLevel(level)


    }

    fun time() {
        fillDistance()
        var distance = wingsObject.distance
        val distancePerLevel = wingsObject.distanceBeforeLevel
        val carTemp = wingsObject.carTemp
        val level = wingsObject.level
        if (carTemp > 0) {
            distance -= distancePerLevel
            val meterPerSecond = 1000.0 / carTemp
            val time = ((level) * 1800) + ((distance * 1000) / meterPerSecond).toInt()
            wingsObject.time = time
            timeSeg()
        }
        runnerTemp()
    }

    private fun runnerTemp() {
        val time = wingsObject.time
        val distance = wingsObject.distance
        val runnerTemp = (time / distance)
        val tempM = (runnerTemp / 60).toInt()
        val tempS = (runnerTemp - (tempM * 60)).toInt()
        wingsObject.runnerTempAll = runnerTemp.toInt()
        wingsObject.runnerTempM = tempM
        wingsObject.runnerTempS = tempS
    }

    fun distance() {
        fillCarSpeed()
        val time = wingsObject.time
        if (time >= 1800) {
            val timePerLevel = wingsObject.timePerLevel
            val distanceBeforeLevel = wingsObject.distanceBeforeLevel
            val carTemp = wingsObject.carTemp
            val distance = ((timePerLevel / carTemp.toDouble()) + distanceBeforeLevel)
                .toBigDecimal()
                .setScale(3, RoundingMode.HALF_UP)
                .toDouble()
            wingsObject.distance = distance
        }
        runnerTemp()
    }

    fun tempToDistance() {
        val runnerTime = wingsObject.runnerTempAll
        var carDistance = 0.0
        var runnerDistance = 0.1
        while (carDistance != runnerDistance) {
            var distance = 0.0
            if (carDistance > runnerDistance) {
                distance = runnerDistance
                wingsObject.distance = distance
                fillDistance()
            } else {
                fillDistance()
                distance = runnerDistance + 0.001
            }
            wingsObject.distance = distance
            val distancePerLevel = wingsObject.distanceBeforeLevel
            val carTemp = wingsObject.carTemp
            val level = wingsObject.level

            val distance2 = distance - distancePerLevel
            val meterPerSecond = 1000.0 / carTemp
            val time = ((level) * 1800) + ((distance2 * 1000) / meterPerSecond).toInt()
            wingsObject.time = time

            carDistance = distance
            runnerDistance = (time.toDouble() / runnerTime)
                .toBigDecimal()
                .setScale(3, RoundingMode.HALF_UP)
                .toDouble()

        }
        timeSeg()
    }


}