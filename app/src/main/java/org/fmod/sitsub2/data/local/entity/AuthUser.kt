package org.fmod.sitsub2.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.android.parcel.Parcelize
import org.fmod.sitsub2.data.local.dao.AuthUserDao
import java.time.OffsetDateTime
import java.util.*

@Entity
@Parcelize
@TypeConverters(AuthUserDao.AuthUserConverter::class)
data class AuthUser(
    @PrimaryKey var accessToken: String,
    var authTime: Date,
    var expireIn: Int,
    var scope: String,
    var selected: Boolean,
    var loginId: String,
    var name: String,
    var avatar: String
): Parcelable {

}