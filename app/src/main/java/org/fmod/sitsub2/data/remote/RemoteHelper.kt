package org.fmod.sitsub2.data.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Credentials
import org.fmod.sitsub2.data.remote.model.recieve.User
import org.fmod.sitsub2.data.remote.model.send.AuthRequestModel
import org.fmod.sitsub2.data.remote.service.LoginService
import org.fmod.sitsub2.data.remote.service.UserService
import retrofit2.Response
import kotlin.properties.Delegates

object RemoteHelper {

    private lateinit var basicLoginService: LoginService
    private val oauthLoginService by lazy {
        RetrofitProvider.instance
            .getRetrofit(GITHUB_BASE_URL, null)
            .create(LoginService::class.java)
    }

    private var basicToken by Delegates.observable("") { _, old, new ->
        if(old != new) {
            basicLoginService = RetrofitProvider.instance
                .getRetrofit(GITHUB_API_BASE_URL, new)
                .create(LoginService::class.java)
        }
    }

    //使用Basic Auth获取token
    suspend fun basicLogin(id: String, pw: String) = withContext(Dispatchers.IO) {
        basicToken = Credentials.basic(id, pw)
        val authRequestModel = AuthRequestModel.generate()
        basicLoginService.authorizations(authRequestModel)
    }

    suspend fun getAccessToken(code: String, state: String) = withContext(Dispatchers.IO) {
        oauthLoginService.getAccessToken(
            CLIENT_ID,
            CLIENT_SECRET,
            code,
            state
        )
    }

    suspend fun getUserInfo(token: String): Response<User> {
        val userService = RetrofitProvider.instance
            .getRetrofit(GITHUB_API_BASE_URL, token)
            .create(UserService::class.java)
        return userService.getUserInfo(true)
    }
}