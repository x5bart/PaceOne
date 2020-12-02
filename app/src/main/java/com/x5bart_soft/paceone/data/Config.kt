package com.x5bart_soft.paceone.data

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.widget.TextView
import com.x5bart_soft.paceone.utils.MyPreference
import java.util.*

object Config {
    var FLAG_MILE_TO_KM = false
    var language = Locale.getDefault().toString()
    var changeLanguage = false


}