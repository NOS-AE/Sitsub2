package org.fmod.sitsub2

import android.app.Application
import android.content.Context

class MyApp: Application() {

    companion object {
        lateinit var instance: MyApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}