package org.fmod.sitsub2.data.local

import org.fmod.sitsub2.data.local.entity.Suggestion

object DB: IDB {

    private val suggestionDao by lazy {
        AppDB.db.suggestionDao()
    }

    override suspend fun findAllUserSuggestion(): List<Suggestion> {
        return suggestionDao.findUserSuggestion()
    }

    override suspend fun insertUserSuggestion(username: String) {
        suggestionDao.insertUserSuggestion(Suggestion(username, SUG_USERNAME))
    }
}