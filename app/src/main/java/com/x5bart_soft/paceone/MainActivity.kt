package com.x5bart_soft.paceone

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.yandex.mobile.ads.AdEventListener
import com.yandex.mobile.ads.AdRequest
import com.yandex.mobile.ads.AdSize
import com.yandex.mobile.ads.AdView






class MainActivity : AppCompatActivity() {
    private val BLOCK_ID = "adf-326819/1042468"
    private var mAdView: AdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.include)
        setSupportActionBar(toolbar)

        val frag1 = Fragment1()
        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.frgCont, frag1)
        ft.commit()

        mAdView = findViewById(R.id.banner_view)
        mAdView!!.blockId = BLOCK_ID
        mAdView!!.adSize = AdSize.BANNER_320x50

        val parameters = HashMap<String, String>()
        parameters["adf_ownerid"] = "example"
        parameters["adf_p1"] = "example"
        parameters["adf_p2"] = "example"
        parameters["adf_pfc"] = "example"
        parameters["adf_pfb"] = "example"
        parameters["adf_plp"] = "example"
        parameters["adf_pli"] = "example"
        parameters["adf_pop"] = "example"
        parameters["adf_pt"] = "example"
        parameters["adf_pd"] = "example"
        parameters["adf_pw"] = "example"
        parameters["adf_pv"] = "example"
        parameters["adf_prr"] = "example"
        parameters["adf_pdw"] = "example"
        parameters["adf_pdh"] = "example"
        parameters["adf_puid1"] = "example"

        val adRequest = AdRequest.builder().withParameters(parameters).build()

        mAdView!!.loadAd(adRequest)
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
