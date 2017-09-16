package one.hundred.lifestyle.data.local.converter

import android.arch.persistence.room.TypeConverter
import java.util.Date


/**
 * Created by zzy on 2017/9/16.
 */
class Date {

    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return if (timestamp == null) null else Date(timestamp)
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return (if (date == null) null else date!!.getTime())?.toLong()
    }

}