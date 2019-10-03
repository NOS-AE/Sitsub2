package org.fmod.sitsub2.data.remote.service

import org.fmod.sitsub2.data.remote.model.recieve.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface UserService {

    @GET("user")
    @Headers("Accept: application/json")
    suspend fun getUserInfo(
        @Header("forceNetWork") forceNetWork: Boolean
    ): Response<User>
}