package com.pape.alarm


import android.app.PendingIntent
import com.pape.alarm.db.Alarm

/**
 * Created by zzy on 2017/8/3.
 */
interface AlarmScheduler {

    fun scheduleAlarms(): Boolean

    fun cancelAlarms()

    fun scheduleAlarm(alarm: Alarm)

    fun cancelAlarm(alarm: Alarm)

    fun createPendingIntent(alarm: Alarm): PendingIntent

    fun startAlarm(time: Long, pendingIntent: PendingIntent)

}