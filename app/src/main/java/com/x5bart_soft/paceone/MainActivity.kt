package com.x5bart_soft.paceone

import android.annotation.SuppressLint
import android.content.Context
import android.content.ContextWrapper
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.analytics.FirebaseAnalytics
import com.x5bart_soft.paceone.databinding.ActivityMainBinding
import com.x5bart_soft.paceone.data.Config
import com.x5bart_soft.paceone.utils.ContextUtils
import com.x5bart_soft.paceone.utils.MyPreference
import java.util.*


const val TAG = "Mylog"


class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private lateinit var preference: MyPreference
    private lateinit var binding: ActivityMainBinding
    private lateinit var config: Config
    private var btnId = ""
    private var changeLanguage = false
    private var language = ""


    companion object {
        val APP_LANGUAGE = "mysettings"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        preference = MyPreference(this)
        config = Config

        start()
        btnBehavior()

    }

    override fun attachBaseContext(newBase: Context?) {
        language = Config.language
        if (language.length == 5) language = language.substring(3)
        val localeToSwitchTo = Locale(language)
        val localeUpdatedContext: ContextWrapper =
            ContextUtils(this).updateLocale(newBase!!, localeToSwitchTo)!!
        super.attachBaseContext(localeUpdatedContext)
    }

    private fun start() {
        changeLanguage = config.changeLanguage
        if (changeLanguage) getFragment("setting", SettingsFragment())
        else getFragment("pace", PaceFragment())
        changeLanguage = false
        config.changeLanguage = changeLanguage
    }

    private fun btnBehavior() {
        changeColorBtn()
        binding.btnPace.setOnClickListener { getFragment("pace", PaceFragment()) }
        binding.btnSplit.setOnClickListener { getFragment("split", SplitFragment()) }
        binding.btnWings.setOnClickListener { getFragment("wings", WingsFragment()) }
        binding.btnSetting.setOnClickListener { getFragment("setting", SettingsFragment()) }
    }

    private fun getFragment(buttonId: String, fragment: Fragment) {
        if (btnId != buttonId) {
            btnId = buttonId
            changeColorBtn()
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frgCont, fragment)
            fragmentTransaction.commit()
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
                binding.btnWings.backgroundTintList = ColorStateList.valueOf(inActiveColor)
                binding.btnPerson.backgroundTintList = ColorStateList.valueOf(inActiveColor)
                binding.btnSetting.backgroundTintList = ColorStateList.valueOf(inActiveColor)
            }
            "split" -> {
                binding.btnPace.backgroundTintList = ColorStateList.valueOf(inActiveColor)
                binding.btnSplit.backgroundTintList = ColorStateList.valueOf(activeColor)
                binding.btnWings.backgroundTintList = ColorStateList.valueOf(inActiveColor)
                binding.btnPerson.backgroundTintList = ColorStateList.valueOf(inActiveColor)
                binding.btnSetting.backgroundTintList = ColorStateList.valueOf(inActiveColor)
            }
            "wings" -> {
                binding.btnPace.backgroundTintList = ColorStateList.valueOf(inActiveColor)
                binding.btnSplit.backgroundTintList = ColorStateList.valueOf(inActiveColor)
                binding.btnWings.backgroundTintList = ColorStateList.valueOf(activeColor)
                binding.btnPerson.backgroundTintList = ColorStateList.valueOf(inActiveColor)
                binding.btnSetting.backgroundTintList = ColorStateList.valueOf(inActiveColor)
            }
            "person" -> {
                binding.btnPace.backgroundTintList = ColorStateList.valueOf(inActiveColor)
                binding.btnSplit.backgroundTintList = ColorStateList.valueOf(inActiveColor)
                binding.btnWings.backgroundTintList = ColorStateList.valueOf(inActiveColor)
                binding.btnPerson.backgroundTintList = ColorStateList.valueOf(activeColor)
                binding.btnSetting.backgroundTintList = ColorStateList.valueOf(inActiveColor)
            }
            "setting" -> {
                binding.btnPace.backgroundTintList = ColorStateList.valueOf(inActiveColor)
                binding.btnSplit.backgroundTintList = ColorStateList.valueOf(inActiveColor)
                binding.btnWings.backgroundTintList = ColorStateList.valueOf(inActiveColor)
                binding.btnPerson.backgroundTintList = ColorStateList.valueOf(inActiveColor)
                binding.btnSetting.backgroundTintList = ColorStateList.valueOf(activeColor)
            }
        }
    }

}
