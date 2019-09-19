package org.fmod.sitsub2.data.pref.user

import android.content.Context
import android.content.SharedPreferences
import org.fmod.sitsub2.MyApp
import org.fmod.sitsub2.data.pref.PrefHelper

class UserPrefHelper<T>(default: T): PrefHelper<T>(default) {

    override fun getSharedPreferences(): SharedPreferences = spf

    companion object {
        private val spf by lazy {
            MyApp.instance.getSharedPreferences("user", Context.MODE_PRIVATE)
        }

        fun clear() {
            spf.edit().clear().apply()
        }
    }
}