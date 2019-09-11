package com.x5bart_soft.paceone



import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import kotlinx.android.synthetic.main.fragment1.*

class Fragment1 : Fragment() {
    private val hour = 60 //60 min
    private val km = 1000 //1000 m
    private val sec = 3.600 // 3600sec/hours
    var res = 0.0
    var etMin = 0
    var etSec = 0
    var etKm = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        etPaceS.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_GO -> {
                    mKmToKmH()
                    true
                }
                else -> false
            }
        }

        etSpeed.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_GO -> {
                    kmHToMKm()
                    true
                }
                else -> false
            }
        }

        btnToSpeed.setOnClickListener {
            mKmToKmH()
        }
        btnToPace.setOnClickListener {
            kmHToMKm()
        }
    }

    private fun mKmToKmH() {
        if (etPaceM.text.toString().isEmpty()) {
            etMin = 0
            etPaceM.setText("0")
        } else
            etMin = etPaceM.text.toString().toInt()

        if (etPaceS.text.toString().isEmpty()) {
            etSec = 0
            etPaceS.setText("00")
        } else etSec = etPaceS.text.toString().toInt()

        if (etPaceS.text.toString().toInt() < 10) etPaceS.setText("0$etSec")

        if (etMin == 0 && etSec == 0) etSpeed.setText("0.00")
        else {
            val second = ((etMin * hour) + etSec).toDouble()
            res = (km / second) * sec
            etSpeed.setText(res.toString())
        }

    }

    private fun kmHToMKm() {
        if (etSpeed.text.toString().isEmpty() || etSpeed.text.toString().toDouble() == 0.00) {
            etSpeed.setText("0.00")
        } else {
            etKm = etSpeed.text.toString().toDouble()
            res = etKm / sec
            val min = (km / res / hour).toInt()
            val sec = ((km / res) - (min * hour)).toInt()

            etPaceM.setText(min.toString())
            if (sec < 10) etPaceS.setText("0$sec")
            else etPaceS.setText(sec.toString())
        }
    }
}

