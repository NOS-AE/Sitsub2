package org.fmod.sitsub2.data.remote.model

import com.google.gson.annotations.SerializedName
import org.fmod.sitsub2.BuildConfig

data class AuthRequestModel(
    var scopes: List<String>,
    var note: String,
    var noteUrl: String,
    @SerializedName("client_id") var clientId: String,
    @SerializedName("client_secret") var clientSecret: String
) {
    companion object {
        /*fun generate(): AuthRequestModel {
            val model = AuthRequestModel(
                arrayListOf("user","repo","gist","notifications"),
                BuildConfig.APPLICATION_ID,
                BuildConfig.
            )
        }*/
    }
}