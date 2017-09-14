package com.pape.alarm


import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.pape.alarm.db.Alarm

/**
 * Created by zzy on 2017/8/3.
 */
class AlarmSchedulerImpl(
        val context: Context) : AlarmScheduler {

    override fun startAlarm(time: Long, pendingIntent: PendingIntent) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        when {
            android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M -> alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, pendingIntent)
            android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT -> alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, pendingIntent)
            else -> alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent)
        }
    }

    /**
     * 唤醒屏幕 创建通知提醒
     */
    override fun createPendingIntent(alarm: Alarm): PendingIntent {

        val intent = Intent(context, AlarmWakeReceiver::class.java)

        intent.putExtra(ARGS_ALARM_ID, alarm.uuid)

        return PendingIntent.getBroadcast(context, alarm.uuid, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }


    override fun scheduleAlarms(): Boolean {
        val alarmList = AlarmRepository.getAlarmList()
        if (alarmList.isNotEmpty()) {
            alarmList.filter { it.isEnable }.map {
                scheduleAlarm(it)
            }
            return true
        }
        return false
    }

    override fun cancelAlarms() {
        val alarmList = AlarmRepository.getAlarmList()
        if (alarmList.isNotEmpty()) {
            alarmList.filter { it.isEnable }.map {
                cancelAlarm(it)
            }
        }
    }

    override fun scheduleAlarm(alarm: Alarm) {
        val pendingIntent = createPendingIntent(alarm)
        startAlarm(alarm.startTime, pendingIntent)
    }

    override fun cancelAlarm(alarm: Alarm) {
        val pIntent = createPendingIntent(alarm)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pIntent)
    }
}