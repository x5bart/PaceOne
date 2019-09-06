package com.x5bart_soft.paceone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG = "btnlog"
    val h1 = 60 //60 min
    var res = 0.0
    val km = 1000 //1000 m
    val coef = 3.600 // 3600sec/hours

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnToSpeed.setOnClickListener {

            val num1 = etPaceM.text.toString().toInt()
            val num2 = etPaceS.text.toString().toInt()
            val minutes = ((num1 * h1) + num2).toDouble()
            res = (km / minutes) * coef

            etSpeed.setText(res.toString())
        }

        btnToPace.setOnClickListener {

            val num1 = etSpeed.text.toString().toDouble()
            res = num1 / coef
            val min = (km / res/h1).toInt()
            val sec = (((km / res) - (min * h1)) * 100).toInt()
            Log.d(TAG, "res = $res, min = $min sec = $sec")

            etPaceM.setText(min.toString())
            etPaceS.setText(sec.toString())
        }
    }
}
 