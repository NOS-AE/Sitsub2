package org.fmod.sitsub2.data.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Credentials
import org.fmod.sitsub2.data.DataManager
import org.fmod.sitsub2.data.remote.model.send.AuthRequestModel
import org.fmod.sitsub2.data.remote.service.LoginService
import org.fmod.sitsub2.util.remoteLog

object RemoteHelper {

    //使用Basic Auth获取token
    suspend fun basicLogin(id: String, pw: String) = withContext(Dispatchers.IO) {
        val token = Credentials.basic(id, pw)
        remoteLog("token: $token")
        val authRequestModel = AuthRequestModel.generate()
        val loginService = RetrofitProvider
            .getRetrofit(GITHUB_API_BASE_URL, token)
            .create(LoginService::class.java)
        loginService.authorizations(authRequestModel)
    }

    suspend fun deleteGrant(grantId: String) = withContext(Dispatchers.IO) {
        val token = Credentials.basic(DataManager.username, DataManager.password)
    }
}