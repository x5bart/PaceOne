package com.x5bart_soft.paceone

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.yandex.mobile.ads.AdRequest
import com.yandex.mobile.ads.AdSize
import com.yandex.mobile.ads.AdView


class MainActivity : AppCompatActivity() {
    private val BLOCK_ID = "adf-326819/1042468"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.include)
        setSupportActionBar(toolbar)

        val frg = Fragment1()
        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.frgCont, frg)
        ft.commit()

        val mAdView = findViewById<AdView>(R.id.banner_view)
        mAdView.blockId = BLOCK_ID
        mAdView.adSize = AdSize.BANNER_320x50

        val adRequest = AdRequest.builder().build()
        mAdView.loadAd(adRequest)
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
