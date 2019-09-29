package com.x5bart_soft.paceone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val frag1 = Fragment1()
        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.frgCont, frag1)
        ft.commit()

    }


}
