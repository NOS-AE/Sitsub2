package org.fmod.sitsub2.data.pref.user

interface IUserPref {
    var username: String
    var password: String
    var isLogin: Boolean
    fun clearUserPref()
}