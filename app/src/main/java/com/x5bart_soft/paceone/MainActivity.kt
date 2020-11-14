package com.x5bart_soft.paceone

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.x5bart_soft.paceone.databinding.ActivityMainBinding
import java.util.*

const val BLOCK_ID = "adf-326819/1077574"
const val TAG = "Mylog"


class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private lateinit var preference: MyPreference
    private lateinit var binding: ActivityMainBinding
    private var btnId = "pace"


    companion object {

        var locale = Locale("en")


        var LANG_ID = 0
        val APP_LANGUAGE = "mysettings"
        val APP_MILE_TO_KM = "mile"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        preference = MyPreference(this)

        changeColorBtn()

//        loaded()

//        val locale = Locale.getDefault()
//        val configuration = Configuration()
//        configuration.locale = locale
//        baseContext.resources.updateConfiguration(configuration, null)

        val frg = PaceFragment()
        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.frgCont, frg)
        ft.commit()
        banBehavior()

    }

    private fun banBehavior() {
        binding.btnPace.setOnClickListener {
            btnId = "pace"
            changeColorBtn()
            val frg = PaceFragment()
            val ft = supportFragmentManager.beginTransaction()
            ft.add(R.id.frgCont, frg)
            ft.commit()
        }
        binding.btnSplit.setOnClickListener {
            btnId = "split"
            changeColorBtn()
            val frg = SplitFragment()
            val ft = supportFragmentManager.beginTransaction()
            ft.add(R.id.frgCont, frg)
            ft.commit()
        }
        binding.btnWings.setOnClickListener {
            btnId = "wings"
            changeColorBtn()
            val frg = WingsFragment()
            val ft = supportFragmentManager.beginTransaction()
            ft.add(R.id.frgCont, frg)
            ft.commit()
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun changeColorBtn() {
        val activeColor = this.resources.getColor(R.color.colorPrimary)
        val inActiveColor = this.resources.getColor(R.color.textActive)
        when (btnId) {
            "pace" -> {
                binding.btnPace.backgroundTintList = ColorStateList.valueOf(activeColor)
                binding.btnSplit.backgroundTintList = ColorStateList.valueOf(inActiveColor)
                binding.btnPerson.backgroundTintList = ColorStateList.valueOf(inActiveColor)
                binding.btnSetting.backgroundTintList = ColorStateList.valueOf(inActiveColor)
            }
            "split" -> {
                binding.btnPace.backgroundTintList = ColorStateList.valueOf(inActiveColor)
                binding.btnSplit.backgroundTintList = ColorStateList.valueOf(activeColor)
                binding.btnPerson.backgroundTintList = ColorStateList.valueOf(inActiveColor)
                binding.btnSetting.backgroundTintList = ColorStateList.valueOf(inActiveColor)
            }
            "wings" -> {
                binding.btnPace.backgroundTintList = ColorStateList.valueOf(inActiveColor)
                binding.btnSplit.backgroundTintList = ColorStateList.valueOf(inActiveColor)
                binding.btnPerson.backgroundTintList = ColorStateList.valueOf(inActiveColor)
                binding.btnSetting.backgroundTintList = ColorStateList.valueOf(inActiveColor)
            }
            "person" -> {
                binding.btnPace.backgroundTintList = ColorStateList.valueOf(inActiveColor)
                binding.btnSplit.backgroundTintList = ColorStateList.valueOf(inActiveColor)
                binding.btnPerson.backgroundTintList = ColorStateList.valueOf(activeColor)
                binding.btnSetting.backgroundTintList = ColorStateList.valueOf(inActiveColor)
            }
            "setting" -> {
                binding.btnPace.backgroundTintList = ColorStateList.valueOf(inActiveColor)
                binding.btnSplit.backgroundTintList = ColorStateList.valueOf(inActiveColor)
                binding.btnPerson.backgroundTintList = ColorStateList.valueOf(inActiveColor)
                binding.btnSetting.backgroundTintList = ColorStateList.valueOf(activeColor)
            }
        }
    }

//    fun loaded() {
//        val sPref = getPreferences(Context.MODE_PRIVATE)
//        val language = sPref.getString(APP_LANGUAGE, null)
//        if (language != null) {
//            locale = Locale(language)
//            Locale.setDefault(locale)
//            val configuration = Configuration()
//            configuration.locale = locale
//            baseContext.resources.updateConfiguration(configuration, null)
//            ActivityCompat.invalidateOptionsMenu(this)
//        }
//        Config.FLAG_MILE_TO_KM = preference.getUnitSw()
//    }

//    private fun onInterstitialLoaded() {
//
//        val mInterstitialAd = InterstitialAd(this)
//        mInterstitialAd.blockId = BLOCK_ID
//        val adRequest = AdRequest.Builder().build()
//        mInterstitialAd.interstitialEventListener =
//            object : InterstitialEventListener.SimpleInterstitialEventListener() {
//                override fun onInterstitialLoaded() {
//                    mInterstitialAd.show()
//                    super.onInterstitialLoaded()
//                }
//            }
//        mInterstitialAd.loadAd(adRequest);
//    }


}
