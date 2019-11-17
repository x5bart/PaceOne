package com.x5bart_soft.paceone

import android.app.Activity
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat.invalidateOptionsMenu
import androidx.core.app.ActivityCompat.recreate
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_settings.*
import java.util.*


class Settings : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val locale = Locale.getDefault()
        val configuration = Configuration()
        configuration.locale = locale
        activity!!.baseContext.resources.updateConfiguration(configuration, null)
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(
        view: View, savedInstanceState: Bundle
        ?
    ) {
        super.onViewCreated(view, savedInstanceState)

        Fragment1.resume = 3

        val lang = Locale.getDefault().toString()
        when (lang) {
            "en" -> tvLanguage2.setText(R.string.english)
            "ru" -> tvLanguage2.setText(R.string.russian)
        }
        when (MainActivity.FLAG_MILE_TO_KM) {
            1 -> tvDistance2.setText(R.string.km)
            2 -> tvDistance2.setText(R.string.mile_2)
        }


        tvLanguage2.setOnClickListener {
            showPopupLang(tvLanguage2)
        }
        ivLanguage.setOnClickListener {
            showPopupLang(ivLanguage)
        }
        translate()
        tvDistance2.setOnClickListener{
            showPopupDist(tvDistance2)
        }
        ivDistanse.setOnClickListener{
            showPopupDist(tvDistance2)
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
                    tvDistance2.setText(R.string.km)
                    saveSw()
                }
                R.id.mile -> {
                    MainActivity.FLAG_MILE_TO_KM = 2
                    tvDistance2.setText(R.string.mile_2)
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
            "en" -> tvLanguage2.setText(R.string.english)
            "ru" -> tvLanguage2.setText(R.string.russian)
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
        interfaceTitle.setText(R.string.system)
        tvLanguage.setText(R.string.language)
        tvDistance.setText(R.string.distanceSetting)
        when (MainActivity.FLAG_MILE_TO_KM) {
            1 -> tvDistance2.setText(R.string.km)
            2 -> tvDistance2.setText(R.string.mile_2)
        }

    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        translate()
        super.onConfigurationChanged(newConfig)
    }
}
