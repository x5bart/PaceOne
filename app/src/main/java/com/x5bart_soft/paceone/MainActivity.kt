package com.x5bart_soft.paceone

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


class MainActivity : AppCompatActivity() {


    companion object {
        var flagMileKm = 1
        const val MINUTE = 60 //60 sec
        const val HOUR = 3600.00 // 3600 sec
        const val KM = 1000 //1000 m
        const val MILEKM = 1609.344 // 1609.344 m
        const val MILEF =1760// 1760 fut
        var unit = if (flagMileKm==1)KM else MILEF
        var MSEC = (HOUR / unit)// 3600sec/km

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.include)
        setSupportActionBar(toolbar)

        val frg = Fragment1()
        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.frgCont, frg)
        ft.commit()


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about -> {
                val dialog = AboutDialog()
                dialog.show(supportFragmentManager, "About")
            }
            R.id.exit -> finish()
        }
        return super.onOptionsItemSelected(item)
    }


}
