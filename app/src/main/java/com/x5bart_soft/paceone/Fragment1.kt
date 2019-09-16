package com.x5bart_soft.paceone


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.fragment1.*
import java.math.RoundingMode

class Fragment1 : Fragment() {
    private val hour = 60 //60 min
    private val km = 1000 //1000 m
    private val second = 3.600 // 3600sec/hours
    private var res = 0.0
    private var etMin = 0
    private var etSec = 0
    private var etKm = 0.0
    private var etCalcDistHour = 0
    private var etCalcDistMin = 0
    private var etCalcDistSec = 0
    var etCalcKm = 0.0

    val TAG = "myLogs"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        calc1.isVisible = false
        calc2.isVisible = false
        btnCalc.isVisible = false
        tvCalcRes.isVisible = false

        etPaceS.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_GO -> {
                    mKmToKmH()
                    true
                }
                else -> false
            }
        }

        etSpeed.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_GO -> {
                    kmHToMKm()
                    true
                }
                else -> false
            }
        }

        etCalcDistS.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_NEXT -> {
                    if (calcDist.isChecked)
                        dist()
                    true

                }
                else -> false

            }
        }

        btnToSpeed.setOnClickListener {
            mKmToKmH()
        }
        btnToPace.setOnClickListener {
            kmHToMKm()
        }
        btnCalc.setOnClickListener {
            if (calcDist.isChecked) dist()
            if (calcTime.isChecked) time()
            if (calcSpeed.isChecked) speed()
        }
        calcDist.setOnClickListener {
            calc1.isVisible = true
            calc2.isVisible = false
            btnCalc.isVisible = true
            tvCalcRes.isVisible = true
        }
        calcTime.setOnClickListener {
            calc1.isVisible = false
            calc2.isVisible = true
            btnCalc.isVisible = true
            tvCalcRes.isVisible = true
        }
        calcSpeed.setOnClickListener {
            calc1.isVisible = true
            calc2.isVisible = true
            btnCalc.isVisible = true
            tvCalcRes.isVisible = true
        }
    }

    private fun mKmToKmH() {
        notNull()
        if (etMin == 0 && etSec == 0) etSpeed.setText("0.00")
        else {
            val second = ((etMin * hour) + etSec).toDouble()
            val mSec = (km / second)
            res = mSec * this.second
            etSpeed.setText("$res")
        }
    }

    private fun kmHToMKm() {
        notNull()
        if (etKm != 0.00) {
            res = (etKm / second)
            val sumSec = (km / res).toBigDecimal().setScale(0, RoundingMode.HALF_UP).toInt()
            val min = (sumSec / hour)
            val sec = sumSec - (min * hour)

            etPaceM.setText(min.toString())
            if (sec < 10) etPaceS.setText("0$sec")
            else etPaceS.setText(sec.toString())
        }
    }

    private fun dist() {
        notNull()
        notNullCalc()
        var paceSec = (etMin * hour) + etSec
        var kmSec = 0

        if (etKm.toInt() != 0) kmSec = (km / (etKm / second)).toInt()

        if (paceSec == 0 && kmSec != 0) {
            kmHToMKm()
            distCalc()

            etMin = etPaceM.text.toString().toInt()
            etSec = etPaceS.text.toString().toInt()
            paceSec = (etMin * hour) + etSec
        }

        if (kmSec == 0 && paceSec != 0) {
            mKmToKmH()
            distCalc()

            kmSec = (km / (etKm / second)).toInt()
        }

        if (paceSec != kmSec) {
            alert()
        } else distCalc()
    }

    private fun distCalc() {
        etKm = etSpeed.text.toString().toDouble()
        val distSec =
            (((etCalcDistHour * hour) + etCalcDistMin) * hour) + etCalcDistSec.toDouble()
        val mSec = etKm / 3600
        val res = (distSec * mSec).toBigDecimal().setScale(3, RoundingMode.HALF_UP).toDouble()
        tvCalcRes.text = "$res km"
        etCalcKmh.setText("$res")
    }

    private fun time() {
        notNull()
        notNullCalc()

        var paceSec = (etMin * hour) + etSec
        var kmSec = 0

        if (etKm.toInt() != 0) kmSec = (km / (etKm / second)).toInt()

        if (paceSec == 0 && kmSec != 0) {
            kmHToMKm()
            timeCalc()

            etMin = (etPaceM.text.toString().toInt()) * hour
            etSec = etPaceS.text.toString().toInt()
            paceSec = etMin + etSec
        }
        if (kmSec == 0 && paceSec != 0) {
            mKmToKmH()
            timeCalc()
            kmSec = (km / (etKm / second)).toInt()
        }
        if (paceSec != kmSec) {
            alert()
        } else timeCalc()
    }

    private fun timeCalc() {
        etKm = etSpeed.text.toString().toDouble()
        val sumSec = etCalcKm / etKm * 3600
        val hour2 = (etCalcKm / etKm).toInt()
        val min = ((sumSec - (hour2 * 3600)) / hour).toInt()
        val sec = (sumSec - (hour2 * 3600) - (min * hour)).toInt()
        tvCalcRes.text = ("$hour2:$min:$sec")
        etCalcDistH.setText("$hour2")
        etCalcDistM.setText("$min")
        etCalcDistS.setText("$sec")
    }

    private fun speed() {
        notNullCalc()
        val timeCalc = (((etCalcDistHour * hour) + etCalcDistMin) * hour) + etCalcDistSec
        val distCalc = etCalcKm

        if (timeCalc == 0 || distCalc == 0.0) {
            etSpeed.setText("0.00")
            kmHToMKm()
        } else speedCalc()

    }

    private fun speedCalc() {
        val distSec =
            (((etCalcDistHour * hour) + etCalcDistMin) * hour) + etCalcDistSec.toDouble()
        val distM = etCalcKm * km
        val mSec = distM / distSec
        res = ((mSec * 3600) / km).toBigDecimal().setScale(2, RoundingMode.HALF_UP).toDouble()
        etSpeed.setText("$res")
        kmHToMKm()
    }

    private fun alert() {
        val builder = AlertDialog.Builder(activity!!)
        val message = "Calculate value by min/km ($etMin:$etSec) or km/h ($etKm)"
        builder.setTitle(R.string.alertTitle)
        builder.setMessage(message)
        builder.setPositiveButton("$etMin:$etSec") { _, _ ->
            mKmToKmH()
            if (calcDist.isChecked) dist()
            if (calcTime.isChecked) time()
        }
        builder.setNegativeButton("$etKm") { _, _ ->
            kmHToMKm()
            if (calcDist.isChecked) dist()
            if (calcTime.isChecked) time()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun notNull() {
        etMin = if (etPaceM.text.toString().isEmpty()) 0 else etPaceM.text.toString().toInt()
        etSec = if (etPaceS.text.toString().isEmpty()) 0 else etPaceS.text.toString().toInt()
        etKm =
            if (etSpeed.text.toString().isEmpty()) 0.0 else if (etSpeed.text.toString().toDouble() < 1 && etSpeed.text.toString().toDouble() > 0) 1.00
            else etSpeed.text.toString().toDouble()
        if (etMin == 0) etPaceM.setText("0")
        if (etSec < 10) etPaceS.setText("0$etSec")
        if (etKm == 0.0) etSpeed.setText("0.00")
        if (etKm == 1.00) etSpeed.setText("1.0")
    }

    private fun notNullCalc() {
        if (calcDist.isChecked) {
            etCalcDistHour =
                if (etCalcDistH.text.toString().isEmpty()) 0 else etCalcDistH.text.toString().toInt()
            etCalcDistMin =
                if (etCalcDistM.text.toString().isEmpty()) 0 else etCalcDistM.text.toString().toInt()
            etCalcDistSec =
                if (etCalcDistS.text.toString().isEmpty()) 0 else etCalcDistS.text.toString().toInt()
            if (etCalcDistHour == 0) etCalcDistH.setText("0")
            if (etCalcDistMin == 0) etCalcDistM.setText("0")
            if (etCalcDistSec == 0) etCalcDistS.setText("0")
            if (etCalcDistHour < 10) etCalcDistH.setText("0$etCalcDistHour")
            if (etCalcDistMin < 10) etCalcDistM.setText("0$etCalcDistMin")
            if (etCalcDistSec < 10) etCalcDistS.setText("0$etCalcDistSec")
        }
        if (calcTime.isChecked) {
            etCalcKm =
                if (etCalcKmh.text.toString().isEmpty()) 0.0 else etCalcKmh.text.toString().toDouble()
            if (etCalcKm == 0.0) etCalcKmh.setText("0.00")
        }
        if (calcSpeed.isChecked) {
            etCalcDistHour =
                if (etCalcDistH.text.toString().isEmpty()) 0 else etCalcDistH.text.toString().toInt()
            etCalcDistMin =
                if (etCalcDistM.text.toString().isEmpty()) 0 else etCalcDistM.text.toString().toInt()
            etCalcDistSec =
                if (etCalcDistS.text.toString().isEmpty()) 0 else etCalcDistS.text.toString().toInt()
            if (etCalcDistHour == 0) etCalcDistH.setText("0")
            if (etCalcDistMin == 0) etCalcDistM.setText("0")
            if (etCalcDistSec == 0) etCalcDistS.setText("0")
            if (etCalcDistHour < 10) etCalcDistH.setText("0$etCalcDistHour")
            if (etCalcDistMin < 10) etCalcDistM.setText("0$etCalcDistMin")
            if (etCalcDistSec < 10) etCalcDistS.setText("0$etCalcDistSec")
            etCalcKm =
                if (etCalcKmh.text.toString().isEmpty()) 0.0 else etCalcKmh.text.toString().toDouble()
            if (etCalcKm == 0.0) etCalcKmh.setText("0.00")
        }
    }
}
