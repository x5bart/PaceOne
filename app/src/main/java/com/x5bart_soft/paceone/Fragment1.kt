package com.x5bart_soft.paceone


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment1.*
import java.math.RoundingMode

class Fragment1 : Fragment() {
    private val hour = 60 //60 min
    private val km = 1000 //1000 m
    private val second = 3.600 // 3600sec/hours
    private var etMin = 0
    private var etSec = 0
    private var etKm = 0.0
    private var etCalcDistHour = 0
    private var etCalcDistMin = 0
    private var etCalcDistSec = 0
    var etCalcKm = 0.0
    var etID = 0
    val TAG = "myLogs"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        etPaceM.setOnFocusChangeListener { view, hasFocus ->
            etID = 1
        }
        etPaceS.setOnFocusChangeListener { view, hasFocus ->
            etID = 2
        }
        etSpeed.setOnFocusChangeListener { view, hasFocus ->
            etID = 3
        }
        etCalcDistH.setOnFocusChangeListener { view, hasFocus ->
            etID = 4
            swTimeDist.isChecked = true
        }
        etCalcDistM.setOnFocusChangeListener { view, hasFocus ->
            etID = 5
            swTimeDist.isChecked = true
        }
        etCalcDistS.setOnFocusChangeListener { view, hasFocus ->
            etID = 6
            swTimeDist.isChecked = true
        }
        etCalcKmh.setOnFocusChangeListener { view, hasFocus ->
            etID = 7
            swTimeDist.isChecked = false
        }

        etPaceM.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

                if (!swTimeDist.isChecked && etID == 1) {
                    mKmToKmH()
                    time()
                }
                if (swTimeDist.isChecked && etID == 1) {
                    mKmToKmH()
                    dist()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        etPaceS.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (!swTimeDist.isChecked && etID == 2) {
                    mKmToKmH()
                    time()
                }
                if (swTimeDist.isChecked && etID == 2) {
                    mKmToKmH()
                    dist()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        etSpeed.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (!swTimeDist.isChecked && etID == 3) {
                    kmHToMKm()
                    time()
                }
                if (swTimeDist.isChecked && etID == 3) {
                    kmHToMKm()
                    dist()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        etCalcDistH.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (swSpeedDist.isChecked && etID == 4) {
                    speed()
                }

                if (!swSpeedDist.isChecked && etID == 4) {
                    dist()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        etCalcDistM.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (swSpeedDist.isChecked && etID == 5) {
                    speed()
                }

                if (!swSpeedDist.isChecked && etID == 5) {
                    dist()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        etCalcDistS.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (swSpeedDist.isChecked && etID == 6) {
                    speed()
                }

                if (!swSpeedDist.isChecked && etID == 6) {
                    dist()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        etCalcKmh.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (swSpeedTime.isChecked && etID == 7) {
                    speed()
                }

                if (!swSpeedTime.isChecked && etID == 7) {
                    time()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        btClear.setOnClickListener {
            clear()
        }
    }

    private fun mKmToKmH() {
        notNull()
        if (etMin != 0 && etSec == 0 || etMin == 0 && etSec != 0 || etMin != 0 && etSec != 0) {
            val allSecond = ((etMin * hour) + etSec).toDouble()
            val mSec = (km / allSecond)
            val res = (mSec * second).toBigDecimal().setScale(2, RoundingMode.HALF_UP)
            etSpeed.setText("$res")
        }
    }

    private fun kmHToMKm() {
        notNull()
        if (etKm != 0.00) {
            val res = (etKm / second)
            val sumSec = (km / res).toBigDecimal().setScale(0, RoundingMode.HALF_UP).toInt()
            etMin = (sumSec / hour)
            etSec = sumSec - (etMin * hour)

            if (etMin == 0) etPaceM.setText("00") else etPaceM.setText(etMin.toString())

            if (etKm < 1) etSpeed.setText("1.00")

            if (etSec < 10) etPaceS.setText("0$etSec") else etPaceS.setText(etSec.toString())
        } else {
            etPaceM.setText("0")
            etPaceS.setText("00")
        }
    }

    private fun dist() {
        notNull()

        if (etKm != 0.0) {
            etKm = etSpeed.text.toString().toDouble()
            val distSec =
                (((etCalcDistHour * hour) + etCalcDistMin) * hour) + etCalcDistSec.toDouble()
            val mSec = etKm / 3600
            val res = (distSec * mSec).toBigDecimal().setScale(3, RoundingMode.HALF_UP).toDouble()
            etCalcKmh.setText("$res")
        }
    }

    private fun time() {
        notNull()

        if (etKm != 0.0) {
            etKm = etSpeed.text.toString().toDouble()
            val sumSec = etCalcKm / etKm * 3600
            val hour2 = (etCalcKm / etKm).toInt()
            val min = ((sumSec - (hour2 * 3600)) / hour).toInt()
            val sec = (sumSec - (hour2 * 3600) - (min * hour)).toInt()
            etCalcDistH.setText("$hour2")
            etCalcDistM.setText("$min")
            etCalcDistS.setText("$sec")
            if (sumSec == 0.0 && !etCalcKmh.isFocused) etCalcKmh.setText("0.00")
        }
    }

    private fun speed() {
        notNull()
        val timeCalc = (((etCalcDistHour * hour) + etCalcDistMin) * hour) + etCalcDistSec
        val distCalc = etCalcKm

        if (timeCalc != 0 || distCalc != 0.0) {
            val distSec =
                (((etCalcDistHour * hour) + etCalcDistMin) * hour) + etCalcDistSec.toDouble()
            val distM = etCalcKm * km
            val mSec = distM / distSec
            val res =
                ((mSec * 3600) / km).toBigDecimal().setScale(2, RoundingMode.HALF_UP).toDouble()
            etSpeed.setText("$res")
            kmHToMKm()
        }
    }

    private fun notNull() {
        etMin = if (etPaceM.text.toString().isEmpty()) 0 else etPaceM.text.toString().toInt()
        if (etMin == 0 && !etPaceM.isFocused) etPaceM.setText("0")

        etSec = if (etPaceS.text.toString().isEmpty()) 0 else etPaceS.text.toString().toInt()
        if (etSec == 0 && !etPaceS.isFocused) etPaceS.setText("00")

        etKm =
            if (etSpeed.text.toString().isEmpty() || etSpeed.text.toString() == ".") 0.0
            else etSpeed.text.toString().toDouble()
        etCalcDistHour =
            if (etCalcDistH.text.toString().isEmpty()) 0 else etCalcDistH.text.toString().toInt()
        etCalcDistMin =
            if (etCalcDistM.text.toString().isEmpty()) 0 else etCalcDistM.text.toString().toInt()
        etCalcDistSec =
            if (etCalcDistS.text.toString().isEmpty()) 0 else etCalcDistS.text.toString().toInt()
        etCalcKm =
            if (etCalcKmh.text.toString().isEmpty() || etCalcKmh.text.toString() == ".") 0.0
            else etCalcKmh.text.toString().toDouble()

    }

    private fun clear() {
        etID = 0
        etPaceM.setText("")
        etPaceS.setText("")
        etSpeed.setText("")
        etCalcDistH.setText("")
        etCalcDistM.setText("")
        etCalcDistS.setText("")
        etCalcKmh.setText("")
    }

}