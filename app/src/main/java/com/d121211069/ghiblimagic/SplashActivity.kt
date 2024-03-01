package com.d121211069.ghiblimagic

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val i = Intent(
                this@SplashActivity,
                MainActivity::class.java
            )

            startActivity(i)
            finish()
        }, 5000)
    }
}