package com.x5bart_soft.paceone

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.x5bart_soft.paceone.databinding.FragmentSettingsBinding
import com.x5bart_soft.paceone.data.Config
import com.x5bart_soft.paceone.utils.AdsUtils
import com.x5bart_soft.paceone.utils.ContextUtils
import com.x5bart_soft.paceone.utils.MyPreference


class SettingsFragment : Fragment() {
    private lateinit var preference: MyPreference
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var utils: ContextUtils
    private lateinit var config: Config
    private lateinit var adsUtils: AdsUtils

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(
        view: View, savedInstanceState: Bundle
        ?
    ) {
        super.onViewCreated(view, savedInstanceState)

        preference = MyPreference()
        utils = ContextUtils(this.activity)
        config = Config
        adsUtils = AdsUtils()

        adsUtils.showAds(binding.settingBannerView, adsUtils.blockIdSetting)

        binding.settingsTvLanguage2.setOnClickListener { showPopupLang(binding.settingsTvLanguage2) }
        binding.settingIvLanguage.setOnClickListener { showPopupLang(binding.settingIvLanguage) }
    }

    private fun showPopupLang(v: View) {
        val popupMenu = PopupMenu(activity, v, Gravity.NO_GRAVITY)
        popupMenu.inflate(R.menu.popup_menu_language)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.en -> {
                    changeLanguage("en")
                }
                R.id.ru -> {
                    changeLanguage("ru")
                }
            }
            true
        }
        popupMenu.show()
    }

    private fun changeLanguage(language: String) {
        config.language = language
        config.changeLanguage = true
        preference.setLanguage(language)
        val intent = Intent(context, MainActivity::class.java)
        requireActivity().finish()
        requireContext().startActivity(intent)
    }


}
