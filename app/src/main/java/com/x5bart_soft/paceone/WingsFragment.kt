package com.x5bart_soft.paceone

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.x5bart_soft.paceone.databinding.FragmentWingsBinding
import com.x5bart_soft.paceone.data.Wings
import com.x5bart_soft.paceone.model.SplitFunction
import com.x5bart_soft.paceone.model.WingsFunction
import com.x5bart_soft.paceone.utils.AdsUtils
import com.x5bart_soft.paceone.utils.MyAnimate
import com.yandex.mobile.ads.AdRequest
import com.yandex.mobile.ads.AdSize


class WingsFragment : Fragment() {

    lateinit var binding: FragmentWingsBinding
    lateinit var function: WingsFunction
    lateinit var wingsObjact: Wings
    lateinit var animate: MyAnimate
    lateinit var splitFunction: SplitFunction
    lateinit var adsUtils: AdsUtils


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWingsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        function = WingsFunction()
        wingsObjact = Wings
        animate = MyAnimate()
        splitFunction = SplitFunction()
        adsUtils = AdsUtils()

        binding.wingsRvSplit.layoutManager = LinearLayoutManager(activity)
        if (wingsObjact.splits) binding.wingsCbSplit.isChecked = true

        wingsObjact.etId = "all"
        if (wingsObjact.distance > 0.0) writeEt()
        if (wingsObjact.splits) {
            binding.wingsCbSplit.isChecked
            visibleView()
            autoRv()
        }

        adsUtils.showAds(binding.wingsBannerView, adsUtils.blockIdWings)

        viewBehavior()

        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN

