package org.fmod.sitsub2.ui.activity.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.fmod.sitsub2.ui.activity.login.LoginActivity
import org.fmod.sitsub2.ui.activity.main.MainActivity
import org.fmod.sitsub2.util.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tryLogin()
    }

    private fun tryLogin(){
        startActivity<LoginActivity>()
        finish()
    }
}
