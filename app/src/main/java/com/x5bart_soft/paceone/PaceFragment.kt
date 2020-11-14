package com.x5bart_soft.paceone


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.x5bart_soft.paceone.databinding.FragmentPaceBinding
import com.yandex.mobile.ads.AdRequest
import com.yandex.mobile.ads.AdSize


class PaceFragment : Fragment() {
    private lateinit var binding: FragmentPaceBinding
    private lateinit var preference: MyPreference
    private lateinit var function: PaceFunction
    private var hasCalculate = false
    var calculateDialog = ""

    private lateinit var paceObject: Pace

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        val locale = Locale.getDefault()
//        val configuration = Configuration()
//        configuration.locale = locale
//        activity!!.baseContext.resources.updateConfiguration(configuration, null)

        binding = FragmentPaceBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        preference = MyPreference(activity!!.applicationContext)
        function = PaceFunction()
        paceObject = Pace
        function.showVersion(binding.tvVersion, this.activity!!)

        if (paceObject.timeAll != 0 || paceObject.speed != 0.0 || paceObject.distance != 0.0) {
            paceObject.etID = "all"
            writeEt()
        }

        val BLOCK_ID = "adf-326819/1042468"
        binding.bannerView.blockId = BLOCK_ID
        binding.bannerView.adSize = AdSize.BANNER_320x50
        val adRequest = AdRequest.builder().build()
        binding.bannerView.loadAd(adRequest)

