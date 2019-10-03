package org.fmod.sitsub2.data.local

import org.fmod.sitsub2.data.local.entity.AuthUser
import org.fmod.sitsub2.data.local.entity.UserSuggestion

interface IDB {
    suspend fun insertUserSuggestion(username: String)
    suspend fun findAllUserSuggestion(): List<UserSuggestion>
    suspend fun deleteSuggestion(userSuggestion: UserSuggestion)
    suspend fun insertAuthUser(authUser: AuthUser)
    suspend fun updateAllToUnselected()
    suspend fun deleteAuthUser(authUser: AuthUser)
}