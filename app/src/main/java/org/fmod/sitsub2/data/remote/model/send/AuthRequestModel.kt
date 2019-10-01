package org.fmod.sitsub2.data.remote.model.send

import com.google.gson.annotations.SerializedName
import org.fmod.sitsub2.BuildConfig
import org.fmod.sitsub2.data.remote.CLIENT_ID
import org.fmod.sitsub2.data.remote.CLIENT_SECRET
import org.fmod.sitsub2.data.remote.REDIRECT_URL

data class AuthRequestModel(
    var scopes: List<String>,
    var note: String,
    @SerializedName("note_url") var noteUrl: String,
    @SerializedName("client_id") var clientId: String,
    @SerializedName("client_secret") var clientSecret: String
) {
    companion object {
        fun generate(): AuthRequestModel =
            AuthRequestModel(
                arrayListOf("user", "repo", "gist", "notifications"),
                BuildConfig.APPLICATION_ID,
                REDIRECT_URL,
                CLIENT_ID,
                CLIENT_SECRET
            )
    }
}