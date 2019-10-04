package org.fmod.sitsub2.data.local.dao

import androidx.room.*
import org.fmod.sitsub2.base.BaseDao
import org.fmod.sitsub2.data.local.SUG_USERNAME
import org.fmod.sitsub2.data.local.entity.UserSuggestion

@Dao
interface UserSuggestionDao: BaseDao<UserSuggestion> {

    @Query("SELECT * from UserSuggestion ORDER BY text ASC")
    suspend fun findAll(): List<UserSuggestion>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserSuggestion(userSuggestion: UserSuggestion)

    @Delete
    suspend fun deleteSuggestion(userSuggestion: UserSuggestion)
}