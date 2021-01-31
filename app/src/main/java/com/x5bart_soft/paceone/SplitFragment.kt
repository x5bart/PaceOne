package com.x5bart_soft.paceone


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.widget.PopupMenu
import android.widget.SeekBar
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.x5bart_soft.paceone.databinding.FragmentSplitBinding
import com.x5bart_soft.paceone.data.Pace
import com.x5bart_soft.paceone.model.SplitFunction
import com.x5bart_soft.paceone.dialogs.HelpDialog
import com.x5bart_soft.paceone.split.SplitAdapter
import com.x5bart_soft.paceone.utils.AdsUtils
import com.x5bart_soft.paceone.utils.MyPreference

class SplitFragment : Fragment(), SeekBar.OnSeekBarChangeListener {
    private lateinit var preference: MyPreference
    private lateinit var paceObject: Pace
    private lateinit var function: SplitFunction
    private lateinit var binding: FragmentSplitBinding
    private lateinit var adsUtils: AdsUtils

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplitBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        paceObject = Pace
        function = SplitFunction()
        adsUtils = AdsUtils()
        preference = MyPreference()

        binding.splitsRvSplits.layoutManager = LinearLayoutManager(activity)

        adsUtils.showAds(binding.splitsBannerView, adsUtils.blockIdSplits)

        paceObject.etSplitId = "all"
        if (paceObject.timeAll != 0 || paceObject.distance != 0.0) {
            writeEt()
            autoRv()
        }

        binding.splitsSbStrategy.setOnSeekBarChangeListener(this)
        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN

        viewBehavior()

    }

    private fun viewBehavior() {

        binding.splitsEtTimeH.setOnFocusChangeListener { _, _ -> paceObject.etSplitId = "timeH" }
        binding.splitsEtTimeM.setOnFocusChangeListener { _, _ -> paceObject.etSplitId = "timeM" }
        binding.splitsTimeS.setOnFocusChangeListener { _, _ -> paceObject.etSplitId = "timeS" }
        binding.splitsEtDistance.setOnFocusChangeListener { _, _ -> paceObject.etSplitId = "dist" }
        binding.splitsEtSplit.setOnFocusChangeListener { _, _ -> paceObject.etSplitId = "split" }

        binding.splitsEtTimeH.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (paceObject.etSplitId == "timeH") {
                    paceObject.timeH = function.readEt(binding.splitsEtTimeH).toInt()
                    function.timeAll()
                    autoRv()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        binding.splitsEtTimeM.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (paceObject.etSplitId == "timeM") {
                    paceObject.timeM = function.readEt(binding.splitsEtTimeM).toInt()
                    function.timeAll()
                    autoRv()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        binding.splitsTimeS.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(p0: Editable?) {
                if (paceObject.etSplitId == "timeS") {
                    paceObject.timeS = function.readEt(binding.splitsTimeS).toInt()
                    function.timeAll()
                    autoRv()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        binding.splitsEtDistance.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (paceObject.etSplitId == "dist") {
                    paceObject.distance = function.readEt(binding.splitsEtDistance).toDouble()
                    function.speedToTemp()
                    autoRv()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        binding.splitsEtSplit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (paceObject.etSplitId == "split") {
                    paceObject.splitValue = function.readEt(binding.splitsEtSplit).toDouble()
                    autoRv()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        binding.splitsEtTimeH.setOnClickListener { binding.splitsEtTimeH.selectAll() }
        binding.splitsEtTimeM.setOnClickListener { binding.splitsEtTimeM.selectAll() }
        binding.splitsTimeS.setOnClickListener { binding.splitsTimeS.selectAll() }
        binding.splitsEtDistance.setOnClickListener { binding.splitsEtDistance.selectAll() }
        binding.splitsEtSplit.setOnClickListener { binding.splitsEtSplit.selectAll() }
        binding.splitsIvInfoStrategy.setOnClickListener {
            val dialog = HelpDialog(
                R.string.negative_and_positive_splits,
                R.string.splitFactory,
                R.string.null_my
            )
            dialog.show(fragmentManager!!, "info")
        }
        binding.splitsIvInfoStrategy.setOnClickListener { showPopupKm(binding.splitsIvInfoStrategy) }
    }

    private fun writeEt() {
        when (paceObject.etSplitId) {

            "all" -> {
                binding.splitsEtTimeH.setText(paceObject.timeH.toString())
                writeTimeM()
                writeTimeS()
                binding.splitsEtDistance.setText(paceObject.distance.toString())
                binding.splitsEtSplit.setText(paceObject.splitValue.toString())
            }
            "timeH" -> {
                writeTimeM()
                writeTimeS()
                binding.splitsEtDistance.setText(paceObject.distance.toString())
                binding.splitsEtSplit.setText(paceObject.splitValue.toString())
            }
            "timeM" -> {
                binding.splitsEtTimeH.setText(paceObject.timeH.toString())
                writeTimeS()
                binding.splitsEtDistance.setText(paceObject.distance.toString())
                binding.splitsEtSplit.setText(paceObject.splitValue.toString())
            }
            "timeS" -> {
                binding.splitsEtTimeH.setText(paceObject.timeH.toString())
                writeTimeM()
                binding.splitsEtDistance.setText(paceObject.distance.toString())
                binding.splitsEtSplit.setText(paceObject.splitValue.toString())

            }
            "dist" -> {
                binding.splitsEtTimeH.setText(paceObject.timeH.toString())
                writeTimeM()
                writeTimeS()
                binding.splitsEtSplit.setText(paceObject.splitValue.toString())
            }
            "split" -> {
                binding.splitsEtTimeH.setText(paceObject.timeH.toString())
                writeTimeM()
                writeTimeS()
                binding.splitsEtDistance.setText(paceObject.distance.toString())

            }
        }
    }

    private fun writeTimeM() {
        if (paceObject.timeM < 10) binding.splitsEtTimeM.setText("0${paceObject.timeM}")
        else binding.splitsEtTimeM.setText(paceObject.timeM.toString())
    }

    private fun writeTimeS() {
        if (paceObject.timeS < 10) binding.splitsTimeS.setText("0${paceObject.timeS}")
        else binding.splitsTimeS.setText(paceObject.timeS.toString())
    }

    private fun showPopupKm(v: View) {
        val popupMenu = PopupMenu(activity, v, Gravity.NO_GRAVITY)
        popupMenu.inflate(R.menu.popup_menu_km)

        popupMenu.setOnMenuItemClickListener { item ->
            binding.splitsEtDistance.requestFocus()
            var tmp = 0.0
            when (item.itemId) {
                R.id.km_3 -> tmp = 3.0
                R.id.km_5 -> tmp = 5.0
                R.id.km_10 -> tmp = 10.0
                R.id.km_21 -> tmp = 21.097
                R.id.km_42 -> tmp = 42.195
            }
//            if (Config.FLAG_MILE_TO_KM) tmp /= (Pace.MILEKM / Pace.KM)
            binding.splitsEtDistance.setText("$tmp")
            true
        }
        popupMenu.show()
    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        val pro = progress / 2.toDouble()
        binding.splitsEtStrategy.text = "$pro%"
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
        if (binding.splitsRvSplits.size>0) function.clearRv(binding.splitsRvSplits)
        paceObject.splitIsEmpty = function.createSplitList(
            binding.splitsRvSplits,
            timeAll,
            distance,
            splitValue,
            splitStrategy
        )
        writeEt()
    }

    override fun onDestroy() {
        Log.d(TAG,"onDestroy()")
        super.onDestroy()
    }

}
