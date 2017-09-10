package com.pape.alarm.db

import android.arch.persistence.room.*


/**
 * Created by zzy on 2017/8/4.
 */
@Dao
interface AlarmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlarms(vararg alarm: Alarm)

    @Update
    fun updateAlarms(vararg alarm: Alarm)

    @Delete
    fun deleteAlarms(vararg alarm: Alarm)

    @Query("SELECT * FROM Alarm")
    fun loadAllAlarms(): Array<Alarm>

    @Query("SELECT * FROM Alarm WHERE uuid = :id")
    fun findAlarmsById(id: Int): Alarm

}