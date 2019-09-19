package org.fmod.sitsub2.data.pref.setting

object SettingPref: ISettingPref {
    override var theme: Int by SettingPrefHelper(0)
    override fun clearSettingPref() {
        SettingPrefHelper.clear()
    }
}