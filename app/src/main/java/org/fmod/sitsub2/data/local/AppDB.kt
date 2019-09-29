package org.fmod.sitsub2.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import org.fmod.sitsub2.data.local.dao.SuggestionDao
import org.fmod.sitsub2.data.local.entity.Suggestion

@Database(entities = [Suggestion::class], version = 1)
abstract class AppDB: RoomDatabase() {
    companion object {
        lateinit var db: AppDB
    }
    abstract fun suggestionDao(): SuggestionDao
}