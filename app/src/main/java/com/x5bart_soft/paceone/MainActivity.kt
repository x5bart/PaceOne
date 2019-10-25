package com.x5bart_soft.paceone

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


class MainActivity : AppCompatActivity() {
    companion object {
        val KM_3 = 1
        val KM_5 = 2
        val KM_10 = 3
        val KM_21 = 4
        val KM_42 = 5
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
