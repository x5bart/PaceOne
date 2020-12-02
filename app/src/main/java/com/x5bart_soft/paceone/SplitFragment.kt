package com.x5bart_soft.paceone


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.PopupMenu
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.x5bart_soft.paceone.databinding.FragmentSplitBinding
import com.x5bart_soft.paceone.data.Pace
import com.x5bart_soft.paceone.model.SplitFunction
import com.x5bart_soft.paceone.split.NegativSplitDialog
import com.yandex.mobile.ads.AdRequest
import com.yandex.mobile.ads.AdSize

class SplitFragment : Fragment(), SeekBar.OnSeekBarChangeListener {
    private lateinit var preference: MyPreference
    private lateinit var paceObject: Pace
    private lateinit var function: SplitFunction
    private lateinit var binding: FragmentSplitBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val locale = Locale.getDefault()
//        val configuration = Configuration()
//        configuration.locale = locale
//        activity!!.baseContext.resources.updateConfiguration(configuration, null)

        binding = FragmentSplitBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        paceObject = Pace
        function = SplitFunction()

        binding.rvSplits.layoutManager = LinearLayoutManager(activity)

        paceObject.etSplitId = "all"
        if (paceObject.timeAll != 0 || paceObject.distance != 0.0) {
            writeEt()
//            function.createSplitList(binding.recyclerView)
            autoRv()
        }

        binding.sbSplitNegativ.setOnSeekBarChangeListener(this)
        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN

        preference = MyPreference(activity!!.applicationContext)

