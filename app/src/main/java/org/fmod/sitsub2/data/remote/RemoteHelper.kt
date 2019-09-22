package org.fmod.sitsub2.data.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object RemoteHelper {
    suspend fun login(id: String, pw: String) = withContext(Dispatchers.IO) {

    }
}