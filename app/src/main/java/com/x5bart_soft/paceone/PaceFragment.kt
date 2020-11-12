package com.x5bart_soft.paceone


import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.x5bart_soft.paceone.databinding.Fragment1Binding
import com.yandex.mobile.ads.AdRequest
import com.yandex.mobile.ads.AdSize
import java.util.*


class PaceFragment : Fragment() {
    private lateinit var binding: Fragment1Binding
    private lateinit var preference: MyPreference
    private lateinit var function: PaceFunction
    private var hasCalculate = false
    private lateinit var paceObject: Pace

    //    private var etMin = 0
//    private var etSec = 0
//    private var etKm = 0.0
//    private var etCalcDistHour = 0
//    private var etCalcDistMin = 0
//    private var etCalcDistSec = 0
//    var time = etCalcDistHour + etCalcDistMin + etCalcDistSec
//    var timeSec = 0
//    var etCalcKm = 0.0

    val REQUEST_WEIGHT = 2
//    companion object {


//    var tmp = 0
//        var flag = 0
//        var resume = 0
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val locale = Locale.getDefault()
        val configuration = Configuration()
        configuration.locale = locale
        activity!!.baseContext.resources.updateConfiguration(configuration, null)

        binding = Fragment1Binding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        preference = MyPreference(activity!!.applicationContext)
        function = PaceFunction()
        paceObject = Pace


//        binding.btnSegment.visibility = View.GONE
//        visible()

        val BLOCK_ID = "adf-326819/1042468"
        binding.bannerView.blockId = BLOCK_ID
        binding.bannerView.adSize = AdSize.BANNER_320x50
        val adRequest = AdRequest.builder().build()
        binding.bannerView.loadAd(adRequest)

        when (Config.FLAG_MILE_TO_KM) {
            false -> {
                binding.swKmToMile.isChecked = false
                binding.textView5.setTextColor(resources.getColor(R.color.textActive))
                binding.textView51.setTextColor(resources.getColor(R.color.textNotActive))
            }
            true -> {
                binding.swKmToMile.isChecked = true
                binding.textView5.setTextColor(resources.getColor(R.color.textNotActive))
                binding.textView51.setTextColor(resources.getColor(R.color.textActive))
            }
        }

        binding.etPaceM.setOnFocusChangeListener { _, _ ->
            paceObject.etID = "paceM"
        }
        binding.etPaceS.setOnFocusChangeListener { _, _ ->
            paceObject.etID = "paceS"

        }
        binding.etSpeed.setOnFocusChangeListener { _, _ ->
            paceObject.etID = "speed"
        }
        binding.etTimeH.setOnFocusChangeListener { _, _ ->
            paceObject.etID = "timeH"
        }
        binding.etTimeM.setOnFocusChangeListener { _, _ ->
            paceObject.etID = "timeM"
        }
        binding.etTimeS.setOnFocusChangeListener { _, _ ->
            paceObject.etID = "timeS"
        }
        binding.etDistance.setOnFocusChangeListener { _, _ ->
            paceObject.etID = "dist"
        }

        binding.etPaceM.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (paceObject.etID == "paceM") listeners()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        binding.etPaceS.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (paceObject.etID == "paceS") listeners()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        binding.etSpeed.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (paceObject.etID == "speed") listeners()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        binding.etTimeH.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (paceObject.etID == "timeH") listeners()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        binding.etTimeM.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (paceObject.etID == "timeM") listeners()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        binding.etTimeS.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (paceObject.etID == "timeS") listeners()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        binding.etDistance.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (paceObject.etID == "dist") listeners()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        binding.etPaceM.setOnClickListener { binding.etPaceM.selectAll() }
        binding.etPaceS.setOnClickListener { binding.etPaceS.selectAll() }
        binding.etSpeed.setOnClickListener { binding.etSpeed.selectAll() }
        binding.etTimeH.setOnClickListener { binding.etTimeH.selectAll() }
        binding.etTimeM.setOnClickListener { binding.etTimeM.selectAll() }
        binding.etTimeS.setOnClickListener { binding.etTimeS.selectAll() }
        binding.etDistance.setOnClickListener { binding.etDistance.selectAll() }


