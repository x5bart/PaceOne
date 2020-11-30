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
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.x5bart_soft.paceone.databinding.FragmentWingsBinding
import com.yandex.mobile.ads.AdRequest
import com.yandex.mobile.ads.AdSize


class WingsFragment : Fragment() {

    lateinit var binding: FragmentWingsBinding
    lateinit var function: WingsFunction
    lateinit var wingsObjact: Wings
    lateinit var animate: MyAnimate
    lateinit var splitFunction: SplitFunction


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

        binding.rvWings.layoutManager = LinearLayoutManager(activity)
        if (wingsObjact.splits) binding.cbSplits.isChecked = true

        wingsObjact.etId = "all"
        if (wingsObjact.distance > 0.0) writeEt()
        if (wingsObjact.splits) {
            binding.cbSplits.isChecked
            visibleView()
            autoRv()
        }

        val BLOCK_ID = "adf-326819/1042468"
        val banner = binding.bannerViewWings
        banner.blockId = BLOCK_ID
        banner.adSize = AdSize.BANNER_320x50
        val adRequest = AdRequest.builder().build()
        banner.loadAd(adRequest)

        viewBehavior()


        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN

        animate.animate(binding.ivCar)

        binding.tvTitleDistance.setOnClickListener {
            fragmentManager!!.beginTransaction().remove(this).commit()
        }

    }

    private fun viewBehavior() {



        binding.etTimeH.setOnFocusChangeListener { _, _ -> wingsObjact.etId = "timeH" }
        binding.etTimeM.setOnFocusChangeListener { _, _ -> wingsObjact.etId = "timeM" }
        binding.etTimeS.setOnFocusChangeListener { _, _ -> wingsObjact.etId = "timeS" }
        binding.etDistance.setOnFocusChangeListener { _, _ -> wingsObjact.etId = "dist" }
        binding.etTempM.setOnFocusChangeListener { _, _ -> wingsObjact.etId = "tempM" }
        binding.etTempS.setOnFocusChangeListener { _, _ -> wingsObjact.etId = "tempS" }
        binding.etSplit.setOnFocusChangeListener { _, _ -> wingsObjact.etId = "split" }

        binding.cbSplits.setOnClickListener {
            if (binding.cbSplits.isChecked) {
                wingsObjact.splits = true
                visibleView()
                autoRv()

            } else {
                wingsObjact.splits = false
                hideView()
            }
        }



        binding.etTimeH.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (wingsObjact.etId == "timeH") listeners()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        binding.etTimeM.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (wingsObjact.etId == "timeM") listeners()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        binding.etTimeS.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (wingsObjact.etId == "timeS") listeners()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        binding.etDistance.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (wingsObjact.etId == "dist") listeners()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        binding.etTempM.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (wingsObjact.etId == "tempM") listeners()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        binding.etTempS.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (wingsObjact.etId == "tempS") listeners()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        binding.etSplit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (wingsObjact.etId == "split") {
                    wingsObjact.splitValue = function.readEt(binding.etSplit).toDouble()
                    autoRv()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        binding.sbWingsDistance.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {

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

        binding.sbWingsStrategy.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val pro = progress / 2.toDouble()
                binding.tvSlitStrategy.text = "$pro%"
                wingsObjact.splitStrategy = pro
                autoRv()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

  private  fun listeners() {
        val id = wingsObjact.etId
        val splits = wingsObjact.splits
        when (id) {
            "timeH" -> {
                wingsObjact.timeH = function.readEt(binding.etTimeH).toInt()
                if (!wingsObjact.isInvalidInput) {
                    function.timeAll()
                    function.timeSeg()
                    function.distance()
                    writeEt()
                    if (splits) autoRv()
                }
            }
            "timeM" -> {
                wingsObjact.timeM = function.readEt(binding.etTimeM).toInt()
                if (!wingsObjact.isInvalidInput) {
                    function.timeAll()
                    function.timeSeg()
                    function.distance()
                    writeEt()
                    if (splits) autoRv()
                }
            }
            "timeS" -> {
                wingsObjact.timeS = function.readEt(binding.etTimeS).toInt()
                if (!wingsObjact.isInvalidInput) {
                    function.timeAll()
                    function.timeSeg()
                    function.distance()
                    writeEt()
                    if (splits) autoRv()
                }
            }
            "dist" -> {
                wingsObjact.distance = function.readEt(binding.etDistance).toDouble()
                if (!wingsObjact.isInvalidInput) {
                    function.time()
                    writeEt()
                    if (splits) autoRv()
                }
            }
            "tempM" -> {
                wingsObjact.runnerTempM = function.readEt(binding.etTempM).toInt()
                if (!wingsObjact.isInvalidInput) {
                    function.tempRunner()
                    function.tempToDistance()
                    writeEt()
                    if (splits) autoRv()
                }
            }
            "tempS" -> {
                wingsObjact.runnerTempS = function.readEt(binding.etTempS).toInt()
                if (!wingsObjact.isInvalidInput) {
                    function.tempRunner()
                    function.tempToDistance()
                    writeEt()
                    if (splits) autoRv()
                }
            }
        }
    }

    private fun visibleView(){
        binding.tvTitleSplit.visibility = View.VISIBLE
        binding.etSplit.visibility = View.VISIBLE
        binding.tvSignSplit.visibility = View.VISIBLE
        binding.tvTitleStrategy.visibility = View.VISIBLE
        binding.ivNegativeSplit.visibility = View.VISIBLE
        binding.tvSlitStrategy.visibility = View.VISIBLE
        binding.sbWingsStrategy.visibility = View.VISIBLE
        binding.headerSplits.visibility = View.VISIBLE
        binding.divider6.visibility = View.VISIBLE
        binding.rvWings.visibility = View.VISIBLE
    }

    private fun hideView(){
        binding.tvTitleSplit.visibility = View.GONE
        binding.etSplit.visibility = View.GONE
        binding.tvSignSplit.visibility = View.GONE
        binding.tvTitleStrategy.visibility = View.GONE
        binding.ivNegativeSplit.visibility = View.GONE
        binding.tvSlitStrategy.visibility = View.GONE
        binding.sbWingsStrategy.visibility = View.GONE
        binding.headerSplits.visibility = View.GONE
        binding.divider6.visibility = View.GONE
        binding.rvWings.visibility = View.GONE
    }
    private fun writeEt() {

        when (wingsObjact.etId) {
            "all" -> {
                view!!.clearFocus()
                binding.etTimeH.setText(wingsObjact.timeH.toString())
                writeZero(binding.etTimeM, wingsObjact.timeM)
                writeZero(binding.etTimeS, wingsObjact.timeS)
                binding.etDistance.setText(wingsObjact.distance.toString())
                binding.etTempM.setText(wingsObjact.runnerTempM.toString())
                writeZero(binding.etTempS, wingsObjact.runnerTempS)
                writeProgress()

            }
            "timeH" -> {
                writeZero(binding.etTimeM, wingsObjact.timeM)
                writeZero(binding.etTimeS, wingsObjact.timeS)
                binding.etDistance.setText(wingsObjact.distance.toString())
                binding.etTempM.setText(wingsObjact.runnerTempM.toString())
                writeZero(binding.etTempS, wingsObjact.runnerTempS)
                writeProgress()
            }
            "timeM" -> {
                binding.etTimeH.setText(wingsObjact.timeH.toString())
                writeZero(binding.etTimeS, wingsObjact.timeS)
                binding.etDistance.setText(wingsObjact.distance.toString())
                binding.etTempM.setText(wingsObjact.runnerTempM.toString())
                writeZero(binding.etTempS, wingsObjact.runnerTempS)
                writeProgress()
            }
            "timeS" -> {
                binding.etTimeH.setText(wingsObjact.timeH.toString())
                writeZero(binding.etTimeM, wingsObjact.timeM)
                binding.etDistance.setText(wingsObjact.distance.toString())
                binding.etTempM.setText(wingsObjact.runnerTempM.toString())
                writeZero(binding.etTempS, wingsObjact.runnerTempS)
                writeProgress()
            }
            "dist" -> {
                binding.etTimeH.setText(wingsObjact.timeH.toString())
                writeZero(binding.etTimeM, wingsObjact.timeM)
                writeZero(binding.etTimeS, wingsObjact.timeS)
                binding.etTempM.setText(wingsObjact.runnerTempM.toString())
                writeZero(binding.etTempS, wingsObjact.runnerTempS)
                writeProgress()
            }
            "tempM" -> {
                binding.etTimeH.setText(wingsObjact.timeH.toString())
                writeZero(binding.etTimeM, wingsObjact.timeM)
                writeZero(binding.etTimeS, wingsObjact.timeS)
                binding.etDistance.setText(wingsObjact.distance.toString())
                writeZero(binding.etTempS, wingsObjact.runnerTempS)
                writeProgress()
            }
            "tempS" -> {
                binding.etTimeH.setText(wingsObjact.timeH.toString())
                writeZero(binding.etTimeM, wingsObjact.timeM)
                writeZero(binding.etTimeS, wingsObjact.timeS)
                binding.etDistance.setText(wingsObjact.distance.toString())
                binding.etTempM.setText(wingsObjact.runnerTempM.toString())
                writeProgress()
            }
            "pro" -> {
                view!!.clearFocus()
                binding.etTimeH.setText(wingsObjact.timeH.toString())
                writeZero(binding.etTimeM, wingsObjact.timeM)
                writeZero(binding.etTimeS, wingsObjact.timeS)
                binding.etDistance.setText(wingsObjact.distance.toString())
                binding.etTempM.setText(wingsObjact.runnerTempM.toString())
                writeZero(binding.etTempS, wingsObjact.runnerTempS)
                writeProgress()
            }
            "split" -> {
                view!!.clearFocus()
                binding.etTimeH.setText(wingsObjact.timeH.toString())
                writeZero(binding.etTimeM, wingsObjact.timeM)
                writeZero(binding.etTimeS, wingsObjact.timeS)
                binding.etDistance.setText(wingsObjact.distance.toString())
                binding.etTempM.setText(wingsObjact.runnerTempM.toString())
                writeZero(binding.etTempS, wingsObjact.runnerTempS)
                writeProgress()
            }
        }
    }

    private fun writeZero(editText: EditText, value: Int) {
        if (value < 10) editText.setText("0$value")
        else editText.setText(value.toString())
    }

    private fun writeProgress() {
        binding.tvCarSpeed.text = "${wingsObjact.carSpeed} ${this.getString(R.string.km_h)}"
        binding.sbWingsDistance.progress = wingsObjact.distance.toInt()
    }

    private fun autoRv() {
        val timeAll = wingsObjact.time
        val distance = wingsObjact.distance
        val splitValue = wingsObjact.splitValue
        val splitStrategy = wingsObjact.splitStrategy
        val splitIsEmpty = wingsObjact.splitsIsEmpty
        if (!splitIsEmpty) splitFunction.clearRv(binding.rvWings)
        wingsObjact.splitsIsEmpty = splitFunction.createSplitList(
            binding.rvWings,
            timeAll,
            distance,
            splitValue,
            splitStrategy
        )
        writeEt()
    }

    override fun onDestroy() {
        Log.d(TAG,"onDestroy")
        wingsObjact.splitsIsEmpty = true
        super.onDestroy()
    }



}