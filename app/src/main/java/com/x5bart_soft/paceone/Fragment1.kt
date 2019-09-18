package com.x5bart_soft.paceone


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    var flagListener = 0
    val TAG = "myLogs"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        etPaceM.setOnFocusChangeListener { view, hasFocus ->
            flagListener = 1
            Log.d(TAG, "flag  = $flagListener")
            etPaceM.selectAll()
        }
        etPaceS.setOnFocusChangeListener { view, hasFocus ->
            flagListener = 2
            Log.d(TAG, "flag  = $flagListener")
            etPaceS.selectAll()
        }
        etSpeed.setOnFocusChangeListener { view, hasFocus ->
            flagListener = 3
            Log.d(TAG, "flag  = $flagListener")
            etSpeed.selectAll()
        }
        etCalcDistH.setOnFocusChangeListener { view, hasFocus ->
            flagListener = 4
            Log.d(TAG, "flag  = $flagListener")
            etSpeed.selectAll()
        }
        etCalcDistM.setOnFocusChangeListener { view, hasFocus ->
            flagListener = 5
            Log.d(TAG, "flag  = $flagListener")
            etSpeed.selectAll()
        }
        etCalcDistS.setOnFocusChangeListener { view, hasFocus ->
            flagListener = 6
            Log.d(TAG, "flag  = $flagListener")
            etSpeed.selectAll()
        }
        etCalcKmh.setOnFocusChangeListener { view, hasFocus ->
            flagListener = 7
            Log.d(TAG, "flag  = $flagListener")
            etSpeed.selectAll()
        }

        etPaceM.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

                if (!cbFixDist.isChecked && flagListener == 1) {
                    mKmToKmH()
//                    dist()
//                    time()
                }
                if (cbFixDist.isChecked && flagListener == 1) mKmToKmH()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        etPaceS.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (!cbFixDist.isChecked && flagListener == 2) {
                    mKmToKmH()
//                    dist()
//                    time()
                }
                if (cbFixDist.isChecked && flagListener == 2) mKmToKmH()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        etSpeed.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (!cbFixDist.isChecked && flagListener == 3) {
                    kmHToMKm()
//                    dist()
//                    time()
                }
                if (cbFixDist.isChecked && flagListener == 3) mKmToKmH()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        etCalcDistH.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (cbFixDist.isChecked && flagListener == 4) speed()
                if (flagListener == 4) dist()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        etCalcDistM.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (cbFixDist.isChecked && flagListener == 5) speed()
                if (!cbFixDist.isChecked && flagListener == 5) dist()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        etCalcDistS.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (cbFixDist.isChecked && flagListener == 6) speed()
                if (!cbFixDist.isChecked && flagListener == 6) dist()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        etCalcKmh.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (cbFixDist.isChecked && flagListener == 7) speed()
                if (!cbFixDist.isChecked && flagListener == 7) time()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    private fun mKmToKmH() {
        notNull()
        if (etMin != 0 && etSec == 0 || etMin == 0 && etSec != 0 || etMin != 0 && etSec != 0) {
            val second = ((etMin * hour) + etSec).toDouble()
            val mSec = (km / second)
            res = mSec * this.second
            etSpeed.setText("$res")
        }
    }

    private fun kmHToMKm() {
        notNull()
        if (etKm != 0.00) {
            val res = (etKm / 3.6)
            val sumSec = (1000 / res).toBigDecimal().setScale(0, RoundingMode.HALF_UP).toInt()
            etMin = (sumSec / 60)
            etSec = sumSec - (etMin * 60)

            if (etMin == 0) etPaceM.setText("00") else etPaceM.setText(etMin.toString())

            if (etKm < 1) etSpeed.setText("1.00")

            if (etSec < 10) etPaceS.setText("0$etSec") else etPaceS.setText(etSec.toString())
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
        } else distCalc()
    }

    private fun distCalc() {
        etKm = etSpeed.text.toString().toDouble()
        val distSec =
            (((etCalcDistHour * hour) + etCalcDistMin) * hour) + etCalcDistSec.toDouble()
        val mSec = etKm / 3600
        val res = (distSec * mSec).toBigDecimal().setScale(3, RoundingMode.HALF_UP).toDouble()
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
        } else timeCalc()
    }

    private fun timeCalc() {
        etKm = etSpeed.text.toString().toDouble()
        val sumSec = etCalcKm / etKm * 3600
        val hour2 = (etCalcKm / etKm).toInt()
        val min = ((sumSec - (hour2 * 3600)) / hour).toInt()
        val sec = (sumSec - (hour2 * 3600) - (min * hour)).toInt()
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
        } else {
            speedCalc()
            kmHToMKm()
        }

    }

    private fun speedCalc() {
        val distSec =
            (((etCalcDistHour * hour) + etCalcDistMin) * hour) + etCalcDistSec.toDouble()
        val distM = etCalcKm * km
        val mSec = distM / distSec
        res = ((mSec * 3600) / km).toBigDecimal().setScale(2, RoundingMode.HALF_UP).toDouble()
        etSpeed.setText("$res")
    }

    private fun notNull() {
        etMin = if (etPaceM.text.toString().isEmpty()) 0 else etPaceM.text.toString().toInt()
        etSec = if (etPaceS.text.toString().isEmpty()) 0 else etPaceS.text.toString().toInt()
        etKm =
            if (etSpeed.text.toString().isEmpty() || etSpeed.text.toString() == ".") 0.0
            else etSpeed.text.toString().toDouble()
    }

    private fun notNullCalc() {

        etCalcDistHour =
            if (etCalcDistH.text.toString().isEmpty()) 0 else etCalcDistH.text.toString().toInt()
        etCalcDistMin =
            if (etCalcDistM.text.toString().isEmpty()) 0 else etCalcDistM.text.toString().toInt()
        etCalcDistSec =
            if (etCalcDistS.text.toString().isEmpty()) 0 else etCalcDistS.text.toString().toInt()
        etCalcKm =
            if (etCalcKmh.text.toString().isEmpty()) 0.0 else etCalcKmh.text.toString().toDouble()

    }
}