        binding.btClear.setOnClickListener {
            view.clearFocus()
            function.clear()
            binding.etPaceM.setText("")
            binding.etPaceS.setText("")
            binding.etSpeed.setText("")
            binding.etTimeH.setText("")
            binding.etTimeM.setText("")
            binding.etTimeS.setText("")
            binding.etDistance.setText("")

        }

        binding.ivInfoKm.setOnClickListener { showPopupKm(binding.ivInfoKm) }

//        binding.swKmToMile.setOnCheckedChangeListener { compoundButton, b ->
//            when (b) {
//                false -> {
//                    Config.FLAG_MILE_TO_KM = false
//                    binding.textView2.text = resources.getString(R.string.min_km)
//                    binding.textView.text = resources.getString(R.string.km_h)
//                    binding.textView11.text = resources.getString(R.string.km)
//                    binding.textView5.setTextColor(resources.getColor(R.color.textActive))
//                    binding.textView51.setTextColor(resources.getColor(R.color.textNotActive))
//                    saveSw()
//                }
//                true -> {
//                    Config.FLAG_MILE_TO_KM = true
//                    binding.textView2.text = resources.getString(R.string.min_mile)
//                    binding.textView.text = resources.getString(R.string.mile_hour)
//                    binding.textView11.text = resources.getString(R.string.mile)
//                    binding.textView5.setTextColor(resources.getColor(R.color.textNotActive))
//                    binding.textView51.setTextColor(resources.getColor(R.color.textActive))
//                    saveSw()
//                }
//            }
//        }
//        binding.swKmToMile.setOnClickListener {
//            val tmp = etID
//            etID = 0
//            kmToMileSw()
//            notNull()
//            kmHToMKm()
//            etID = tmp
//        }
    }

    fun listeners() {
        when (paceObject.etID) {

            "paceM" -> {
                paceObject.tempM = function.readEt(binding.etPaceM).toInt()
                if (!paceObject.isInvalidInput) {
                    function.tempAll()
                    function.tempToSpeed()
                    if (paceObject.activeFunction == "speed") hasCalculate = true
                    if (paceObject.timeAll != 0 && paceObject.distance != 0.0 && hasCalculate ) {
                        hasCalculate = false
                        alertDialog()
                    } else {
                        if (paceObject.timeAll == 0 && paceObject.distance != 0.0
                            || paceObject.activeFunction == "time"
                        ) {
                            function.time()
                            paceObject.activeFunction = "time"
                        }
                        if (paceObject.timeAll != 0 && paceObject.distance == 0.0 ||
                            paceObject.activeFunction == "dist"
                        ) {
                            function.distance()
                            paceObject.activeFunction = "dist"
                        }
                        paceObject.lod()
                        paceObject.calculateDialog = "speed"
                        writeEt()
                    }
                }
            }
            "paceS" -> {
                paceObject.tempS = function.readEt(binding.etPaceS).toInt()
                if (!paceObject.isInvalidInput) {
                    function.tempAll()
                    function.tempToSpeed()
                    if (paceObject.activeFunction == "speed") hasCalculate = true
                    if (paceObject.timeAll != 0 && paceObject.distance != 0.0 && hasCalculate ) {
                        hasCalculate = false
                        alertDialog()
                    } else {
                        if (paceObject.timeAll == 0 && paceObject.distance != 0.0
                            || paceObject.activeFunction == "time"
                        ) {
                            function.time()
                            paceObject.activeFunction = "time"
                        }
                        if (paceObject.timeAll != 0 && paceObject.distance == 0.0 ||
                            paceObject.activeFunction == "dist"
                        ) {
                            function.distance()
                            paceObject.activeFunction = "dist"
                        }
                        paceObject.lod()
                        paceObject.calculateDialog = "speed"
                        writeEt()
                    }
                }
            }
            "speed" -> {
                paceObject.speed = function.readEt(binding.etSpeed).toDouble()
                if (!paceObject.isInvalidInput) {
                    function.speedToTemp()
                    if (paceObject.activeFunction == "speed") hasCalculate = true
                    if (paceObject.timeAll != 0 && paceObject.distance != 0.0 && hasCalculate ) {
                        hasCalculate = false
                        alertDialog()
                    } else {
                        if (paceObject.timeAll == 0 && paceObject.distance != 0.0 ||
                            paceObject.activeFunction == "time"
                        ) {
                            function.time()
                            paceObject.activeFunction = "time"
                        }
                        if (paceObject.timeAll != 0 && paceObject.distance == 0.0 ||
                            paceObject.activeFunction == "dist"
                        ) {
                            function.distance()
                            paceObject.activeFunction = "dist"
                        }
                        paceObject.lod()
                        paceObject.calculateDialog = "speed"
                        writeEt()
                    }
                }
            }
            "timeH" -> {
                paceObject.timeH = function.readEt(binding.etTimeH).toInt()
                if (!paceObject.isInvalidInput) {
                    function.timeAll()
                    if (paceObject.activeFunction == "time") hasCalculate = true
                    if (paceObject.speed != 0.0 && paceObject.distance != 0.0 && hasCalculate ) {
                        hasCalculate = false
                        alertDialog()
                    } else {
                        if (paceObject.speed == 0.0 && paceObject.distance != 0.0 ||
                            paceObject.activeFunction == "speed"
                        ) {
                            function.speed()
                            paceObject.activeFunction = "speed"
                        }
                        if (paceObject.speed != 0.0 && paceObject.distance == 0.0 ||
                            paceObject.activeFunction == "dist"
                        ) {
                            function.distance()
                            paceObject.activeFunction = "dist"
                        }
                        paceObject.lod()
                        paceObject.calculateDialog = "time"
                        writeEt()
                    }
                }
            }
            "timeM" -> {
                paceObject.timeM = function.readEt(binding.etTimeM).toInt()
                if (!paceObject.isInvalidInput) {
                    function.timeAll()
                    if (paceObject.activeFunction == "time") hasCalculate = true
                    if (paceObject.speed != 0.0 && paceObject.distance != 0.0 && hasCalculate ) {
                        hasCalculate = false
                        alertDialog()
                    } else {
                        if (paceObject.speed == 0.0 && paceObject.distance != 0.0 ||
                            paceObject.activeFunction == "speed"
                        ) {
                            function.speed()
                            paceObject.activeFunction = "speed"
                        }
                        if (paceObject.speed != 0.0 && paceObject.distance == 0.0 ||
                            paceObject.activeFunction == "dist"
                        ) {
                            function.distance()
                            paceObject.activeFunction = "dist"
                        }
                        paceObject.lod()
                        paceObject.calculateDialog = "time"
                        writeEt()
                    }
                }
            }
            "timeS" -> {
                paceObject.timeS = function.readEt(binding.etTimeS).toInt()
                if (!paceObject.isInvalidInput) {
                    function.timeAll()
                    if (paceObject.activeFunction == "time") hasCalculate = true
                    if (paceObject.speed != 0.0 && paceObject.distance != 0.0 && hasCalculate ) {
                        hasCalculate = false
                        alertDialog()
                    } else {
                        if (paceObject.speed == 0.0 && paceObject.distance != 0.0 ||
                            paceObject.activeFunction == "speed"
                        ) {
                            function.speed()
                            paceObject.activeFunction = "speed"
                        }
                        if (paceObject.speed != 0.0 && paceObject.distance == 0.0 ||
                            paceObject.activeFunction == "dist"
                        ) {
                            function.distance()
                            paceObject.activeFunction = "dist"
                        }
                        paceObject.lod()
                        paceObject.calculateDialog = "time"
                        writeEt()
                    }
                }
            }
            "dist" -> {
                paceObject.distance = function.readEt(binding.etDistance).toDouble()
                if (!paceObject.isInvalidInput) {

                    if (paceObject.activeFunction == "dist") hasCalculate = true

                    if (paceObject.speed != 0.0 &&
                        paceObject.timeAll != 0 &&
                        hasCalculate) {

                        hasCalculate = false
                        alertDialog()

                    } else {
                        if (paceObject.speed == 0.0 && paceObject.timeAll != 0 ||
                            paceObject.activeFunction == "speed"
                        ) {
                            function.speed()
                            paceObject.activeFunction = "speed"
                        }
                        if (paceObject.speed != 0.0 && paceObject.timeAll == 0 ||
                            paceObject.activeFunction == "time"
                        ) {
                            function.time()
                            paceObject.activeFunction = "time"
                        }
                        paceObject.lod()
                        paceObject.calculateDialog = "dist"
                        writeEt()
                    }
                }
            }
        }
    }


    private fun writeEt() {
        paceObject.lod()
        when (Pace.etID) {
            "paceM" -> {
                writePaceS()
                binding.etSpeed.setText(paceObject.speed.toString())
                binding.etTimeH.setText(paceObject.timeH.toString())
                writeTimeM()
                writeTimeS()
                binding.etDistance.setText(paceObject.distance.toString())
            }
            "paceS" -> {
                binding.etPaceM.setText(paceObject.tempM.toString())
                binding.etSpeed.setText(paceObject.speed.toString())
                binding.etTimeH.setText(paceObject.timeH.toString())
                writeTimeM()
                writeTimeS()
                binding.etDistance.setText(paceObject.distance.toString())
            }
            "speed" -> {
                binding.etPaceM.setText(paceObject.tempM.toString())
                writePaceS()
                binding.etTimeH.setText(paceObject.timeH.toString())
                writeTimeM()
                writeTimeS()
                binding.etDistance.setText(paceObject.distance.toString())
            }
            "timeH" -> {
                binding.etPaceM.setText(paceObject.tempM.toString())
                writePaceS()
                binding.etSpeed.setText(paceObject.speed.toString())
                writeTimeM()
                writeTimeS()
                binding.etDistance.setText(paceObject.distance.toString())
            }
            "timeM" -> {
                binding.etPaceM.setText(paceObject.tempM.toString())
                writePaceS()
                binding.etSpeed.setText(paceObject.speed.toString())
                binding.etTimeH.setText(paceObject.timeH.toString())
                writeTimeS()
                binding.etDistance.setText(paceObject.distance.toString())
            }
            "timeS" -> {
                binding.etPaceM.setText(paceObject.tempM.toString())
                writePaceS()
                binding.etSpeed.setText(paceObject.speed.toString())
                binding.etTimeH.setText(paceObject.timeH.toString())
                writeTimeM()
                binding.etDistance.setText(paceObject.distance.toString())
            }
            "dist" -> {
                binding.etPaceM.setText(paceObject.tempM.toString())
                writePaceS()
                binding.etSpeed.setText(paceObject.speed.toString())
                binding.etTimeH.setText(paceObject.timeH.toString())
                writeTimeM()
                writeTimeS()
            }
        }
    }

    private fun writePaceS() {
        if (paceObject.tempS < 10) binding.etPaceS.setText("0${paceObject.tempS}")
        else binding.etPaceS.setText(paceObject.tempS.toString())
    }

    private fun writeTimeM() {
        if (paceObject.timeM < 10) binding.etTimeM.setText("0${paceObject.timeM}")
        else binding.etTimeM.setText(paceObject.timeM.toString())
    }

    private fun writeTimeS() {
        if (paceObject.timeS < 10) binding.etTimeS.setText("0${paceObject.timeS}")
        else binding.etTimeS.setText(paceObject.timeS.toString())
    }

    //    fun saveSw() {
