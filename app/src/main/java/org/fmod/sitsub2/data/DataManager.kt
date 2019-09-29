package org.fmod.sitsub2.data

import org.fmod.sitsub2.data.local.DB
import org.fmod.sitsub2.data.local.IDB
import org.fmod.sitsub2.data.pref.setting.ISettingPref
import org.fmod.sitsub2.data.pref.setting.SettingPref
import org.fmod.sitsub2.data.pref.user.IUserPref
import org.fmod.sitsub2.data.pref.user.UserPref

object DataManager: ISettingPref by SettingPref, IUserPref by UserPref, IDB by DB
