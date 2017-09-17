package one.hundred.lifestyle.data.local

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

/**
 * Created by zzy on 2017/9/15.
 */
@Dao
interface DbTestDao {

    @Query("SELECT * FROM DbTest")
    fun getDataList(): LiveData<MutableList<DbTest>>

    @Query("SELECT * FROM DbTest WHERE id = :id")
    fun getData(id: Long): LiveData<DbTest>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveData(dbTest: DbTest)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveData(list: List<DbTest>)

    @Update
    fun updateData(dbTest: DbTest)

    @Delete
    fun deleteData(dbTest: DbTest)

}