//        preference.setUnitSw(Config.FLAG_MILE_TO_KM)
//    }
//
    private fun showPopupKm(v: View) {
        val popupMenu = PopupMenu(activity, v, Gravity.NO_GRAVITY)
        popupMenu.inflate(R.menu.popup_menu_km)
        if (Config.FLAG_MILE_TO_KM) {
            popupMenu.menu.findItem(R.id.km_3).setTitle(R.string._3_km_mile)
            popupMenu.menu.findItem(R.id.km_5).setTitle(R.string._5_km_mile)
            popupMenu.menu.findItem(R.id.km_10).setTitle(R.string._10_km_mile)
        }

        popupMenu.setOnMenuItemClickListener { item ->
            paceObject.etID = "dist"
            binding.etDistance.requestFocus()
            var tmp = 0.0
            when (item.itemId) {
                R.id.km_3 -> tmp = 3.00
                R.id.km_5 -> tmp = 5.00
                R.id.km_10 -> tmp = 10.00
                R.id.km_21 -> tmp = 21.097
                R.id.km_42 -> tmp = 42.195
            }
//            if (Config.FLAG_MILE_TO_KM) tmp /= (Pace.MILEKM / Pace.KM)
            paceObject.distance = tmp
            binding.etDistance.setText("$tmp")
            true
        }
        popupMenu.show()
    }

    //
