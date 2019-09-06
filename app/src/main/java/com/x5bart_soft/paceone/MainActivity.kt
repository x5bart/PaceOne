package com.x5bart_soft.paceone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
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

            if (etPaceM.text.toString().length == 0)
                num1 = 0
            else
                num1 = etPaceM.text.toString().toInt()
            if (etPaceS.text.toString().length == 0)
                num2 = 0
            else
                num2 = etPaceS.text.toString().toInt()

            val minutes = ((num1 * h1) + num2).toDouble()
            res = (km / minutes) * coef

            etSpeed.setText(res.toString())
        }

        btnToPace.setOnClickListener {
            if (etSpeed.text.toString().length == 0)
                num3 = 0.0
            else
                num3 = etSpeed.text.toString().toDouble()
            res = num3 / coef
            val min = (km / res / h1).toInt()
            val sec = (((km / res) - (min * h1)) * 100).toInt()

            etPaceM.setText(min.toString())
            etPaceS.setText(sec.toString())
        }
    }
}
