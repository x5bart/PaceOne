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
    var time = etCalcDistHour + etCalcDistMin + etCalcDistSec
    var etCalcKm = 0.0
    var alertId = 0
    val TAG = "myLogs"
    val dialog = Dialog()

    public companion object {
        var etID = 0
        var flag = 0
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        etPaceM.setOnFocusChangeListener { view, hasFocus ->
            etID = 1
            if (time != 0 && etCalcKm != 0.0) {
                if (alertId != 1) {
                    dialog.show(fragmentManager!!, "dialog")
                }
            }
            alertId = 1
            Log.d(TAG, "id = $etID")
        }
        etPaceS.setOnFocusChangeListener { view, hasFocus ->
            etID = 2
            if (time != 0 && etCalcKm != 0.0) {
                if (alertId != 1) {
                    dialog.show(fragmentManager!!, "dialog")
                }
            }
            alertId = 1
            Log.d(TAG, "id = $etID")
        }
        etSpeed.setOnFocusChangeListener { view, hasFocus ->
            etID = 3
            if (time != 0 && etCalcKm != 0.0) {
                if (alertId != 1) {
                    dialog.show(fragmentManager!!, "dialog")
                }
            }
            alertId = 1
            Log.d(TAG, "id = $etID")
        }
        etCalcDistH.setOnFocusChangeListener { view, hasFocus ->
            etID = 4
            if (etKm != 0.0 && etCalcKm != 0.0) {
                if (alertId != 2) {
//                    dialog.show(fragmentManager!!,"dialog")
                    alert()
                }
            }
            alertId = 2
            Log.d(TAG, "id = $etID")
        }
        etCalcDistM.setOnFocusChangeListener { view, hasFocus ->
            etID = 5
            if (etKm != 0.0 && etCalcKm != 0.0) {
                if (alertId != 2) {
//                    dialog.show(fragmentManager!!,"dialog")
                    alert()
                }
            }
            alertId = 2
            Log.d(TAG, "id = $etID")
        }
        etCalcDistS.setOnFocusChangeListener { view, hasFocus ->
            etID = 6
            if (etKm != 0.0 && etCalcKm != 0.0) {
                if (alertId != 2) {
//                    dialog.show(fragmentManager!!,"dialog")
                    alert()
                }
            }
            alertId = 2
            Log.d(TAG, "id = $etID")
        }
        etCalcKmh.setOnFocusChangeListener { view, hasFocus ->
            etID = 7
            if (etKm != 0.0 && time != 0) {
                if (alertId != 3) {
                    dialog.show(fragmentManager!!, "dialog")
                }
            }
            alertId = 3
            Log.d(TAG, "id = $etID")
        }

        etPaceM.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

                if (etID == 1) {
                    mKmToKmH()
                    if (flag == 2) time()
                    if (flag == 3) dist()
                    if (time == 0 && etCalcKm != 0.0) {
                        time()
                        flag = 2
                    }
                    if (time != 0 && etCalcKm == 0.0) {
                        dist()
                        flag = 3
                    }
                }
                Log.d(TAG, "flag = $flag")
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        etPaceS.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (etID == 2) {
                    mKmToKmH()
                    if (flag == 2) time()
                    if (flag == 3) dist()
                    if (time == 0 && etCalcKm != 0.0) {
                        time()
                        flag = 2
                    }
                    if (time != 0 && etCalcKm == 0.0) {
                        dist()
                        flag = 3
                    }
                }
                Log.d(TAG, "flag = $flag")
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        etSpeed.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (etID == 3) {
                    kmHToMKm()
                    if (flag == 2) time()
                    if (flag == 3) dist()
                    if (time == 0 && etCalcKm != 0.0) {
                        time()
                        flag = 2
                    }
                    if (time != 0 && etCalcKm == 0.0) {
                        dist()
                        flag = 3
                    }
                }
                Log.d(TAG, "flag = $flag")
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        etCalcDistH.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (etID == 4) {
                    notNull()
                    if (flag == 1) speed()
                    if (flag == 3) dist()
                    if (etKm == 0.0 && etCalcKm != 0.0) {
                        speed()
                        flag = 1
                    }
                    if (etKm != 0.0 && etCalcKm == 0.0) {
                        dist()
                        flag = 3
                    }
                }
                Log.d(TAG, "flag = $flag")
                Log.d(TAG, "etKM = $etKm etCalcKM = $etCalcKm")
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        etCalcDistM.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (etID == 5) {
                    notNull()
                    if (flag == 1) speed()
                    if (flag == 3) dist()
                    if (etKm == 0.0 && etCalcKm != 0.0) {
                        speed()
                        flag = 1
                    }
                    if (etKm != 0.0 && etCalcKm == 0.0) {
                        dist()
                        flag = 3
                    }
                }
                Log.d(TAG, "flag = $flag")
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        etCalcDistS.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (etID == 6) {
                    notNull()
                    if (flag == 1) speed()
                    if (flag == 3) dist()
                    if (etKm == 0.0 && etCalcKm != 0.0) {
                        speed()
                        flag = 1
                    }
                    if (etKm != 0.0 && etCalcKm == 0.0) {
                        dist()
                        flag = 3
                    }
                }
                Log.d(TAG, "flag = $flag")
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        etCalcKmh.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (etID == 7) {
                    notNull()
                    if (flag == 1) speed()
                    if (flag == 2) time()
                    if (etKm == 0.0 && time != 0) {
                        speed()
                        flag = 1
                    }
                    if (etKm != 0.0 && time == 0) {
                        time()
                        flag = 2
                    }
                }
                Log.d(TAG, "flag = $flag")
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        etPaceM.setOnClickListener { etPaceM.selectAll() }
        etPaceS.setOnClickListener { etPaceS.selectAll() }
        etSpeed.setOnClickListener { etSpeed.selectAll() }
        etCalcDistH.setOnClickListener { etCalcDistH.selectAll() }
        etCalcDistM.setOnClickListener { etCalcDistM.selectAll() }
        etCalcDistS.setOnClickListener { etCalcDistS.selectAll() }
        etCalcKmh.setOnClickListener { etCalcKmh.selectAll() }


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
        notNull()
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
        notNull()
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
        notNull()
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
        notNull()
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
        notNull()
    }

    private fun notNull() {
        etMin = if (etPaceM.text.toString().isEmpty()) 0 else etPaceM.text.toString().toInt()
        if (etMin == 0 && !etPaceM.isFocused) etPaceM.setText("0")

        etSec = if (etPaceS.text.toString().isEmpty()) 0 else etPaceS.text.toString().toInt()
        if (etSec == 0 && !etPaceS.isFocused) etPaceS.setText("00")

        etKm =
            if (etSpeed.text.toString().isEmpty() || etSpeed.text.toString() == ".") 0.0
            else etSpeed.text.toString().toDouble()
        if (etKm == 0.0 && !etSpeed.isFocused) etSpeed.setText("0.00")

        etCalcDistHour =
            if (etCalcDistH.text.toString().isEmpty()) 0 else etCalcDistH.text.toString().toInt()
        if (etCalcDistHour == 0 && !etCalcDistH.isFocused) etCalcDistH.setText("0")

        etCalcDistMin =
            if (etCalcDistM.text.toString().isEmpty()) 0 else etCalcDistM.text.toString().toInt()
        if (etCalcDistMin == 0 && !etCalcDistM.isFocused) etCalcDistM.setText("00")

        etCalcDistSec =
            if (etCalcDistS.text.toString().isEmpty()) 0 else etCalcDistS.text.toString().toInt()
        if (etCalcDistSec == 0 && !etCalcDistS.isFocused) etCalcDistS.setText("00")

        etCalcKm =
            if (etCalcKmh.text.toString().isEmpty() || etCalcKmh.text.toString() == ".") 0.0
            else etCalcKmh.text.toString().toDouble()
        if (etCalcKm == 0.0 && !etCalcKmh.isFocused) etCalcKmh.setText("0.00")

        time = etCalcDistHour + etCalcDistMin + etCalcDistSec
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
        flag = 0
    }

    private fun alert() {
        var btn1 = ""
        var btn2 = ""
        var btn1Flag = 0
        var btn2Flag = 0

        if (etID in 1..3) {
            btn1 = "Time"
            btn1Flag = 2
            btn2 = "Dist"
            btn2Flag = 3
        }
        if (etID in 4..6) {
            btn1 = "Speed"
            btn1Flag = 1
            btn2 = "Dist"
            btn2Flag = 3
        }
        if (etID == 7) {
            btn1 = "Time"
            btn1Flag = 2
            btn2 = "Speed"
            btn2Flag = 1
        }

        val builder = AlertDialog.Builder(activity!!)
        val message = "Что рассчитать?"
        builder.setMessage(message)

        builder.setNegativeButton("$btn1") { _, _ ->
            flag = btn1Flag
        }
        builder.setPositiveButton("$btn2") { _, _ ->
            flag = btn2Flag
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


}