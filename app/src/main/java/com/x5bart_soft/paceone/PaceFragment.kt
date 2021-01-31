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
import com.x5bart_soft.paceone.data.Config
import com.x5bart_soft.paceone.data.Pace
import com.x5bart_soft.paceone.dialogs.AlertDialog
import com.x5bart_soft.paceone.model.PaceFunction
import com.x5bart_soft.paceone.utils.AdsUtils
import com.x5bart_soft.paceone.utils.MyPreference


class PaceFragment : Fragment() {
    private lateinit var binding: FragmentPaceBinding
    private lateinit var preference: MyPreference
    private lateinit var function: PaceFunction
    private lateinit var adsUtils: AdsUtils
    private var hasCalculate = false
    var calculateDialog = ""

    private lateinit var paceObject: Pace

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaceBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        preference = MyPreference()
        function = PaceFunction()
        paceObject = Pace
        adsUtils = AdsUtils()

        function.showVersion(binding.paceTvVersion, this.activity!!)

        adsUtils.showAds(binding.paceBannerView, adsUtils.blockIdPace)

        if (paceObject.timeAll != 0 || paceObject.speed != 0.0 || paceObject.distance != 0.0) {
            paceObject.etID = "all"
            writeEt()
        }

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
                paceObject.tempM = function.readEt(binding.paceEtPaceM).toInt()

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
                paceObject.tempS = function.readEt(binding.paceEtPaceS).toInt()
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
                paceObject.speed = function.readEt(binding.paceEtSpeed).toDouble()
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
                paceObject.timeH = function.readEt(binding.paceEtTimeH).toInt()
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
                paceObject.timeM = function.readEt(binding.paceEtTimeM).toInt()
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
                paceObject.timeS = function.readEt(binding.paceEtTimeS).toInt()
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

