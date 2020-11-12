package com.x5bart_soft.paceone

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.firebase.analytics.FirebaseAnalytics
import com.yandex.mobile.ads.AdRequest
import com.yandex.mobile.ads.InterstitialAd
import com.yandex.mobile.ads.InterstitialEventListener
import java.util.*

const val BLOCK_ID = "adf-326819/1077574"
const val TAG = "Mylog"


class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private lateinit var preference: MyPreference



    companion object {

        var locale = Locale("en")



        var LANG_ID = 0
        val APP_LANGUAGE = "mysettings"
        val APP_MILE_TO_KM = "mile"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        preference = MyPreference(this)

        loaded()

        val locale = Locale.getDefault()
        val configuration = Configuration()
        configuration.locale = locale
        baseContext.resources.updateConfiguration(configuration, null)

        val frg = PaceFragment()
        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.frgCont, frg)
        ft.commit()
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
        Config.FLAG_MILE_TO_KM = preference.getUnitSw()
    }

    private fun onInterstitialLoaded() {

        val mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.blockId = BLOCK_ID
        val adRequest = AdRequest.Builder().build()
        mInterstitialAd.interstitialEventListener =
            object : InterstitialEventListener.SimpleInterstitialEventListener() {
                override fun onInterstitialLoaded() {
                    mInterstitialAd.show()
                    super.onInterstitialLoaded()
                }
            }
        mInterstitialAd.loadAd(adRequest);
    }

}