        viewBehavior()


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            writeEt()

        }
    }

    fun listeners() {

        when (paceObject.etID) {

            "paceM" -> {
                paceObject.tempM = function.readEt(binding.etPaceM).toInt()

                if (!paceObject.isInvalidInput) {
                    function.tempAll()
                    function.tempToSpeed()

                    if (calculateDialog == "speed") hasCalculate = false
                    if (paceObject.timeAll != 0
                        && paceObject.distance != 0.0
                        && hasCalculate
                    ) {
                        hasCalculate = false
                        calculateDialog = "speed"
                        alertDialog()

                    } else {

                        if (paceObject.timeAll == 0 &&
                            paceObject.distance != 0.0 ||
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
                        calculateDialog = "speed"
                        hasCalculate = false
                        writeEt()
                    }
                }
            }
            "paceS" -> {
                paceObject.tempS = function.readEt(binding.etPaceS).toInt()
                if (!paceObject.isInvalidInput) {
                    function.tempAll()
                    function.tempToSpeed()
                    if (calculateDialog == "speed") hasCalculate = false
                    if (paceObject.timeAll != 0 && paceObject.distance != 0.0 && hasCalculate) {
                        hasCalculate = false
                        calculateDialog = "speed"
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
                        calculateDialog = "speed"
                        hasCalculate = false
                        writeEt()
                    }
                }
            }
            "speed" -> {
                paceObject.speed = function.readEt(binding.etSpeed).toDouble()
                if (!paceObject.isInvalidInput) {
                    function.speedToTemp()
                    if (calculateDialog == "speed") hasCalculate = false
                    if (paceObject.timeAll != 0 && paceObject.distance != 0.0 && hasCalculate) {
                        hasCalculate = false
                        calculateDialog = "speed"
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
                        calculateDialog = "speed"
                        hasCalculate = false
                        writeEt()
                    }
                }
            }
            "timeH" -> {
                paceObject.timeH = function.readEt(binding.etTimeH).toInt()
                if (!paceObject.isInvalidInput) {
                    function.timeAll()
                    if (calculateDialog == "time") hasCalculate = false
                    if (paceObject.speed != 0.0 && paceObject.distance != 0.0 && hasCalculate) {
                        hasCalculate = false
                        calculateDialog = "time"
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
                        calculateDialog = "time"
                        hasCalculate = false
                        writeEt()
                    }
                }
            }
            "timeM" -> {
                paceObject.timeM = function.readEt(binding.etTimeM).toInt()
                if (!paceObject.isInvalidInput) {
                    function.timeAll()
                    if (calculateDialog == "time") hasCalculate = false
                    if (paceObject.speed != 0.0 && paceObject.distance != 0.0 && hasCalculate) {
                        hasCalculate = false
                        calculateDialog = "time"
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
                        calculateDialog = "time"
                        hasCalculate = false
                        writeEt()
                    }
                }
            }
            "timeS" -> {
                paceObject.timeS = function.readEt(binding.etTimeS).toInt()
                if (!paceObject.isInvalidInput) {
                    function.timeAll()
                    if (calculateDialog == "time") hasCalculate = false
                    if (paceObject.speed != 0.0 && paceObject.distance != 0.0 && hasCalculate) {
                        hasCalculate = false
                        calculateDialog = "time"
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
                        calculateDialog = "time"
                        hasCalculate = false
                        writeEt()
                    }
                }
            }
            "dist" -> {

                paceObject.distance = function.readEt(binding.etDistance).toDouble()
                if (!paceObject.isInvalidInput) {

                    if (calculateDialog == "dist") hasCalculate = false
                    if (paceObject.speed != 0.0 &&
                        paceObject.timeAll != 0 &&
                        hasCalculate
                    ) {

                        hasCalculate = false
                        calculateDialog = "dist"
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
                        calculateDialog = "dist"
                        hasCalculate = false
                        writeEt()
                    }
                }
            }
        }
    }

    private fun writeEt() {
        when (Pace.etID) {
            "all" -> {
                binding.etPaceM.setText(paceObject.tempM.toString())
                writePaceS()
                binding.etSpeed.setText(paceObject.speed.toString())
                binding.etTimeH.setText(paceObject.timeH.toString())
                writeTimeM()
                writeTimeS()
                binding.etDistance.setText(paceObject.distance.toString())

            }
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
            paceObject.distance = tmp
            binding.etDistance.setText("$tmp")
            true
        }
        popupMenu.show()
    }

    private fun alertDialog() {
        val dialog = AlertDialog()
        dialog.setTargetFragment(this, 0)
        dialog.show(fragmentManager!!, "AlertDialog")
    }

    private fun viewBehavior() {

        binding.etPaceM.setOnFocusChangeListener { _, _ ->
            paceObject.etID = "paceM"
            hasCalculate = true
        }
        binding.etPaceS.setOnFocusChangeListener { _, _ ->
            paceObject.etID = "paceS"
            hasCalculate = true
        }
        binding.etSpeed.setOnFocusChangeListener { _, _ ->
            paceObject.etID = "speed"
            hasCalculate = true
        }
        binding.etTimeH.setOnFocusChangeListener { _, _ ->
            paceObject.etID = "timeH"
            hasCalculate = true
        }
        binding.etTimeM.setOnFocusChangeListener { _, _ ->
            paceObject.etID = "timeM"
            hasCalculate = true
        }
        binding.etTimeS.setOnFocusChangeListener { _, _ ->
            paceObject.etID = "timeS"
            hasCalculate = true
        }
        binding.etDistance.setOnFocusChangeListener { _, _ ->
            paceObject.etID = "dist"
            hasCalculate = true
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
            view!!.clearFocus()
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
    }
}

//    fun saveSw() {
//        preference.setUnitSw(Config.FLAG_MILE_TO_KM)
//    }
//

//        when (Config.FLAG_MILE_TO_KM) {
//            false -> {
//                binding.swKmToMile.isChecked = false
//                binding.textView5.setTextColor(resources.getColor(R.color.textActive))
//                binding.textView51.setTextColor(resources.getColor(R.color.textNotActive))
//            }
//            true -> {
//                binding.swKmToMile.isChecked = true
//                binding.textView5.setTextColor(resources.getColor(R.color.textNotActive))
//                binding.textView51.setTextColor(resources.getColor(R.color.textActive))
//            }
//        }


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