        animate.animate(binding.wingsIvCar)

    }

    private fun viewBehavior() {


        binding.wingsEtTimeH.setOnFocusChangeListener { _, _ -> wingsObjact.etId = "timeH" }
        binding.wingsEtTimeM.setOnFocusChangeListener { _, _ -> wingsObjact.etId = "timeM" }
        binding.wingsTimeS.setOnFocusChangeListener { _, _ -> wingsObjact.etId = "timeS" }
        binding.wingsEtDistance.setOnFocusChangeListener { _, _ -> wingsObjact.etId = "dist" }
        binding.wingsEtPaceM.setOnFocusChangeListener { _, _ -> wingsObjact.etId = "tempM" }
        binding.wingsEtPaceS.setOnFocusChangeListener { _, _ -> wingsObjact.etId = "tempS" }
        binding.wingsEtSplit.setOnFocusChangeListener { _, _ -> wingsObjact.etId = "split" }

        binding.wingsCbSplit.setOnClickListener {
            if (binding.wingsCbSplit.isChecked) {
                wingsObjact.splits = true
                visibleView()
                autoRv()

            } else {
                wingsObjact.splits = false
                hideView()
            }
        }



        binding.wingsEtTimeH.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (wingsObjact.etId == "timeH") listeners()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        binding.wingsEtTimeM.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (wingsObjact.etId == "timeM") listeners()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        binding.wingsTimeS.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (wingsObjact.etId == "timeS") listeners()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        binding.wingsEtDistance.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (wingsObjact.etId == "dist") listeners()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        binding.wingsEtPaceM.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (wingsObjact.etId == "tempM") listeners()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        binding.wingsEtPaceS.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (wingsObjact.etId == "tempS") listeners()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        binding.wingsEtSplit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (wingsObjact.etId == "split") {
                    wingsObjact.splitValue = function.readEt(binding.wingsEtSplit).toDouble()
                    autoRv()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        binding.wingsSbDistance.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                view!!.clearFocus()
                wingsObjact.etId = "pro"
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (wingsObjact.etId == "pro") {
                    wingsObjact.distance = progress.toDouble()
                    function.time()
                    writeEt()
                }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }

        })

        binding.wingsSbStrategy.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val pro = progress / 2.toDouble()
                binding.wingsEtStrategy.text = "$pro%"
                wingsObjact.splitStrategy = pro
                autoRv()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    private fun listeners() {
        val id = wingsObjact.etId
        val splits = wingsObjact.splits
        when (id) {
            "timeH" -> {
                wingsObjact.timeH = function.readEt(binding.wingsEtTimeH).toInt()
                if (!wingsObjact.isInvalidInput) {
                    function.timeAll()
                    function.timeSeg()
                    function.distance()
                    writeEt()
                    if (splits) autoRv()
                }
            }
            "timeM" -> {
                wingsObjact.timeM = function.readEt(binding.wingsEtTimeM).toInt()
                if (!wingsObjact.isInvalidInput) {
                    function.timeAll()
                    function.timeSeg()
                    function.distance()
                    writeEt()
                    if (splits) autoRv()
                }
            }
            "timeS" -> {
                wingsObjact.timeS = function.readEt(binding.wingsTimeS).toInt()
                if (!wingsObjact.isInvalidInput) {
                    function.timeAll()
                    function.timeSeg()
                    function.distance()
                    writeEt()
                    if (splits) autoRv()
                }
            }
            "dist" -> {
                wingsObjact.distance = function.readEt(binding.wingsEtDistance).toDouble()
                if (!wingsObjact.isInvalidInput) {
                    function.time()
                    writeEt()
                    if (splits) autoRv()
                }
            }
            "tempM" -> {
                wingsObjact.runnerTempM = function.readEt(binding.wingsEtPaceM).toInt()
                if (!wingsObjact.isInvalidInput) {
                    function.tempRunner()
                    function.tempToDistance()
                    writeEt()
                    if (splits) autoRv()
                }
            }
            "tempS" -> {
                wingsObjact.runnerTempS = function.readEt(binding.wingsEtPaceS).toInt()
                if (!wingsObjact.isInvalidInput) {
                    function.tempRunner()
                    function.tempToDistance()
                    writeEt()
                    if (splits) autoRv()
                }
            }
        }
    }

    private fun visibleView() {
        binding.wingsTvTitleSplit.visibility = View.VISIBLE
        binding.wingsEtSplit.visibility = View.VISIBLE
        binding.wingsTvSignSplit.visibility = View.VISIBLE
        binding.wingsTitleStrategy.visibility = View.VISIBLE
        binding.wingsIvInfoStrategy.visibility = View.VISIBLE
        binding.wingsEtStrategy.visibility = View.VISIBLE
        binding.wingsSbStrategy.visibility = View.VISIBLE
        binding.wingsLlHeaderRv.visibility = View.VISIBLE
        binding.wingsDivider4.visibility = View.VISIBLE
        binding.wingsRvSplit.visibility = View.VISIBLE
    }

    private fun hideView() {
        binding.wingsTvTitleSplit.visibility = View.GONE
        binding.wingsEtSplit.visibility = View.GONE
        binding.wingsTvSignSplit.visibility = View.GONE
        binding.wingsTitleStrategy.visibility = View.GONE
        binding.wingsIvInfoStrategy.visibility = View.GONE
        binding.wingsEtStrategy.visibility = View.GONE
        binding.wingsSbStrategy.visibility = View.GONE
        binding.wingsLlHeaderRv.visibility = View.GONE
        binding.wingsDivider4.visibility = View.GONE
        binding.wingsRvSplit.visibility = View.GONE
    }

    private fun writeEt() {

        when (wingsObjact.etId) {
            "all" -> {
                view!!.clearFocus()
                binding.wingsEtTimeH.setText(wingsObjact.timeH.toString())
                writeZero(binding.wingsEtTimeM, wingsObjact.timeM)
                writeZero(binding.wingsTimeS, wingsObjact.timeS)
                binding.wingsEtDistance.setText(wingsObjact.distance.toString())
                binding.wingsEtPaceM.setText(wingsObjact.runnerTempM.toString())
                writeZero(binding.wingsEtPaceS, wingsObjact.runnerTempS)
                writeProgress()

            }
            "timeH" -> {
                writeZero(binding.wingsEtTimeM, wingsObjact.timeM)
                writeZero(binding.wingsTimeS, wingsObjact.timeS)
                binding.wingsEtDistance.setText(wingsObjact.distance.toString())
                binding.wingsEtPaceM.setText(wingsObjact.runnerTempM.toString())
                writeZero(binding.wingsEtPaceS, wingsObjact.runnerTempS)
                writeProgress()
            }
            "timeM" -> {
                binding.wingsEtTimeH.setText(wingsObjact.timeH.toString())
                writeZero(binding.wingsTimeS, wingsObjact.timeS)
                binding.wingsEtDistance.setText(wingsObjact.distance.toString())
                binding.wingsEtPaceM.setText(wingsObjact.runnerTempM.toString())
                writeZero(binding.wingsEtPaceS, wingsObjact.runnerTempS)
                writeProgress()
            }
            "timeS" -> {
                binding.wingsEtTimeH.setText(wingsObjact.timeH.toString())
                writeZero(binding.wingsEtTimeM, wingsObjact.timeM)
                binding.wingsEtDistance.setText(wingsObjact.distance.toString())
                binding.wingsEtPaceM.setText(wingsObjact.runnerTempM.toString())
                writeZero(binding.wingsEtPaceS, wingsObjact.runnerTempS)
                writeProgress()
            }
            "dist" -> {
                binding.wingsEtTimeH.setText(wingsObjact.timeH.toString())
                writeZero(binding.wingsEtTimeM, wingsObjact.timeM)
                writeZero(binding.wingsTimeS, wingsObjact.timeS)
                binding.wingsEtPaceM.setText(wingsObjact.runnerTempM.toString())
                writeZero(binding.wingsEtPaceS, wingsObjact.runnerTempS)
                writeProgress()
            }
            "tempM" -> {
                binding.wingsEtTimeH.setText(wingsObjact.timeH.toString())
                writeZero(binding.wingsEtTimeM, wingsObjact.timeM)
                writeZero(binding.wingsTimeS, wingsObjact.timeS)
                binding.wingsEtDistance.setText(wingsObjact.distance.toString())
                writeZero(binding.wingsEtPaceS, wingsObjact.runnerTempS)
                writeProgress()
            }
            "tempS" -> {
                binding.wingsEtTimeH.setText(wingsObjact.timeH.toString())
                writeZero(binding.wingsEtTimeM, wingsObjact.timeM)
                writeZero(binding.wingsTimeS, wingsObjact.timeS)
                binding.wingsEtDistance.setText(wingsObjact.distance.toString())
                binding.wingsEtPaceM.setText(wingsObjact.runnerTempM.toString())
                writeProgress()
            }
            "pro" -> {
                view!!.clearFocus()
                binding.wingsEtTimeH.setText(wingsObjact.timeH.toString())
                writeZero(binding.wingsEtTimeM, wingsObjact.timeM)
                writeZero(binding.wingsTimeS, wingsObjact.timeS)
                binding.wingsEtDistance.setText(wingsObjact.distance.toString())
                binding.wingsEtPaceM.setText(wingsObjact.runnerTempM.toString())
                writeZero(binding.wingsEtPaceS, wingsObjact.runnerTempS)
                writeProgress()
            }
            "split" -> {
                view!!.clearFocus()
                binding.wingsEtTimeH.setText(wingsObjact.timeH.toString())
                writeZero(binding.wingsEtTimeM, wingsObjact.timeM)
                writeZero(binding.wingsTimeS, wingsObjact.timeS)
                binding.wingsEtDistance.setText(wingsObjact.distance.toString())
                binding.wingsEtPaceM.setText(wingsObjact.runnerTempM.toString())
                writeZero(binding.wingsEtPaceS, wingsObjact.runnerTempS)
                writeProgress()
            }
        }
    }

    private fun writeZero(editText: EditText, value: Int) {
        if (value < 10) editText.setText("0$value")
        else editText.setText(value.toString())
    }

    private fun writeProgress() {
        binding.wingsTvCarSpeed.text = "${wingsObjact.carSpeed} ${this.getString(R.string.km_h)}"
        binding.wingsSbDistance.progress = wingsObjact.distance.toInt()
    }

    private fun autoRv() {
        val timeAll = wingsObjact.time
        val distance = wingsObjact.distance
        val splitValue = wingsObjact.splitValue
        val splitStrategy = wingsObjact.splitStrategy
        if (binding.wingsRvSplit.size>0) splitFunction.clearRv(binding.wingsRvSplit)
        wingsObjact.splitsIsEmpty = splitFunction.createSplitList(
            binding.wingsRvSplit,
            timeAll,
            distance,
            splitValue,
            splitStrategy
        )
        writeEt()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }


}