package com.x5bart_soft.paceone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG = "btnlog"
    val tmp = 60
    var res = 0.0
    var num1 = 0
    var num2 = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnToSpeed.setOnClickListener {

            num1 = Integer.parseInt(etPaceM.text.toString())
            num2 = Integer.parseInt(etPaceS.text.toString())

            val minutes = ((num1 * 60) + num2).toDouble()
            res = (1000 / minutes) * 3.6

            Log.d(TAG, "num1 = $num1, num2 = $num2 res = $res ms = $minutes")
            etSpeed.setText(res.toString())
        }
        btnToPace.setOnClickListener { Log.d(TAG, "btnToPace clicked") }
    }

    fun conv() {

    }
}
 