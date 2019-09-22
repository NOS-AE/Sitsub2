package org.fmod.sitsub2.data.remote.service

import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginService {

    @POST("authorizations")
    //@Headers("Accept: application/vnd.github.v3+json")
    @Headers("Accept: application/json")
    fun authorizations(

    )

    @POST("login/oauth/access_token")
    @Headers("Accept: application/json")
    fun getAccessToken(
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("code") code: String,
        @Query("state") state: String
    )
}