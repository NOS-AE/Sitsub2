package org.fmod.sitsub2.data.remote.model.recieve

import com.google.gson.annotations.SerializedName

data class OAuthResponse(
    @SerializedName("access_token") var accessToken: String,
    var scope: String
)