package org.fmod.sitsub2.data.remote.model.recieve

import com.google.gson.annotations.SerializedName

data class BasicResponse(
    var id: Int,
    var url: String,
    var scopes: List<String>,
    var token: String,
    @SerializedName("updated_at") var updatedAt: String,
    @SerializedName("created_at") var createdAt: String
)