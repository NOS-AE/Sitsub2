package org.fmod.sitsub2.data.remote.model.recieve

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*

data class User(
    var login: String
) {

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

    /*override fun writeToParcel(p0: Parcel?, p1: Int) {

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
    }*/

}

/*
{
  "login": "octocat",
  "id": 1,
  "node_id": "MDQ6VXNlcjE=",
  "avatar_url": "https://github.com/images/error/octocat_happy.gif",
  "gravatar_id": "",
  "url": "https://api.github.com/users/octocat",
  "html_url": "https://github.com/octocat",
  "followers_url": "https://api.github.com/users/octocat/followers",
  "following_url": "https://api.github.com/users/octocat/following{/other_user}",
  "gists_url": "https://api.github.com/users/octocat/gists{/gist_id}",
  "starred_url": "https://api.github.com/users/octocat/starred{/owner}{/repo}",
  "subscriptions_url": "https://api.github.com/users/octocat/subscriptions",
  "organizations_url": "https://api.github.com/users/octocat/orgs",
  "repos_url": "https://api.github.com/users/octocat/repos",
  "events_url": "https://api.github.com/users/octocat/events{/privacy}",
  "received_events_url": "https://api.github.com/users/octocat/received_events",
  "type": "User",
  "site_admin": false,
  "name": "monalisa octocat",
  "company": "GitHub",
  "blog": "https://github.com/blog",
  "location": "San Francisco",
  "email": "octocat@github.com",
  "hireable": false,
  "bio": "There once was...",
  "public_repos": 2,
  "public_gists": 1,
  "followers": 20,
  "following": 0,
  "created_at": "2008-01-14T04:33:35Z",
  "updated_at": "2008-01-14T04:33:35Z",
  "private_gists": 81,
  "total_private_repos": 100,
  "owned_private_repos": 100,
  "disk_usage": 10000,
  "collaborators": 8,
  "two_factor_authentication": true,
  "plan": {
    "name": "Medium",
    "space": 400,
    "private_repos": 20,
    "collaborators": 0
  }
}
 */