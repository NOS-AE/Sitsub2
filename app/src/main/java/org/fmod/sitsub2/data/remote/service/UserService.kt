package org.fmod.sitsub2.data.remote.service

import org.fmod.sitsub2.data.remote.model.recieve.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface UserService {

    @GET("user")
    fun getUserInfo(
        @Header("forceNetWork") forceNetWork: Boolean
    ): Response<User>
}