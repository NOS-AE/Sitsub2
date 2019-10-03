package org.fmod.sitsub2.data.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Credentials
import org.fmod.sitsub2.data.DataManager
import org.fmod.sitsub2.data.remote.model.send.AuthRequestModel
import org.fmod.sitsub2.data.remote.service.LoginService
import org.fmod.sitsub2.data.remote.service.UserService
import org.fmod.sitsub2.util.remoteLog

object RemoteHelper {

    //使用Basic Auth获取token
    suspend fun basicLogin(id: String, pw: String) = withContext(Dispatchers.IO) {
        val token = Credentials.basic(id, pw)
        val authRequestModel = AuthRequestModel.generate()
        val loginService = RetrofitProvider.instance
            .getRetrofit(GITHUB_API_BASE_URL, token)
            .create(LoginService::class.java)
        loginService.authorizations(authRequestModel)
    }

    suspend fun getAccessToken(code: String, state: String) = withContext(Dispatchers.IO) {
        val loginService = RetrofitProvider.instance
            .getRetrofit(GITHUB_BASE_URL, null)
            .create(LoginService::class.java)
        loginService.getAccessToken(
            CLIENT_ID,
            CLIENT_SECRET,
            code,
            state
        )
    }

    suspend fun getUserInfo(token: String) = withContext(Dispatchers.IO) {
        val userService = RetrofitProvider.instance
            .getRetrofit(GITHUB_API_BASE_URL, token)
            .create(UserService::class.java)
        userService.getUserInfo(true)
    }
}