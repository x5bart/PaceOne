package com.x5bart_soft.paceone

import android.util.Log
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.x5bart_soft.paceone.split.Split
import com.x5bart_soft.paceone.split.SplitAdapter
import java.math.RoundingMode

class SplitFunction {
    val paceObject = Pace
    private val splits = arrayListOf<Split>()

    fun createSplitList(viev:RecyclerView) {

        val timeAll = paceObject.timeAll
        val distance = paceObject.distance
        var splitValue = paceObject.splitValue
        val splitStrategy = paceObject.splitStrategy

        if (timeAll != 0 && distance != 0.0 && splitValue != 0.0) {
            val splitCount = (distance / splitValue)
                .toBigDecimal()
                .setScale(0, RoundingMode.UP)
                .toInt()
            val tempAll = timeAll / distance // second per km avg
            val splitStrategyPercent = (splitStrategy / 100)
            val splitStrategyDelta = ((1 - splitStrategyPercent) - (1 + splitStrategyPercent))
            var timeNextSplit = 0.0
            var slitNumber = 0

            while (slitNumber != splitCount) {

                // #1 Number
                slitNumber++

                //#2 km
                var splitDistance = (splitValue * slitNumber)
                    .toBigDecimal()
                    .setScale(2, RoundingMode.HALF_EVEN)
                    .toDouble()
                if (splitDistance > distance) {
                    splitValue = distance - (splitDistance - splitValue)
                    splitDistance = distance
                }


                //#3 time
                //negativSplit
                val percentNext = splitDistance / distance
                val percentPrev = (splitValue * (slitNumber - 1)) / distance

                val coefNext = splitStrategyDelta * percentNext
                val coefPrev = splitStrategyDelta * percentPrev
                val coefAvg = ((coefPrev) + (coefNext)) / 2
                val splitTime = ((tempAll * splitValue) * (1 - (splitStrategyPercent + coefAvg)))

                val splitTimeAll = (splitTime + timeNextSplit)
                timeNextSplit = splitTimeAll
                val splitTimeAllPrint =
                    splitTimeAll.toBigDecimal().setScale(0, RoundingMode.HALF_UP).toInt()
                val splitTimePrint = splitTime.toInt()

                val splitTimeH = (splitTimeAllPrint / paceObject.HOUR.toInt())
                    .toBigDecimal()
                    .setScale(0, RoundingMode.HALF_UP)
                    .toInt()
                val splitTimeM =
                    ((splitTimeAllPrint - (splitTimeH * paceObject.HOUR)) / paceObject.MINUTE).toInt()
                val splitTimeS =
                    (((splitTimeAllPrint - (splitTimeH * paceObject.HOUR) - (splitTimeM * paceObject.MINUTE)))).toInt()
                var splitTimeMPrint = splitTimeM.toString()
                var splitTimeSPrint = splitTimeS.toString()
                if (splitTimeM < 10) splitTimeMPrint = "0$splitTimeM"
                if (splitTimeS < 10) splitTimeSPrint = "0$splitTimeS"
                val time = if (splitTimeH > 0) "$splitTimeH:$splitTimeMPrint:$splitTimeSPrint"
                else "$splitTimeMPrint:$splitTimeSPrint"

                //#4 temp
                val tempM = (splitTimePrint / paceObject.MINUTE)
                val tempS = (splitTimePrint - (tempM * paceObject.MINUTE))
                var tempSPrint = tempS.toString()
                if (tempS < 10) tempSPrint = "0$tempS"
                val temp = "$tempM:$tempSPrint"

                //#5 avg
                val avgPace = (splitTimeAll / splitDistance)
                    .toBigDecimal()
                    .setScale(0, RoundingMode.HALF_UP)
                    .toInt()
                val avgM = (avgPace / Pace.MINUTE)
                val avgS = (avgPace - (avgM * Pace.MINUTE))
                var avgSPrint = avgS.toString()
                if (avgS < 10) avgSPrint = "0$avgS"
                val avg = "$avgM:$avgSPrint"

                splits.add(Split(slitNumber, splitDistance, time, temp, avg))
            }
            val adapter = SplitAdapter(splits)
            viev.adapter = adapter

        }
    }

    fun readEt(view: EditText): String {
        var value = view.text.toString()
        Log.d(TAG, "readEt: value = $value")
        if (value.isEmpty() || value == ".") {
            value = "0"
            paceObject.isInvalidInput = true
            return value
        } else {
            paceObject.isInvalidInput = false
            return value
        }
    }
    fun timeAll() {
        val timeH = paceObject.timeH
        val timeM = paceObject.timeM
        val timeS = paceObject.timeS
        paceObject.timeAll = (((timeH * paceObject.MINUTE) + timeM) * paceObject.MINUTE) + timeS
        Log.d(TAG, "timeAll(): timeAll = ${paceObject.timeAll}")
    }

    fun speedToTemp() {
        if (paceObject.speed < 1) paceObject.speed = 1.0
        val speed = paceObject.speed
        val tempAll = (paceObject.KM / (speed / paceObject.MSEC))
            .toBigDecimal()
            .setScale(0, RoundingMode.HALF_UP)
            .toInt()
        paceObject.tempAll = tempAll
        val tempM = tempAll / paceObject.MINUTE
        paceObject.tempM = tempM
        paceObject.tempS = tempAll - (tempM * paceObject.MINUTE)
        Log.d(TAG, "speedToTemp() : tempAll = ${paceObject.tempAll}")

    }


}