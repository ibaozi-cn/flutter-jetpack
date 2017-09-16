package one.hundred.lifestyle.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import one.hundred.lifestyle.data.local.converter.Date

/**
 * Created by zzy on 2017/9/16.
 */
@Database(entities = arrayOf(DbTest::class), version = 1)
@TypeConverters(Date::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        val DATABASE_NAME = "basic-db"
    }

    abstract fun getDbTestDao(): DbTestDao

}