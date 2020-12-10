package com.x5bart_soft.paceone.utils

import android.view.View
import com.yandex.mobile.ads.AdRequest
import com.yandex.mobile.ads.AdSize
import com.yandex.mobile.ads.AdView
import com.yandex.mobile.ads.nativeads.template.NativeBannerView

class AdsUtils {
    val blockIdPace = "adf-326819/1042468"
    val blockIdSplits = "adf-326819/1146536"
    val blockIdWings = "adf-326819/1146537"
    val blockIdPerson = "adf-326819/1147595"
    val blockIdSetting = "adf-326819/1146538"

    fun showAds(bannerView: AdView, blockId:String){
        bannerView.blockId = blockId
        bannerView.adSize = AdSize.BANNER_320x50
        val adRequest = AdRequest.builder().build()
        bannerView.loadAd(adRequest)
    }
}