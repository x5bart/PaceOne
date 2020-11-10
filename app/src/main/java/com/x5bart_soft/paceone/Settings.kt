package com.x5bart_soft.paceone

import android.content.Context.MODE_PRIVATE
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
import java.util.*


class Settings : Fragment() {

    lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val locale = Locale.getDefault()
        val configuration = Configuration()
        configuration.locale = locale
        activity!!.baseContext.resources.updateConfiguration(configuration, null)

        binding = FragmentSettingsBinding.inflate(layoutInflater)
        return binding.root
//        inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(
        view: View, savedInstanceState: Bundle
        ?
    ) {
        super.onViewCreated(view, savedInstanceState)

        Fragment1.resume = 3

        val lang = Locale.getDefault().toString()
        when (lang) {
            "en" -> binding.tvLanguage2.setText(R.string.english)
            "ru" -> binding.tvLanguage2.setText(R.string.russian)
        }
        when (MainActivity.FLAG_MILE_TO_KM) {
            1 -> binding.tvDistance2.setText(R.string.km)
            2 -> binding.tvDistance2.setText(R.string.mile_2)
        }


        binding.tvLanguage2.setOnClickListener {
            showPopupLang(binding.tvLanguage2)
        }
        binding.ivLanguage.setOnClickListener {
            showPopupLang(binding.ivLanguage)
        }
        translate()
        binding.tvDistance2.setOnClickListener{
            showPopupDist(binding.tvDistance2)
        }
        binding.ivDistanse.setOnClickListener{
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
                    setApplicationLanguage("en")
                    translate()
                }
                R.id.ru -> {
                    MainActivity.LANG_ID = 2
                    setApplicationLanguage("ru")
                    translate()
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
                    MainActivity.FLAG_MILE_TO_KM = 1
                    binding.tvDistance2.setText(R.string.km)
                    saveSw()
                }
                R.id.mile -> {
                    MainActivity.FLAG_MILE_TO_KM = 2
                    binding.tvDistance2.setText(R.string.mile_2)
                    saveSw()
                }
            }
            true
        }
        popupMenu.show()
    }

    fun saveSw() {
        val sPref = activity!!.getPreferences(MODE_PRIVATE)
        val ed = sPref.edit()
        ed.putInt(MainActivity.APP_MILE_TO_KM, MainActivity.FLAG_MILE_TO_KM)
        ed.apply()
        Fragment1.resume = 1
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
        binding.interfaceTitle.setText(R.string.system)
        binding.tvLanguage.setText(R.string.language)
        binding.tvDistance.setText(R.string.distanceSetting)
        when (MainActivity.FLAG_MILE_TO_KM) {
            1 -> binding.tvDistance2.setText(R.string.km)
            2 -> binding.tvDistance2.setText(R.string.mile_2)
        }

    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        translate()
        super.onConfigurationChanged(newConfig)
    }


}
