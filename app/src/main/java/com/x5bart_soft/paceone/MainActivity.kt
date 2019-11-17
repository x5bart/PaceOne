package com.x5bart_soft.paceone

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import java.util.*


class MainActivity : AppCompatActivity() {
    val LOG = "MLOG"

    companion object {

        var locale = Locale("en")
        var FLAG_MILE_TO_KM = 1
        const val MINUTE = 60 //60 sec
        const val HOUR = 3600.00 // 3600 sec
        const val KM = 1000 //1000 m
        const val MILEKM = 1609.344 // 1609.344 m
        const val MILEF = 1760// 1760 fut
        var unit = if (FLAG_MILE_TO_KM == 1) KM else MILEF
        var MSEC = (HOUR / unit)// 3600sec/km
        var LANG_ID = 0
        val APP_LANGUAGE = "mysettings"
        val APP_MILE_TO_KM = "mile"
        val LOG = "MLOG"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        loaded()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val locale = Locale.getDefault()
        val configuration = Configuration()
        configuration.locale = locale
        baseContext.resources.updateConfiguration(configuration, null)


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
            R.id.setting -> {
                val frg = Settings()
                val ft = supportFragmentManager.beginTransaction()
                ft.addToBackStack("frag")
                ft.replace(R.id.frgCont, frg, "frg")
                ft.commit()
            }
            R.id.about -> {
                val dialog = AboutDialog()
                dialog.show(supportFragmentManager, "About")
            }
            R.id.exit -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun loaded() {
        val sPref = getPreferences(Context.MODE_PRIVATE)
        val language = sPref.getString(APP_LANGUAGE, null)
        if (language != null) {
            locale = Locale(language)
            Locale.setDefault(locale)
            val configuration = Configuration()
            configuration.locale = locale
            baseContext.resources.updateConfiguration(configuration, null)
            ActivityCompat.invalidateOptionsMenu(this)
        }
        FLAG_MILE_TO_KM = sPref.getInt(APP_MILE_TO_KM, 0)
    }


}
