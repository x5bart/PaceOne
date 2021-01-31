package com.x5bart_soft.paceone

import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
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
    private lateinit var navController: NavController
    private var btnId = ""
    private var changeLanguage = false
    private var language = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        preference = MyPreference(this)
        config = Config

        navController = findNavController(R.id.navHostFragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.paceFragment,
                R.id.splitFragment,
                R.id.wingsFragment,
                R.id.personFragment,
                R.id.settingsFragment
            )
        )
        binding.bottomNavigationView.setupWithNavController(navController)
//        setupActionBarWithNavController(navController, appBarConfiguration)


    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun attachBaseContext(newBase: Context?) {
        language = Config.language
        if (language.length == 5) language = language.substring(3)
        val localeToSwitchTo = Locale(language)
        val localeUpdatedContext: ContextWrapper =
            ContextUtils(this).updateLocale(newBase!!, localeToSwitchTo)!!
        super.attachBaseContext(localeUpdatedContext)
    }


}
