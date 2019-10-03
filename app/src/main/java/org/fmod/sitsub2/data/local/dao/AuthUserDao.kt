package org.fmod.sitsub2.data.local.dao

import androidx.room.*
import org.fmod.sitsub2.data.local.entity.AuthUser
import java.util.*

@Dao
@TypeConverters(AuthUserDao.AuthUserConverter::class)
interface AuthUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAuthUser(authUser: AuthUser)

    @Query("UPDATE AuthUser SET selected = 0 WHERE selected = 1")
    suspend fun updateAllToUnselected()

    @Query("SELECT * from AuthUser ORDER BY authTime ASC")
    suspend fun findAll(): List<AuthUser>

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