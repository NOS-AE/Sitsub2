package org.fmod.sitsub2.data.remote.model.recieve

import com.google.gson.annotations.SerializedName

data class BasicResponse(
    @SerializedName("token") var accessToken: String = "",
    var scopes: List<String> //repo,gists
) {
    var id: Int = 0
    var url: String = ""
    @SerializedName("updated_at") var updatedAt: String = ""
    @SerializedName("created_at") var createdAt: String = ""

    constructor(oauth: OAuthResponse): this(oauth.accessToken, oauth.scope.split(","))

}