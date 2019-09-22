package org.fmod.sitsub2.data.remote

import org.fmod.sitsub2.data.remote.service.LoginService
import retrofit2.Retrofit

object ServiceProvider {

    fun getLoginService(token: String) = RetrofitProvider
        .getRetrofit(GITHUB_API_BASE_URL, token)
        .create(LoginService::class.java)
}