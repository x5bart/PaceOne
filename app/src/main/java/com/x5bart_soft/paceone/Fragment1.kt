package com.x5bart_soft.paceone


import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment1.*

/**
 * A simple [Fragment] subclass.
 */
class Fragment1 : Fragment() {
    private val h1 = 60 //60 min
    private val km = 1000 //1000 m
    private val sec = 3.600 // 3600sec/hours
    var res = 0.0
    var num1 = 0
    var num2 = 0
    var num3 = 0.0





//    private fun showMyDialog() {
//        val dialog: AlertDialog
//        val builder = AlertDialog.Builder()
//        builder.setTitle("Error")
//        builder.setMessage("Please enter values for conversion.")
//        dialog = builder.create()
//        dialog.show()
//    }

    private fun mKmToKmH() {
        if (etPaceM.text.toString().isEmpty()) {
            num1 = 0
            etPaceM.setText("0")
        } else
            num1 = etPaceM.text.toString().toInt()

        if (etPaceS.text.toString().isEmpty()) {
            num2 = 0
            etPaceS.setText("00")
        } else num2 = etPaceS.text.toString().toInt()
        if (etPaceS.text.toString().toInt() < 10) etPaceS.setText("0$num2")

        if (num1 == 0 && num2 == 0)
        //showMyDialog()
        else {
            val minutes = ((num1 * h1) + num2).toDouble()
            res = (km / minutes) * sec
            etSpeed.setText(res.toString())
        }
    }

    private fun kmHToMKm() {
        if (etSpeed.text.toString().isEmpty() || etSpeed.text.toString().toDouble() == 0.0) {
//            showMyDialog()
            etSpeed.setText("0.0")
        } else {
            num3 = etSpeed.text.toString().toDouble()
            res = num3 / sec
            val min = (km / res / h1).toInt()
            val sec = ((km / res) - (min * h1)).toInt()

            etPaceM.setText(min.toString())
            if (sec < 10) etPaceS.setText("0$sec")
            else etPaceS.setText(sec.toString())
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        etPaceS.setOnEditorActionListener{
                _, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_GO -> {
                    mKmToKmH()
                    true
                }
                else -> false
            }
        }

        etSpeed.setOnEditorActionListener{
                _, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_GO -> {
                    kmHToMKm()
                    true
                }
                else -> false
            }
        }

        btnToSpeed.setOnClickListener{
            mKmToKmH()
        }
        btnToPace.setOnClickListener{
            kmHToMKm()
        }
    }
}

