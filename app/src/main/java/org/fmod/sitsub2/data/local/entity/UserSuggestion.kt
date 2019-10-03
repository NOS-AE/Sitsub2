package org.fmod.sitsub2.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index("text")])
data class UserSuggestion(
    @PrimaryKey override var text: String
): Suggestion
