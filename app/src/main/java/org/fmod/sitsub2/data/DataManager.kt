package org.fmod.sitsub2.data

import org.fmod.sitsub2.data.local.AppDB
import org.fmod.sitsub2.data.pref.setting.ISettingPref
import org.fmod.sitsub2.data.pref.setting.SettingPref
import org.fmod.sitsub2.data.pref.user.IUserPref
import org.fmod.sitsub2.data.pref.user.UserPref

object DataManager: ISettingPref by SettingPref, IUserPref by UserPref {

    val userSuggestionDao by lazy {
        AppDB.db.userSuggestionDao()
    }
    val authUserDao by lazy {
        AppDB.db.authUserDao()
    }

}