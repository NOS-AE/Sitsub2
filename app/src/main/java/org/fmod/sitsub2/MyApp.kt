package org.fmod.sitsub2

import android.app.Application
import androidx.room.Room
import org.fmod.sitsub2.data.local.AppDB

class MyApp: Application() {

    companion object {
        lateinit var instance: MyApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        AppDB.db = Room.databaseBuilder(this, AppDB::class.java, "database").build()
    }


}