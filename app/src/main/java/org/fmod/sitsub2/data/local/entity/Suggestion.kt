package org.fmod.sitsub2.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index("type")])
data class Suggestion(
    @PrimaryKey
    var text: String,
    @ColumnInfo(name = "type")
    var type: Int //多种建议（用户名历史，搜索历史等）
) {

    companion object {
        fun toSuggestionList(array: Array<String>, type: Int): ArrayList<Suggestion> {
            val res = ArrayList<Suggestion>()
            array.forEach {
                res.add(Suggestion(it, type))
            }
            return res
        }
    }

    override fun toString(): String {
        return text
    }
}