        viewBehavior()

//        when (Config.FLAG_MILE_TO_KM) {
//            false -> {
//                binding.textView77.text = resources.getString(R.string.km)
//                binding.textView555.text = resources.getString(R.string.km)
//                binding.textView11.text = resources.getString(R.string.km_2)
//            }
//            true -> {
//                binding.textView77.text = resources.getString(R.string.mile)
//                binding.textView555.text = resources.getString(R.string.mile)
//                binding.textView11.text = resources.getString(R.string.mile_3)
//            }
//        }


//        val BLOCK_ID = "adf-326819/1043357"
        val BLOCK_ID = "adf-326819/1146536"
        binding.bannerViewSplit.blockId = BLOCK_ID
        binding.bannerViewSplit.adSize = AdSize.BANNER_320x50
        val adRequest = AdRequest.builder().build()
        binding.bannerViewSplit.loadAd(adRequest)


    }

    private fun viewBehavior() {

        binding.etTimeH.setOnFocusChangeListener { _, _ -> paceObject.etSplitId = "timeH" }
        binding.etTimeM.setOnFocusChangeListener { _, _ -> paceObject.etSplitId = "timeM" }
        binding.etTimeS.setOnFocusChangeListener { _, _ -> paceObject.etSplitId = "timeS" }
        binding.etDistance.setOnFocusChangeListener { _, _ -> paceObject.etSplitId = "dist" }
        binding.etSplit.setOnFocusChangeListener { _, _ -> paceObject.etSplitId = "split" }

        binding.etTimeH.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (paceObject.etSplitId == "timeH") {
                    paceObject.timeH = function.readEt(binding.etTimeH).toInt()
                    function.timeAll()
                    autoRv()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        binding.etTimeM.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (paceObject.etSplitId == "timeM") {
                    paceObject.timeM = function.readEt(binding.etTimeM).toInt()
                    function.timeAll()
                    autoRv()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        binding.etTimeS.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(p0: Editable?) {
                if (paceObject.etSplitId == "timeS") {
                    paceObject.timeS = function.readEt(binding.etTimeS).toInt()
                    function.timeAll()
                    autoRv()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        binding.etDistance.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (paceObject.etSplitId == "dist") {
                    paceObject.distance = function.readEt(binding.etDistance).toDouble()
                    function.speedToTemp()
                    autoRv()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        binding.etSplit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (paceObject.etSplitId == "split") {
                    paceObject.splitValue = function.readEt(binding.etSplit).toDouble()
                    autoRv()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        binding.etTimeH.setOnClickListener { binding.etTimeH.selectAll() }
        binding.etTimeM.setOnClickListener { binding.etTimeM.selectAll() }
        binding.etTimeS.setOnClickListener { binding.etTimeS.selectAll() }
        binding.etDistance.setOnClickListener { binding.etDistance.selectAll() }
        binding.etSplit.setOnClickListener { binding.etSplit.selectAll() }
        binding.ivNegativeSplit.setOnClickListener {
            val dialog = NegativSplitDialog()
            dialog.show(fragmentManager!!, "info")
        }
        binding.ivInfoKm.setOnClickListener { showPopupKm(binding.ivInfoKm) }
    }

    private fun writeEt() {
        when (paceObject.etSplitId) {

            "all" -> {
                binding.etTimeH.setText(paceObject.timeH.toString())
                writeTimeM()
                writeTimeS()
                binding.etDistance.setText(paceObject.distance.toString())
                binding.etSplit.setText(paceObject.splitValue.toString())
            }
            "timeH" -> {
                writeTimeM()
                writeTimeS()
                binding.etDistance.setText(paceObject.distance.toString())
                binding.etSplit.setText(paceObject.splitValue.toString())
            }
            "timeM" -> {
                binding.etTimeH.setText(paceObject.timeH.toString())
                writeTimeS()
                binding.etDistance.setText(paceObject.distance.toString())
                binding.etSplit.setText(paceObject.splitValue.toString())
            }
            "timeS" -> {
                binding.etTimeH.setText(paceObject.timeH.toString())
                writeTimeM()
                binding.etDistance.setText(paceObject.distance.toString())
                binding.etSplit.setText(paceObject.splitValue.toString())

            }
            "dist" -> {
                binding.etTimeH.setText(paceObject.timeH.toString())
                writeTimeM()
                writeTimeS()
                binding.etSplit.setText(paceObject.splitValue.toString())
            }
            "split" -> {
                binding.etTimeH.setText(paceObject.timeH.toString())
                writeTimeM()
                writeTimeS()
                binding.etDistance.setText(paceObject.distance.toString())

            }
        }
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
//        if (Config.FLAG_MILE_TO_KM) {
//            popupMenu.menu.findItem(R.id.km_3).setTitle(R.string._3_km_mile)
//            popupMenu.menu.findItem(R.id.km_5).setTitle(R.string._5_km_mile)
//            popupMenu.menu.findItem(R.id.km_10).setTitle(R.string._10_km_mile)
//        }

        popupMenu.setOnMenuItemClickListener { item ->
            binding.etDistance.requestFocus()
            var tmp = 0.0
            when (item.itemId) {
                R.id.km_3 -> tmp = 3.0
                R.id.km_5 -> tmp = 5.0
                R.id.km_10 -> tmp = 10.0
                R.id.km_21 -> tmp = 21.097
                R.id.km_42 -> tmp = 42.195
            }
//            if (Config.FLAG_MILE_TO_KM) tmp /= (Pace.MILEKM / Pace.KM)
            binding.etDistance.setText("$tmp")
            true
        }
        popupMenu.show()
    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        val pro = progress / 2.toDouble()
        binding.tvSlitStrategy.text = "$pro%"
        paceObject.splitStrategy = pro
        autoRv()

    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {
    }



    fun autoRv() {
        val timeAll = paceObject.timeAll
        val distance = paceObject.distance
        val splitValue = paceObject.splitValue
        val splitStrategy = paceObject.splitStrategy
        if (!paceObject.splitIsEmpty) function.clearRv(binding.rvSplits)
        paceObject.splitIsEmpty = function.createSplitList(
            binding.rvSplits,
            timeAll,
            distance,
            splitValue,
            splitStrategy
        )
        writeEt()
    }

    override fun onDestroy() {
        paceObject.splitIsEmpty = true
        super.onDestroy()
    }

}

//    fun kmToMile() {
//        val res = (dist / (Pace.MILEKM / Pace.KM))
//            .toBigDecimal()
//            .setScale(3, RoundingMode.HALF_UP)
//        etSegId = 4
//        binding.etKm.setText("$res")
//    }
//
//    fun mileToKm() {
//        val res = (dist * (Pace.MILEKM / Pace.KM))
//            .toBigDecimal()
//            .setScale(3, RoundingMode.HALF_UP)
//        etSegId = 4
//        binding.etKm.setText("$res")
//    }

