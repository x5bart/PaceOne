package com.x5bart_soft.paceone

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val h1 = 60 //60 min
    private val km = 1000 //1000 m
    private val sec = 3.600 // 3600sec/hours
    var res = 0.0
    var num1 = 0
    var num2 = 0
    var num3 = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<EditText>(R.id.etPaceS).setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_GO -> {
                    mKmToKmH()
                    true
                }
                else -> false
            }
        }

        findViewById<EditText>(R.id.etSpeed).setOnEditorActionListener { v, actionId, event ->
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


    fun showMyDialog() {
        val dialog: AlertDialog
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Please enter values for conversion.")
        dialog = builder.create()
        dialog.show()
    }

    fun mKmToKmH() {
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

        if (num1 == 0 && num2 == 0) showMyDialog()
        else {
            val minutes = ((num1 * h1) + num2).toDouble()
            res = (km / minutes) * sec
            etSpeed.setText(res.toString())
        }
    }

    fun kmHToMKm(){
        if (etSpeed.text.toString().isEmpty() || etSpeed.text.toString().toDouble() == 0.0) {
            showMyDialog()
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
}

