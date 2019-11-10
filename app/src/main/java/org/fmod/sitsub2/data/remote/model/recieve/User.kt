package org.fmod.sitsub2.data.remote.model.recieve

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class User(

    var login: String,
    var id: Long = 24354872,
    var name: String = "Yu Tang",
    @SerializedName("avatar_url") var avatarUrl: String = "https://avatars0.githubusercontent.com/u/24354872?v=4",
    @SerializedName("html_url") var htmlUrl: String = "https://github.com/NOS-AE",
    var type: UserType = UserType.User,
    var company: String = "",
    var blog: String = "",
    var location: String = "",
    var email: String = "",
    var bio: String = "",

    @SerializedName("public_repos") var publicRepos: Int = 0,
    @SerializedName("public_gists") var publicGists: Int = 0,
    var followers: Int = 0,
    var following: Int = 0,
    @SerializedName("created_at") var createdAt: Date,
    @SerializedName("updated_at") var updatedAt: Date
    //@SerializedName("followers_url") var followersUrl: String = "https://api.github.com/users/NOS-AE/followers"
    //@SerializedName("following_url") var followingUrl: String = "https://api.github.com/users/NOS-AE/following{/other_user}"
    //@SerializedName("gists_url") var gistsUrl: String = "https://api.github.com/users/NOS-AE/gists{/gist_id}"
    //@SerializedName("starred_url") var starredUrl: String = "https://api.github.com/users/NOS-AE/starred{/owner}{/repo}"
    //@SerializedName("subscriptions_url") var subscriptionsUrl: String = "https://api.github.com/users/NOS-AE/subscriptions"
    //@SerializedName("organizations_url") var organizationUrl: String = "https://api.github.com/users/NOS-AE/orgs"
    //@SerializedName("repos_url") var reposUrl: String = "https://api.github.com/users/NOS-AE/repos"
    //@SerializedName("events_url") var eventsUrl: String = "https://api.github.com/users/NOS-AE/events{/privacy}"
    //@SerializedName("received_events_url") var receivedEventsUrl: String = "https://api.github.com/users/NOS-AE/received_events"
    //@SerializedName("site_admin") var siteAdmin = false
    //var hireable: Boolean = false,
    //@SerializedName("gravatar_id") var grAvatarId: String = ""
    //var url: String = "https://api.github.com/users/NOS-AE"
    //@SerializedName("node_id") lateinit var nodeId: String = "MDQ6VXNlcjI0MzU0ODcy"

): Parcelable {

    enum class UserType{
        User, Organization
    }

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