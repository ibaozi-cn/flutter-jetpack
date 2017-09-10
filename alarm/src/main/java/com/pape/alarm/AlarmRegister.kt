package com.pape.alarm


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.pape.alarm.AsyncHandler

/**
 * Created by zzy on 2017/8/3.
 */
class AlarmRegister : BroadcastReceiver() {

    lateinit var alarmScheduler: AlarmScheduler

    override fun onReceive(context: Context, intent: Intent) {
        alarmScheduler = AlarmSchedulerImpl(context)
        val result = goAsync()
        val wl = AlarmAlertWakeLock.createPartialWakeLock(context)
        wl.acquire()
        AsyncHandler.post({
            alarmScheduler.cancelAlarms()
            alarmScheduler.scheduleAlarms()
            result.finish()
            wl.release()
        })
    }

}