package org.fmod.sitsub2

import org.fmod.sitsub2.data.local.entity.AuthUser
import org.fmod.sitsub2.data.remote.model.recieve.User

object AppData {
    lateinit var authUser: AuthUser
    var loggedUser: User? = null
}