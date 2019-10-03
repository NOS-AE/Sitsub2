package org.fmod.sitsub2.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import org.fmod.sitsub2.data.local.dao.AuthUserDao
import org.fmod.sitsub2.data.local.dao.SuggestionDao
import org.fmod.sitsub2.data.local.entity.AuthUser
import org.fmod.sitsub2.data.local.entity.UserSuggestion
import java.util.*

@Database(entities = [UserSuggestion::class, AuthUser::class], version = 1,exportSchema = false)
abstract class AppDB: RoomDatabase() {
    companion object {

        lateinit var db: AppDB
    }
    abstract fun suggestionDao(): SuggestionDao
    abstract fun authUserDao(): AuthUserDao

}