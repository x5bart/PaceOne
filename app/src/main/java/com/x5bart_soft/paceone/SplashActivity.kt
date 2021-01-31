package com.x5bart_soft.paceone

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.x5bart_soft.paceone.data.Config
import com.x5bart_soft.paceone.utils.MyPreference


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val language = MyPreference().getLanguage()
        if (language != "notSave") Config.language = language!!

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


}