                paceObject.distance = function.readEt(binding.paceEtDistance).toDouble()
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
                binding.paceEtPaceM.setText(paceObject.tempM.toString())
                writePaceS()
                binding.paceEtSpeed.setText(paceObject.speed.toString())
                binding.paceEtTimeH.setText(paceObject.timeH.toString())
                writeTimeM()
                writeTimeS()
                binding.paceEtDistance.setText(paceObject.distance.toString())

            }
            "paceM" -> {
                writePaceS()
                binding.paceEtSpeed.setText(paceObject.speed.toString())
                binding.paceEtTimeH.setText(paceObject.timeH.toString())
                writeTimeM()
                writeTimeS()
                binding.paceEtDistance.setText(paceObject.distance.toString())
            }
            "paceS" -> {
                binding.paceEtPaceM.setText(paceObject.tempM.toString())
                binding.paceEtSpeed.setText(paceObject.speed.toString())
                binding.paceEtTimeH.setText(paceObject.timeH.toString())
                writeTimeM()
                writeTimeS()
                binding.paceEtDistance.setText(paceObject.distance.toString())
            }
            "speed" -> {
                binding.paceEtPaceM.setText(paceObject.tempM.toString())
                writePaceS()
                binding.paceEtTimeH.setText(paceObject.timeH.toString())
                writeTimeM()
                writeTimeS()
                binding.paceEtDistance.setText(paceObject.distance.toString())
            }
            "timeH" -> {
                binding.paceEtPaceM.setText(paceObject.tempM.toString())
                writePaceS()
                binding.paceEtSpeed.setText(paceObject.speed.toString())
                writeTimeM()
                writeTimeS()
                binding.paceEtDistance.setText(paceObject.distance.toString())
            }
            "timeM" -> {
                binding.paceEtPaceM.setText(paceObject.tempM.toString())
                writePaceS()
                binding.paceEtSpeed.setText(paceObject.speed.toString())
                binding.paceEtTimeH.setText(paceObject.timeH.toString())
                writeTimeS()
                binding.paceEtDistance.setText(paceObject.distance.toString())
            }
            "timeS" -> {
                binding.paceEtPaceM.setText(paceObject.tempM.toString())
                writePaceS()
                binding.paceEtSpeed.setText(paceObject.speed.toString())
                binding.paceEtTimeH.setText(paceObject.timeH.toString())
                writeTimeM()
                binding.paceEtDistance.setText(paceObject.distance.toString())
            }
            "dist" -> {
                binding.paceEtPaceM.setText(paceObject.tempM.toString())
                writePaceS()
                binding.paceEtSpeed.setText(paceObject.speed.toString())
                binding.paceEtTimeH.setText(paceObject.timeH.toString())
                writeTimeM()
                writeTimeS()
            }
        }
    }

    private fun writePaceS() {
        if (paceObject.tempS < 10) binding.paceEtPaceS.setText("0${paceObject.tempS}")
        else binding.paceEtPaceS.setText(paceObject.tempS.toString())
    }

    private fun writeTimeM() {
        if (paceObject.timeM < 10) binding.paceEtTimeM.setText("0${paceObject.timeM}")
        else binding.paceEtTimeM.setText(paceObject.timeM.toString())
    }

    private fun writeTimeS() {
        if (paceObject.timeS < 10) binding.paceEtTimeS.setText("0${paceObject.timeS}")
        else binding.paceEtTimeS.setText(paceObject.timeS.toString())
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
            binding.paceEtDistance.requestFocus()
            var tmp = 0.0
            when (item.itemId) {
                R.id.km_3 -> tmp = 3.00
                R.id.km_5 -> tmp = 5.00
                R.id.km_10 -> tmp = 10.00
                R.id.km_21 -> tmp = 21.097
                R.id.km_42 -> tmp = 42.195
            }
            paceObject.distance = tmp
            binding.paceEtDistance.setText("$tmp")
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

        binding.paceEtPaceM.setOnFocusChangeListener { _, _ ->
            paceObject.etID = "paceM"
            hasCalculate = true
        }
        binding.paceEtPaceS.setOnFocusChangeListener { _, _ ->
            paceObject.etID = "paceS"
            hasCalculate = true
        }
        binding.paceEtSpeed.setOnFocusChangeListener { _, _ ->
            paceObject.etID = "speed"
            hasCalculate = true
        }
        binding.paceEtTimeM.setOnFocusChangeListener { _, _ ->
            paceObject.etID = "timeH"
            hasCalculate = true
        }
        binding.paceEtTimeM.setOnFocusChangeListener { _, _ ->
            paceObject.etID = "timeM"
            hasCalculate = true
        }
        binding.paceEtTimeS.setOnFocusChangeListener { _, _ ->
            paceObject.etID = "timeS"
            hasCalculate = true
        }
        binding.paceEtDistance.setOnFocusChangeListener { _, _ ->
            paceObject.etID = "dist"
            hasCalculate = true
        }

        binding.paceEtPaceM.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (paceObject.etID == "paceM") listeners()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        binding.paceEtPaceS.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (paceObject.etID == "paceS") listeners()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        binding.paceEtSpeed.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (paceObject.etID == "speed") listeners()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        binding.paceEtTimeH.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (paceObject.etID == "timeH") listeners()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        binding.paceEtTimeM.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (paceObject.etID == "timeM") listeners()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        binding.paceEtTimeS.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (paceObject.etID == "timeS") listeners()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        binding.paceEtDistance.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (paceObject.etID == "dist") listeners()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        binding.paceEtPaceM.setOnClickListener { binding.paceEtPaceM.selectAll() }
        binding.paceEtPaceS.setOnClickListener { binding.paceEtPaceS.selectAll() }
        binding.paceEtSpeed.setOnClickListener { binding.paceEtSpeed.selectAll() }
        binding.paceEtTimeH.setOnClickListener { binding.paceEtTimeH.selectAll() }
        binding.paceEtTimeM.setOnClickListener { binding.paceEtTimeM.selectAll() }
        binding.paceEtTimeS.setOnClickListener { binding.paceEtTimeS.selectAll() }
        binding.paceEtDistance.setOnClickListener { binding.paceEtDistance.selectAll() }


        binding.paceBtnClear.setOnClickListener {
            view!!.clearFocus()
            function.clear()
            binding.paceEtPaceM.setText("")
            binding.paceEtPaceS.setText("")
            binding.paceEtSpeed.setText("")
            binding.paceEtTimeH.setText("")
            binding.paceEtTimeM.setText("")
            binding.paceEtTimeS.setText("")
            binding.paceEtDistance.setText("")

        }

        binding.paceIvDistanceMenu.setOnClickListener { showPopupKm(binding.paceIvDistanceMenu) }
    }
}

