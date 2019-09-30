package org.fmod.sitsub2.data.local.dao

import androidx.room.*
import org.fmod.sitsub2.data.local.SUG_USERNAME
import org.fmod.sitsub2.data.local.entity.Suggestion
import org.fmod.sitsub2.data.remote.model.recieve.User

@Dao
interface SuggestionDao {

    @Query("SELECT * from Suggestion WHERE type = $SUG_USERNAME ORDER BY text ASC")
    suspend fun findUserSuggestion(): List<Suggestion>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUserSuggestion(suggestion: Suggestion)

    @Delete
    suspend fun deleteSuggestion(suggestion: Suggestion)
}