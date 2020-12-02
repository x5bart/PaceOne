package com.x5bart_soft.paceone

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.app.ActivityCompat.invalidateOptionsMenu
import androidx.fragment.app.Fragment
import com.x5bart_soft.paceone.databinding.FragmentSettingsBinding
import com.x5bart_soft.paceone.data.Config
import com.x5bart_soft.paceone.utils.ContextUtils
import com.yandex.mobile.ads.AdRequest
import com.yandex.mobile.ads.AdSize
import java.util.*


class SettingsFragment : Fragment() {
    private lateinit var preference: MyPreference

    lateinit var binding: FragmentSettingsBinding
    lateinit var utils: ContextUtils
    lateinit var config: Config

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        val locale = Locale.getDefault()
//        val configuration = Configuration()
//        configuration.locale = locale
//        activity!!.baseContext.resources.updateConfiguration(configuration, null)

        binding = FragmentSettingsBinding.inflate(layoutInflater)
        return binding.root
//        inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(
        view: View, savedInstanceState: Bundle
        ?
    ) {
        super.onViewCreated(view, savedInstanceState)

        preference = MyPreference(activity!!.applicationContext)
        utils = ContextUtils(this.activity)
        config = Config

        val BLOCK_ID = "adf-326819/1146538"
        binding.bannerViewWings.blockId = BLOCK_ID
        binding.bannerViewWings.adSize = AdSize.BANNER_320x50
        val adRequest = AdRequest.builder().build()
        binding.bannerViewWings.loadAd(adRequest)

        val lang = Locale.getDefault().toString()
        when (lang) {
            "en" -> binding.tvLanguage2.setText(R.string.english)
            "ru" -> binding.tvLanguage2.setText(R.string.russian)
        }
        when (Config.FLAG_MILE_TO_KM) {
            false -> binding.tvDistance2.setText(R.string.km)
            true -> binding.tvDistance2.setText(R.string.mile_2)
        }


        binding.tvLanguage2.setOnClickListener {
            showPopupLang(binding.tvLanguage2)
        }
        binding.ivLanguage.setOnClickListener {
            showPopupLang(binding.ivLanguage)
        }
        translate()
        binding.tvDistance2.setOnClickListener {
            showPopupDist(binding.tvDistance2)
        }
        binding.ivDistanse.setOnClickListener {
            showPopupDist(binding.tvDistance2)
        }

    }


    fun showPopupLang(v: View) {
        val popupMenu = PopupMenu(activity, v, Gravity.NO_GRAVITY)
        popupMenu.inflate(R.menu.popup_menu_language)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.en -> {
                    MainActivity.LANG_ID = 1
                    utils.updateLocale(this.activity!!, Locale("en"))
                    config.language = "en"
                    val intent = Intent(context, MainActivity::class.java)
                    context!!.startActivity(intent)

//                    setApplicationLanguage("en")
//                    translate()
                }
                R.id.ru -> {
                    MainActivity.LANG_ID = 2
                    utils.updateLocale(this.activity!!, Locale("ru"))
                    config.language = "ru"
                    val intent = Intent(context, MainActivity::class.java)
                    context!!.startActivity(intent)

//                    setApplicationLanguage("ru")
//                    translate()
                }
            }
            true
        }
        popupMenu.show()
    }

    fun showPopupDist(v: View) {
        val popupMenu = PopupMenu(activity, v, Gravity.NO_GRAVITY)
        popupMenu.inflate(R.menu.popup_menu_dist)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.km -> {
                    Config.FLAG_MILE_TO_KM = false
                    binding.tvDistance2.setText(R.string.km)
                    saveSw()
                }
                R.id.mile -> {
                    Config.FLAG_MILE_TO_KM = true
                    binding.tvDistance2.setText(R.string.mile_2)
                    saveSw()
                }
            }
            true
        }
        popupMenu.show()
    }

    fun saveSw() {
        preference.setUnitSw(Config.FLAG_MILE_TO_KM)
    }

    fun setApplicationLanguage(language: String) {
        when (language) {
            "en" -> binding.tvLanguage2.setText(R.string.english)
            "ru" -> binding.tvLanguage2.setText(R.string.russian)
        }
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration = Configuration()
        configuration.locale = locale
        activity!!.baseContext.resources.updateConfiguration(configuration, null)
        invalidateOptionsMenu(activity)


        val sPref = activity!!.getPreferences(MODE_PRIVATE)
        val ed = sPref.edit()
        ed.putString(MainActivity.APP_LANGUAGE, language)
        ed.apply()


    }

    fun translate() {
        binding.tvSystem.setText(R.string.system)
        binding.tvLanguage.setText(R.string.language)
        binding.tvDistance.setText(R.string.distanceSetting)
        when (Config.FLAG_MILE_TO_KM) {
            false -> binding.tvDistance2.setText(R.string.km)
            true -> binding.tvDistance2.setText(R.string.mile_2)
        }

    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        translate()
        super.onConfigurationChanged(newConfig)
    }




}
