package org.fmod.sitsub2.data.local.dao

import androidx.room.*
import org.fmod.sitsub2.base.BaseDao
import org.fmod.sitsub2.data.local.entity.UserSuggestion

@Dao
interface UserSuggestionDao: BaseDao<UserSuggestion> {

    @Query("SELECT * from UserSuggestion ORDER BY text ASC")
    suspend fun find(): List<UserSuggestion>

    @Delete
    suspend fun deleteSuggestion(userSuggestion: UserSuggestion)
}