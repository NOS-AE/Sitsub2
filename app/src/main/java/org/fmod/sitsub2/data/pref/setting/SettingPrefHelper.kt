package org.fmod.sitsub2.data.pref.setting

import android.content.Context
import android.content.SharedPreferences
import org.fmod.sitsub2.MyApp
import org.fmod.sitsub2.data.pref.PrefHelper

class SettingPrefHelper<T>(default: T): PrefHelper<T>(default) {

    override fun getSharedPreferences(): SharedPreferences = spf

    companion object {
        private val spf by lazy {
            MyApp.instance.getSharedPreferences("setting", Context.MODE_PRIVATE)
        }

        fun clear() {
            spf.edit().clear().apply()
        }
    }
}