package com.x5bart_soft.paceone


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.x5bart_soft.paceone.segment.Fragment2
import com.yandex.mobile.ads.AdRequest
import com.yandex.mobile.ads.AdSize
import kotlinx.android.synthetic.main.fragment1.*
import java.math.RoundingMode
import java.util.*


class Fragment1 : Fragment() {
    private var etMin = 0
    private var etSec = 0
    private var etKm = 0.0
    private var etCalcDistHour = 0
    private var etCalcDistMin = 0
    private var etCalcDistSec = 0
    var time = etCalcDistHour + etCalcDistMin + etCalcDistSec
    var timeSec = 0
    var etCalcKm = 0.0
    var alertId = 0
    val REQUEST_WEIGHT = 2
    var tmp = 0


    companion object {
        var etID = 0
        var flag = 0
        var resume = 0
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val locale = Locale.getDefault()
        val configuration = Configuration()
        configuration.locale = locale
        activity!!.baseContext.resources.updateConfiguration(configuration, null)



        return inflater.inflate(R.layout.fragment1, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        tmp = MainActivity.FLAG_MILE_TO_KM
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        MainActivity.FLAG_MILE_TO_KM = tmp
        when (MainActivity.FLAG_MILE_TO_KM) {
            1 -> {
                swKmToMile.isChecked = false
            }
            2 -> {
                swKmToMile.isChecked = true
            }
        }
        if (resume in 1..2) {
            kmToMileSw()
            kmHToMKm()
            resume = 0
        }
        super.onStart()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {



        etID = 0
        btnSegment.isVisible = false
        visible()


        val BLOCK_ID = "adf-326819/1042468"
        banner_view.blockId = BLOCK_ID
        banner_view.adSize = AdSize.BANNER_320x50
        val adRequest = AdRequest.builder().build()
        banner_view.loadAd(adRequest)

        when (MainActivity.FLAG_MILE_TO_KM) {
            1 -> {
                textView5.setTextColor(resources.getColor(R.color.textActive))
                textView5_1.setTextColor(resources.getColor(R.color.textNotActive))
            }
            2 -> {
                textView5.setTextColor(resources.getColor(R.color.textNotActive))
                textView5_1.setTextColor(resources.getColor(R.color.textActive))
            }
        }

        etPaceM.setOnFocusChangeListener { _, _ ->
            etID = 1
        }
        etPaceS.setOnFocusChangeListener { _, _ ->
            etID = 2
        }
        etSpeed.setOnFocusChangeListener { _, _ ->
            etID = 3
        }
        etCalcDistH.setOnFocusChangeListener { _, _ ->
            etID = 4
        }
        etCalcDistM.setOnFocusChangeListener { _, _ ->
            etID = 5
        }
        etCalcDistS.setOnFocusChangeListener { _, _ ->
            etID = 6
        }
        etCalcKmh.setOnFocusChangeListener { _, _ ->
            etID = 7
        }

        etPaceM.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (etID == 1) {
                    if (time != 0 && etCalcKm != 0.0 && alertId != 1) {
                        alertId = 1
                        alertDialog()
                    } else {
                        etPaceM()
                        alertId = 1
                    }
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        etPaceS.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (etID == 2)
                    if (time != 0 && etCalcKm != 0.0 && alertId != 1) {
                        alertId = 1
                        alertDialog()
                    } else {
                        etPaceS()
                        alertId = 1
                    }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        etSpeed.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (etID == 3) {
                    if (time != 0 && etCalcKm != 0.0 && alertId != 1) {
                        alertId = 1
                        alertDialog()
                    } else {
                        etKmh()
                        alertId = 1
                    }
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        etCalcDistH.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (etID == 4) {
                    if (etKm != 0.0 && etCalcKm != 0.0 && alertId != 2) {
                        alertId = 2
                        alertDialog()
                    } else {
                        etTimeH()
                        alertId = 2
                    }
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        etCalcDistM.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (etID == 5) {
                    if (etKm != 0.0 && etCalcKm != 0.0 && alertId != 2) {
                        alertId = 2
                        alertDialog()
                    } else {
                        etTimeM()
                        alertId = 2
                    }
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        etCalcDistS.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (etID == 6) {
                    if (etKm != 0.0 && etCalcKm != 0.0 && alertId != 2) {
                        alertId = 2
                        alertDialog()
                    } else {
                        etTimeS()
                        alertId = 2
                    }
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        etCalcKmh.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (etID == 7) {
                    if (etKm != 0.0 && time != 0 && alertId != 3) {
                        alertId = 3
                        alertDialog()
                    } else {
                        etDist()
                        alertId = 3
                    }
                }
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

        btnSegment.setOnClickListener {
            notNull()
            val bundle = Bundle()
            val dist = etCalcKm
            bundle.putInt("h", etCalcDistHour)
            bundle.putInt("m", etCalcDistMin)
            bundle.putInt("s", etCalcDistSec)
            bundle.putDouble("dist", dist)
            val frg = Fragment2()
            frg.arguments = bundle
            val ft = fragmentManager!!.beginTransaction()
            ft.addToBackStack("frag")
            ft.replace(R.id.frgCont, frg)
            ft.commit()
        }

        ivInfoKm.setOnClickListener { showPopupKm(ivInfoKm) }

        swKmToMile.setOnCheckedChangeListener { compoundButton, b ->
            when (b) {
                false -> {
                    MainActivity.FLAG_MILE_TO_KM = 1
                    textView2.text = resources.getString(R.string.min_km)
                    textView.text = resources.getString(R.string.km_h)
                    textView11.text = resources.getString(R.string.km)
                    textView5.setTextColor(resources.getColor(R.color.textActive))
                    textView5_1.setTextColor(resources.getColor(R.color.textNotActive))
                    saveSw()
                }
                true -> {
                    MainActivity.FLAG_MILE_TO_KM = 2
                    textView2.text = resources.getString(R.string.min_mile)
                    textView.text = resources.getString(R.string.km_mile)
                    textView11.text = resources.getString(R.string.mile)
                    textView5.setTextColor(resources.getColor(R.color.textNotActive))
                    textView5_1.setTextColor(resources.getColor(R.color.textActive))
                    saveSw()
                }
            }
        }
        swKmToMile.setOnClickListener {
            val tmp = etID
            etID = 0
            kmToMileSw()
            notNull()
            kmHToMKm()
            etID = tmp
        }
    }

    fun saveSw() {
        val sPref = activity!!.getPreferences(Context.MODE_PRIVATE)
        val ed = sPref.edit()
        ed.putInt(MainActivity.APP_MILE_TO_KM, MainActivity.FLAG_MILE_TO_KM)
        ed.apply()
    }

    private fun showPopupKm(v: View) {
        val popupMenu = PopupMenu(activity, v, Gravity.NO_GRAVITY)
        popupMenu.inflate(R.menu.popup_menu_km)
        if (MainActivity.FLAG_MILE_TO_KM == 2) {
            popupMenu.menu.findItem(R.id.km_3).setTitle(R.string._3_km_mile)
            popupMenu.menu.findItem(R.id.km_5).setTitle(R.string._5_km_mile)
            popupMenu.menu.findItem(R.id.km_10).setTitle(R.string._10_km_mile)
        }

        popupMenu.setOnMenuItemClickListener { item ->
            etID = 7
            etCalcKmh.requestFocus()
            var tmp = 0.0
            when (item.itemId) {
                R.id.km_3 -> tmp = 3.00
                R.id.km_5 -> tmp = 5.00
                R.id.km_10 -> tmp = 10.00
                R.id.km_21 -> tmp = 21.097
                R.id.km_42 -> tmp = 42.195
            }
            if (MainActivity.FLAG_MILE_TO_KM == 2) tmp /= (MainActivity.MILEKM / MainActivity.KM)

            etCalcKmh.setText("$tmp")
            true
        }
        popupMenu.show()
    }

    fun etPaceM() {
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

    fun etPaceS() {
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

    fun etKmh() {
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

    fun etTimeH() {
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

    fun etTimeM() {
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

    fun etTimeS() {
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

    fun etDist() {
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


    fun kmToMile() {
        val res = (etKm / (MainActivity.MILEKM / MainActivity.KM))
            .toBigDecimal()
            .setScale(3, RoundingMode.HALF_UP)
        etSpeed.setText("$res")
        val res2 = (etCalcKm / (MainActivity.MILEKM / MainActivity.KM))
            .toBigDecimal()
            .setScale(3, RoundingMode.HALF_UP).toDouble()
        etCalcKmh.setText("$res2")

    }

    fun mileToKm() {
        val res = (etKm * (MainActivity.MILEKM / MainActivity.KM))
            .toBigDecimal()
            .setScale(3, RoundingMode.HALF_UP)
        etSpeed.setText("$res")
        val res2 = (etCalcKm * (MainActivity.MILEKM / MainActivity.KM))
            .toBigDecimal()
            .setScale(3, RoundingMode.HALF_UP).toDouble()
        etCalcKmh.setText("$res2")
    }

    fun kmToMileSw() {
        when (MainActivity.FLAG_MILE_TO_KM) {
            1 -> {
                mileToKm()
            }
            2 -> {
                kmToMile()
            }
        }
    }

    fun mKmToKmH() {
        notNull()
        if (etMin != 0 && etSec == 0 || etMin == 0 && etSec != 0 || etMin != 0 && etSec != 0) {
            val allSecond = ((etMin * MainActivity.MINUTE) + etSec).toDouble()
            val mSec = (MainActivity.unit / allSecond)
            val res = (mSec * MainActivity.MSEC)
                .toBigDecimal()
                .setScale(2, RoundingMode.HALF_UP)
            etSpeed.setText("$res")
        }
        notNull()
    }

    fun kmHToMKm() {
        notNull()
        if (etKm != 0.00) {
            if (etKm < 1) {
                etSpeed.setText("1.00")
                etKm = 1.0
            }
            val res = (etKm / MainActivity.MSEC)
            val sumSec =
                (MainActivity.unit / res)
                    .toBigDecimal()
                    .setScale(0, RoundingMode.HALF_UP)
                    .toInt()
            etMin = (sumSec / MainActivity.MINUTE)
            etSec = sumSec - (etMin * MainActivity.MINUTE)
            if (etMin == 0) etPaceM.setText("00") else etPaceM.setText(etMin.toString())
            if (etSec < 10) etPaceS.setText("0$etSec") else etPaceS.setText(etSec.toString())
        } else {
            etPaceM.setText("0")
            etPaceS.setText("00")
        }
        notNull()
    }

    fun dist() {
        notNull()
        if (etKm != 0.0) {
            val pace = ((etMin * MainActivity.MINUTE) + etSec).toDouble()
            val distSec =
                (((etCalcDistHour * MainActivity.MINUTE) + etCalcDistMin) * MainActivity.MINUTE) + etCalcDistSec
            val res = (distSec / pace).toBigDecimal()
                .setScale(3, RoundingMode.HALF_UP)
            etCalcKmh.setText("$res")
        }
        notNull()
    }

    fun time() {
        notNull()
        if (etKm != 0.0) {
            etKm = etSpeed.text.toString().toDouble()
            val sumSec =
                (etCalcKm / etKm * MainActivity.HOUR).toBigDecimal().setScale(0, RoundingMode.UP)
                    .toInt()
            val hour = (etCalcKm / etKm).toInt()
            val min = ((sumSec - (hour * MainActivity.HOUR)) / MainActivity.MINUTE).toInt()
            val sec = (sumSec - (hour * MainActivity.HOUR) - (min * MainActivity.MINUTE)).toInt()
            etCalcDistH.setText("$hour")
            etCalcDistM.setText("$min")
            etCalcDistS.setText("$sec")
            if (sumSec == 0 && !etCalcKmh.isFocused) etCalcKmh.setText("0.00")
        }
        notNull()
    }

    fun speed() {
        notNull()
        if (etCalcDistHour != 0 || etCalcDistMin != 0 || etCalcDistSec != 0) {
            val timeCalc =
                (((etCalcDistHour * MainActivity.MINUTE) + etCalcDistMin) * MainActivity.MINUTE) + etCalcDistSec
            val distCalc = etCalcKm

            if (timeCalc != 0 || distCalc != 0.0) {
                val distSec =
                    (((etCalcDistHour * MainActivity.MINUTE) + etCalcDistMin) * MainActivity.MINUTE) + etCalcDistSec.toDouble()
                val distM = etCalcKm * MainActivity.KM
                val mSec = distM / distSec
                val res =
                    ((mSec * MainActivity.HOUR) / MainActivity.KM).toBigDecimal()
                        .setScale(2, RoundingMode.HALF_UP)
                        .toDouble()
                etSpeed.setText("$res")
                kmHToMKm()
            }
            notNull()
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
        if (etKm == 0.0 && !etSpeed.isFocused) etSpeed.setText("0.00")

        etCalcDistHour =
            if (etCalcDistH.text.toString().isEmpty()) 0 else etCalcDistH.text.toString().toInt()
        if (etCalcDistHour == 0 && !etCalcDistH.isFocused) etCalcDistH.setText("0")

        etCalcDistMin =
            if (etCalcDistM.text.toString().isEmpty()) 0 else etCalcDistM.text.toString().toInt()
        if (etCalcDistMin < 10 && !etCalcDistM.isFocused) etCalcDistM.setText("0$etCalcDistMin")
        if (etCalcDistMin == 0 && !etCalcDistM.isFocused) etCalcDistM.setText("00")

        etCalcDistSec =
            if (etCalcDistS.text.toString().isEmpty()) 0 else etCalcDistS.text.toString().toInt()
        if (etCalcDistSec < 10 && !etCalcDistS.isFocused) etCalcDistS.setText("0$etCalcDistSec")
        if (etCalcDistSec == 0 && !etCalcDistS.isFocused) etCalcDistS.setText("00")

        etCalcKm =
            if (etCalcKmh.text.toString().isEmpty() || etCalcKmh.text.toString() == ".") 0.0
            else etCalcKmh.text.toString().toDouble()
        if (etCalcKm == 0.0 && !etCalcKmh.isFocused) etCalcKmh.setText("0.00")


        time = etCalcDistHour + etCalcDistMin + etCalcDistSec
        visible()
    }

    private fun visible() {
        if (etKm != 0.0 && time != 0 && etCalcKm != 0.0) btnSegment.isVisible = true
    }

    private fun clear() {
        if (!etPaceM.isFocused && !etPaceS.isFocused && !etSpeed.isFocused && !etCalcDistH.isFocused && !etCalcDistM.isFocused && !etCalcDistS.isFocused && !etCalcKmh.isFocused) etCalcKmh.requestFocus()
        flag = 0
        time = 0
        etKm = 0.0
        etCalcKm = 0.0
        timeSec = 0

        etPaceM.setText("")
        etPaceS.setText("")
        etSpeed.setText("")
        etCalcDistH.setText("")
        etCalcDistM.setText("")
        etCalcDistS.setText("")
        etCalcKmh.setText("")
        btnSegment.isVisible = false
        when (etID) {
            1 -> {
                etPaceM.requestFocus()
                etID = 1
                alertId = 1
            }
            2 -> {
                etPaceS.requestFocus()
                etID = 2
                alertId = 1
            }
            3 -> {
                etSpeed.requestFocus()
                etID = 3
                alertId = 1
            }
            4 -> {
                etCalcDistH.requestFocus()
                etID = 4
                alertId = 2
            }
            5 -> {
                etCalcDistM.requestFocus()
                etID = 5
                alertId = 2
            }
            6 -> {
                etCalcDistS.requestFocus()
                etID = 6
                alertId = 2
            }
            7 -> {
                etCalcKmh.requestFocus()
                etID = 7
                alertId = 3
            }
        }
        notNull()
    }

    private fun alertDialog() {
        val dialog = AlertDialog()
        dialog.setTargetFragment(this, REQUEST_WEIGHT)
        dialog.show(fragmentManager!!, "AlertDialog")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val FLAG = data!!.getIntExtra("2", 0)
            flag = FLAG
            val etID = data.getIntExtra("1", 0)
            when (etID) {
                1 -> etPaceM()
                2 -> etPaceS()
                3 -> etKmh()
                4 -> etTimeH()
                5 -> etTimeM()
                6 -> etTimeS()
                7 -> etDist()
            }

        }
    }


}