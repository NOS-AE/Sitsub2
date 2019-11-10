package org.fmod.sitsub2.data.pref.setting


object SettingPref: ISettingPref {
    override var isDarkTheme: Boolean by SettingPrefHelper(false)
    override var language: Int by SettingPrefHelper(Language.CN)
    override fun clearSettingPref() {
        SettingPrefHelper.clear()
    }
}

object Language {
    const val CN = 0
    const val EN = 1
}