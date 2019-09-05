package com.x5bart_soft.paceone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG = "btnlog"
    val tmp = 60
    var res = 0
    var num1 = 0
    var num2 = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnToSpeed.setOnClickListener {
            Log.d(TAG, "btnToSpeed clicked")
            num1 = Integer.parseInt(etPace.text.toString())
            res = num1 + tmp
            Log.d(TAG,"num1 = $num1, res = $res")
            etSpeed.text(res.toString())
        }
        btnToPace.setOnClickListener { Log.d(TAG, "btnToPace clicked") }
    }
}
