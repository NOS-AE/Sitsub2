package org.fmod.sitsub2.data.pref.user

object UserPref: IUserPref {
    override var username: String by UserPrefHelper("")
    override var password: String by UserPrefHelper("")
    override var isLogin: Boolean by UserPrefHelper(false)
    override fun clearUserPref() {
        UserPrefHelper.clear()
    }
}