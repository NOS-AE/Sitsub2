package org.fmod.sitsub2.data.local

import org.fmod.sitsub2.data.local.entity.Suggestion

interface IDB {
    suspend fun insertUserSuggestion(username: String)
    suspend fun findAllUserSuggestion(): List<Suggestion>
    suspend fun deleteSuggestion(suggestion: Suggestion)
}