package org.fmod.sitsub2.data.local

import org.fmod.sitsub2.data.local.entity.AuthUser
import org.fmod.sitsub2.data.local.entity.UserSuggestion

object DB: IDB {

    private val suggestionDao by lazy { AppDB.db.suggestionDao() }

    private val authUserDao by lazy { AppDB.db.authUserDao() }

    override suspend fun findAllUserSuggestion(): List<UserSuggestion> {
        return suggestionDao.findUserSuggestion()
    }

    override suspend fun insertUserSuggestion(username: String) {
        suggestionDao.insertUserSuggestion(UserSuggestion(username))
    }

    override suspend fun deleteSuggestion(userSuggestion: UserSuggestion) {
        suggestionDao.deleteSuggestion(userSuggestion)
    }

    override suspend fun insertAuthUser(authUser: AuthUser) {
        authUserDao.insertAuthUser(authUser)
    }

    override suspend fun updateAllToUnselected() {
        authUserDao.updateAllToUnselected()
    }

    override suspend fun deleteAuthUser(authUser: AuthUser) {

    }

}