//    fun etPaceM() {
//        mKmToKmH()
//        if (flag == 2) time()
//        if (flag == 3) dist()
//        if (time == 0 && etCalcKm != 0.0) {
//            time()
//            flag = 2
//        }
//        if (time != 0 && etCalcKm == 0.0) {
//            dist()
//            flag = 3
//        }
//    }
//
//    fun etPaceS() {
//        mKmToKmH()
//
//
//        if (flag == 2) time()
//        if (flag == 3) dist()
//        if (time == 0 && etCalcKm != 0.0) {
//            time()
//            flag = 2
//        }
//        if (time != 0 && etCalcKm == 0.0) {
//            dist()
//            flag = 3
//        }
//    }
//
//    fun etKmh() {
//        kmHToMKm()
//        if (flag == 2) time()
//        if (flag == 3) dist()
//        if (time == 0 && etCalcKm != 0.0) {
//            time()
//            flag = 2
//        }
//        if (time != 0 && etCalcKm == 0.0) {
//            dist()
//            flag = 3
//        }
//    }
//
//    fun etTimeH() {
//        notNull()
//        if (flag == 1) speed()
//        if (flag == 3) dist()
//        if (etKm == 0.0 && etCalcKm != 0.0) {
//            speed()
//            flag = 1
//        }
//        if (etKm != 0.0 && etCalcKm == 0.0) {
//            dist()
//            flag = 3
//        }
//    }
//
//    fun etTimeM() {
//        notNull()
//        if (flag == 1) speed()
//        if (flag == 3) dist()
//        if (etKm == 0.0 && etCalcKm != 0.0) {
//            speed()
//            flag = 1
//        }
//        if (etKm != 0.0 && etCalcKm == 0.0) {
//            dist()
//            flag = 3
//        }
//    }
//
//    fun etTimeS() {
//        notNull()
//        if (flag == 1) speed()
//        if (flag == 3) dist()
//        if (etKm == 0.0 && etCalcKm != 0.0) {
//            speed()
//            flag = 1
//        }
//        if (etKm != 0.0 && etCalcKm == 0.0) {
//            dist()
//            flag = 3
//        }
//    }
//
//    fun etDist() {
//        notNull()
//        if (flag == 1) speed()
//        if (flag == 2) time()
//        if (etKm == 0.0 && time != 0) {
//            speed()
//            flag = 1
//        }
//        if (etKm != 0.0 && time == 0) {
//            time()
//            flag = 2
//        }
//
//    }
//
//
//    fun kmToMile() {
//        val res = (etKm / (Pace.MILEKM / Pace.KM))
//            .toBigDecimal()
//            .setScale(3, RoundingMode.HALF_UP)
//        binding.etSpeed.setText("$res")
//        val res2 = (etCalcKm / (Pace.MILEKM / Pace.KM))
//            .toBigDecimal()
//            .setScale(3, RoundingMode.HALF_UP).toDouble()
//        binding.etCalcKmh.setText("$res2")
//
//    }
//
//    fun mileToKm() {
//        val res = (etKm * (Pace.MILEKM / Pace.KM))
//            .toBigDecimal()
//            .setScale(3, RoundingMode.HALF_UP)
//        binding.etSpeed.setText("$res")
//        val res2 = (etCalcKm * (Pace.MILEKM / Pace.KM))
//            .toBigDecimal()
//            .setScale(3, RoundingMode.HALF_UP).toDouble()
//        binding.etCalcKmh.setText("$res2")
//    }
//
//    fun kmToMileSw() {
//        when (Config.FLAG_MILE_TO_KM) {
//            false -> {
//                mileToKm()
//            }
//            true -> {
//                kmToMile()
//            }
//        }
//    }
//
//
//
//    fun kmHToMKm() {
//        notNull()
//        if (etKm != 0.00) {
//            if (etKm < 1) {
//                binding.etSpeed.setText("1.00")
//                etKm = 1.0
//            }
//            val res = (etKm / Pace.MSEC)
//            val sumSec =
//                (Pace.unit / res)
//                    .toBigDecimal()
//                    .setScale(0, RoundingMode.HALF_UP)
//                    .toInt()
//            etMin = (sumSec / Pace.MINUTE)
//            etSec = sumSec - (etMin * Pace.MINUTE)
//            if (etMin == 0) binding.etPaceM.setText("00") else binding.etPaceM.setText(etMin.toString())
//            if (etSec < 10) binding.etPaceS.setText("0$etSec") else binding.etPaceS.setText(etSec.toString())
//        } else {
//            binding.etPaceM.setText("0")
//            binding.etPaceS.setText("00")
//        }
//        notNull()
//    }
//
//    fun dist() {
//        notNull()
//        if (etKm != 0.0) {
//            val pace = ((etMin * Pace.MINUTE) + etSec).toDouble()
//            val distSec =
//                (((etCalcDistHour * Pace.MINUTE) + etCalcDistMin) * Pace.MINUTE) + etCalcDistSec
//            val res = (distSec / pace).toBigDecimal()
//                .setScale(3, RoundingMode.HALF_UP)
//            binding.etCalcKmh.setText("$res")
//        }
//        notNull()
//    }
//
//    fun time() {
//        notNull()
//        if (etKm != 0.0) {
//            etKm = binding.etSpeed.text.toString().toDouble()
//            val sumSec =
//                (etCalcKm / etKm * Pace.HOUR).toBigDecimal().setScale(0, RoundingMode.UP)
//                    .toInt()
//            val hour = (etCalcKm / etKm).toInt()
//            val min = ((sumSec - (hour * Pace.HOUR)) / Pace.MINUTE).toInt()
//            val sec = (sumSec - (hour * Pace.HOUR) - (min * Pace.MINUTE)).toInt()
//            binding.etCalcDistH.setText("$hour")
//            binding.etCalcDistM.setText("$min")
//            binding.etCalcDistS.setText("$sec")
//            if (sumSec == 0 && !binding.etCalcKmh.isFocused) binding.etCalcKmh.setText("0.00")
//        }
//        notNull()
//    }
//
//    fun speed() {
//        notNull()
//        if (etCalcDistHour != 0 || etCalcDistMin != 0 || etCalcDistSec != 0) {
//            val timeCalc =
//                (((etCalcDistHour * Pace.MINUTE) + etCalcDistMin) * Pace.MINUTE) + etCalcDistSec
//            val distCalc = etCalcKm
//
//            if (timeCalc != 0 || distCalc != 0.0) {
//                val distSec =
//                    (((etCalcDistHour * Pace.MINUTE) + etCalcDistMin) * Pace.MINUTE) + etCalcDistSec.toDouble()
//                val distM = etCalcKm * Pace.KM
//                val mSec = distM / distSec
//                val res =
//                    ((mSec * Pace.HOUR) / Pace.KM).toBigDecimal()
//                        .setScale(2, RoundingMode.HALF_UP)
//                        .toDouble()
//                binding.etSpeed.setText("$res")
//                kmHToMKm()
//            }
//            notNull()
//        }
//    }
//
//    private fun notNull() {
//        etMin =
//            if (binding.etPaceM.text.toString().isEmpty()) 0 else binding.etPaceM.text.toString()
//                .toInt()
//        if (etMin == 0 && !binding.etPaceM.isFocused) binding.etPaceM.setText("0")
//
//        etSec =
//            if (binding.etPaceS.text.toString().isEmpty()) 0 else binding.etPaceS.text.toString()
//                .toInt()
//        if (etSec == 0 && !binding.etPaceS.isFocused) binding.etPaceS.setText("00")
//
//        etKm =
//            if (binding.etSpeed.text.toString()
//                    .isEmpty() || binding.etSpeed.text.toString() == "."
//            ) 0.0
//            else binding.etSpeed.text.toString().toDouble()
//        if (etKm == 0.0 && !binding.etSpeed.isFocused) binding.etSpeed.setText("0.00")
//
//        etCalcDistHour =
//            if (binding.etCalcDistH.text.toString()
//                    .isEmpty()
//            ) 0 else binding.etCalcDistH.text.toString().toInt()
//        if (etCalcDistHour == 0 && !binding.etCalcDistH.isFocused) binding.etCalcDistH.setText("0")
//
//        etCalcDistMin =
//            if (binding.etCalcDistM.text.toString()
//                    .isEmpty()
//            ) 0 else binding.etCalcDistM.text.toString().toInt()
//        if (etCalcDistMin < 10 && !binding.etCalcDistM.isFocused) binding.etCalcDistM.setText("0$etCalcDistMin")
//        if (etCalcDistMin == 0 && !binding.etCalcDistM.isFocused) binding.etCalcDistM.setText("00")
//
//        etCalcDistSec =
//            if (binding.etCalcDistS.text.toString()
//                    .isEmpty()
//            ) 0 else binding.etCalcDistS.text.toString().toInt()
//        if (etCalcDistSec < 10 && !binding.etCalcDistS.isFocused) binding.etCalcDistS.setText("0$etCalcDistSec")
//        if (etCalcDistSec == 0 && !binding.etCalcDistS.isFocused) binding.etCalcDistS.setText("00")
//
//        etCalcKm =
//            if (binding.etCalcKmh.text.toString()
//                    .isEmpty() || binding.etCalcKmh.text.toString() == "."
//            ) 0.0
//            else binding.etCalcKmh.text.toString().toDouble()
//        if (etCalcKm == 0.0 && !binding.etCalcKmh.isFocused) binding.etCalcKmh.setText("0.00")
//
//
//        time = etCalcDistHour + etCalcDistMin + etCalcDistSec
//        visible()
//    }
//
//    private fun visible() {
//        if (etKm != 0.0 && time != 0 && etCalcKm != 0.0) binding.btnSegment.visibility =
//            View.VISIBLE
//    }
//
//    private fun clear() {
//        if (!binding.etPaceM.isFocused && !binding.etPaceS.isFocused && !binding.etSpeed.isFocused && !binding.etCalcDistH.isFocused && !binding.etCalcDistM.isFocused && !binding.etCalcDistS.isFocused && !binding.etCalcKmh.isFocused) binding.etCalcKmh.requestFocus()
//        flag = 0
//        time = 0
//        etKm = 0.0
//        etCalcKm = 0.0
//        timeSec = 0
//
//        binding.etPaceM.setText("")
//        binding.etPaceS.setText("")
//        binding.etSpeed.setText("")
//        binding.etCalcDistH.setText("")
//        binding.etCalcDistM.setText("")
//        binding.etCalcDistS.setText("")
//        binding.etCalcKmh.setText("")
//        binding.btnSegment.visibility = View.GONE
//        when (etID) {
//            1 -> {
//                binding.etPaceM.requestFocus()
//                etID = 1
//                alertId = 1
//            }
//            2 -> {
//                binding.etPaceS.requestFocus()
//                etID = 2
//                alertId = 1
//            }
//            3 -> {
//                binding.etSpeed.requestFocus()
//                etID = 3
//                alertId = 1
//            }
//            4 -> {
//                binding.etCalcDistH.requestFocus()
//                etID = 4
//                alertId = 2
//            }
//            5 -> {
//                binding.etCalcDistM.requestFocus()
//                etID = 5
//                alertId = 2
//            }
//            6 -> {
//                binding.etCalcDistS.requestFocus()
//                etID = 6
//                alertId = 2
//            }
//            7 -> {
//                binding.etCalcKmh.requestFocus()
//                etID = 7
//                alertId = 3
//            }
//        }
//        notNull()
//    }
//
    private fun alertDialog() {
        val dialog = AlertDialog()
        dialog.setTargetFragment(this, REQUEST_WEIGHT)
        dialog.show(fragmentManager!!, "AlertDialog")
    }

    //
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            writeEt()

        }
    }
//
//
}


//    fun mKmToKmH() {
//        notNull()
//        if (etMin != 0 && etSec == 0 || etMin == 0 && etSec != 0 || etMin != 0 && etSec != 0) {
//            val allSecond = ((etMin * Pace.MINUTE) + etSec).toDouble()
//            val mSec = (Pace.unit / allSecond)
//            val res = (mSec * Pace.MSEC)
//                .toBigDecimal()
//                .setScale(2, RoundingMode.HALF_UP)
//            binding.etSpeed.setText("$res")
//        }
//        notNull()
//    }