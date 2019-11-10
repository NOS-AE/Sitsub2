package org.fmod.sitsub2.data.pref.setting

interface ISettingPref {
    var isDarkTheme: Boolean
    var language: Int
    fun clearSettingPref()
}