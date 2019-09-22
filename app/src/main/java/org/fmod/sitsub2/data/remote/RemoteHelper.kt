package org.fmod.sitsub2.data.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Credentials
import org.fmod.sitsub2.data.remote.model.AuthRequestModel

object RemoteHelper {
    suspend fun basicLogin(id: String, pw: String) = withContext(Dispatchers.IO) {
        val token = Credentials.basic(id, pw)
        val authRequestModel = AuthRequestModel.generate()
        ServiceProvider.getLoginService(token).authorizations(authRequestModel)
    }
}