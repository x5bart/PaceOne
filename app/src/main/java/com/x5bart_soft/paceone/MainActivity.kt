package com.x5bart_soft.paceone

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.ColorSpace
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG = "btnlog"
    val h1 = 60 //60 min
    var res = 0.0
    val km = 1000 //1000 m
    val coef = 3.600 // 3600sec/hours
    var num1 = 0
    var num2 = 0
    var num3 = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnToSpeed.setOnClickListener {
            if (etPaceM.text.toString().isEmpty()) {
                num1 = 0
                etPaceM.setText("0")
            } else
                num1 = etPaceM.text.toString().toInt()

            if (etPaceS.text.toString().isEmpty()) {
                num2 = 0
                etPaceS.setText("00")
            } else
                num2 = etPaceS.text.toString().toInt()

            if (num1 == 0 && num2 == 0) showMyDialog()
            else {
                val minutes = ((num1 * h1) + num2).toDouble()
                res = (km / minutes) * coef
                etSpeed.setText(res.toString())
            }
        }
        btnToPace.setOnClickListener {
            if (etSpeed.text.toString().isEmpty() || etSpeed.text.toString().toDouble() == 0.0) {
                showMyDialog()
                etSpeed.setText("0.0")
            } else {
                num3 = etSpeed.text.toString().toDouble()
                res = num3 / coef
                val min = (km / res / h1).toInt()
                val sec = (((km / res) - (min * h1)) * 100).toInt()

                etPaceM.setText(min.toString())
                etPaceS.setText(sec.toString())
            }
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
}

