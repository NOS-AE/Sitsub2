package org.fmod.sitsub2.data.local.dao

import androidx.room.*
import org.fmod.sitsub2.base.BaseDao
import org.fmod.sitsub2.data.local.entity.AuthUser
import java.util.*

@Dao
@TypeConverters(AuthUserDao.AuthUserConverter::class)
interface AuthUserDao: BaseDao<AuthUser> {

    @Query("UPDATE AuthUser SET selected = 0 WHERE selected = 1")
    suspend fun updateToUnselected()

    @Query("SELECT * FROM AuthUser ORDER BY authTime ASC")
    suspend fun find(): List<AuthUser>

    @Query("DELETE FROM AuthUser WHERE loginId = :loginId")
    suspend fun deleteByLoginId(loginId: String)

    object AuthUserConverter {

        @TypeConverter
        @JvmStatic
        fun long2Date(time: Long?): Date? {
            return time?.let {
                Date(it)
            }
        }

        @TypeConverter
        @JvmStatic
        fun date2Long(date: Date?): Long? {
            return date?.time
        }
    }

}