package org.fmod.sitsub2.data.remote.service

import org.fmod.sitsub2.data.remote.model.recieve.BasicResponse
import org.fmod.sitsub2.data.remote.model.recieve.OAuthResponse
import org.fmod.sitsub2.data.remote.model.send.AuthRequestModel
import retrofit2.Response
import retrofit2.http.*

interface LoginService {

    @POST("authorizations")
    @Headers("Accept: application/json")
    suspend fun authorizations(
        @Body authRequestModel: AuthRequestModel
    ): Response<BasicResponse>

    @POST("login/oauth/access_token")
    @FormUrlEncoded
    @Headers("Accept: application/json")
    suspend fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String,
        @Field("state") state: String
    ): Response<OAuthResponse>


}