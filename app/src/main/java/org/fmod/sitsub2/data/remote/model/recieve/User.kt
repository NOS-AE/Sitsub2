package org.fmod.sitsub2.data.remote.model.recieve

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*

data class User(
    var login: String
): Parcelable {

    lateinit var id: String
    lateinit var name: String
    @SerializedName("avatar_url") lateinit var avatarUrl: String
    @SerializedName("html_url") lateinit var htmlUrl: String
    lateinit var userType: UserType
    lateinit var company: String
    lateinit var blog: String
    lateinit var location: String
    lateinit var email: String
    lateinit var bio: String

    @SerializedName("public_repos") var publicRepos: Int = 0
    @SerializedName("public_gists") var publicGists: Int = 0
    var followers: Int = 0
    var following: Int = 0
    @SerializedName("created_at") lateinit var createdAt: Date
    @SerializedName("updated_at") lateinit var updatedAt: Date

    enum class UserType{
        User, Organization
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    protected constructor(p0: Parcel): this(p0.readString() as String) {
        id = p0.readString() as String

    }

    companion object {
        @JvmField
        val CREATOR = object :Parcelable.Creator<User> {
            override fun createFromParcel(p0: Parcel): User {
                return User(p0)
            }

            override fun newArray(p0: Int): Array<User?> {
                return arrayOfNulls(p0)
            }
        }
    }

}