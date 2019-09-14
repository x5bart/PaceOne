package com.x5bart_soft.paceone


import android.app.Dialog
import android.icu.math.BigDecimal
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.graphics.scaleMatrix
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.fragment1.*
import java.math.RoundingMode
import kotlin.math.round

class Fragment1 : Fragment() {
    private val hour = 60 //60 min
    private val km = 1000 //1000 m
    private val sec = 3.600 // 3600sec/hours
    private var res = 0.0
    private var etMin = 0
    private var etSec = 0
    private var etKm = 0.0
    var etCalcDistHour = 0
    var etCalcDistMin = 0
    var etCalcDistSec = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

//        calc1.isVisible = false

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
        when (rgCalc.checkedRadioButtonId){
            R.id.calcDist ->{
                calc1.isVisible = true
                btnCalc.isVisible = true
                mKmToKmH()
            }
            R.id.calcTime -> {
                calc1.isVisible = false
                btnCalc.isVisible = true
            }
            R.id.calcSpeed ->{
                calc1.isVisible = false
                btnCalc.isVisible = true
            }
        }

        btnToSpeed.setOnClickListener {
            mKmToKmH()
        }
        btnToPace.setOnClickListener {
            kmHToMKm()
        }
        btnCalc.setOnClickListener {
            dist()
        }
    }

    private fun mKmToKmH() {
        notNull()
        if (etMin == 0 && etSec == 0) etSpeed.setText("0.00")
        else {
            val second = ((etMin * hour) + etSec).toDouble()
            res = ((km / second) * sec).toBigDecimal().setScale(2,RoundingMode.HALF_UP).toDouble()
            etSpeed.setText(res.toString())
        }

    }

    private fun kmHToMKm() {
        notNull()
        if (etKm != 0.00) {
            etKm = etSpeed.text.toString().toDouble()
            res = etKm / sec
            val min = (km / res / hour).toInt()
            val sec = ((km / res) - (min * hour)).toInt()

            etPaceM.setText(min.toString())
            if (sec < 10) etPaceS.setText("0$sec")
            else etPaceS.setText(sec.toString())
        }
    }

    fun dist() {

        notNull()
        notNullCalc()
        val paceSec = (etMin * hour) + etSec
        var kmh = 0
        if (etKm.toInt() != 0) kmh = (km / (etKm / sec)).toInt()
        var time = 0

        if (paceSec == 0 && kmh != 0) {
            time = kmh
            kmHToMKm()
        }
        if (kmh == 0 && paceSec != 0) {
            time = paceSec
            mKmToKmH()
        }
        if (paceSec == kmh) time = paceSec
        if (time != 0) {
            val distSec =
                (((etCalcDistHour * hour) + etCalcDistMin) * hour) + etCalcDistSec
            val res  = (distSec / time).toBigDecimal().setScale(3,RoundingMode.HALF_UP)
            tvCalcRes1.text = "$res"
        } else tvCalcRes1.text = "0.00"
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

    fun notNullCalc() {
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
}

