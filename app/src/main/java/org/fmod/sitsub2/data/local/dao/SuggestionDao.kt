package org.fmod.sitsub2.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.fmod.sitsub2.data.local.SUG_USERNAME
import org.fmod.sitsub2.data.local.entity.Suggestion

@Dao
interface SuggestionDao {

    @Query("SELECT * from Suggestion WHERE type = $SUG_USERNAME ORDER BY text ASC")
    suspend fun findUserSuggestion(): List<Suggestion>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUserSuggestion(suggestion: Suggestion)
}