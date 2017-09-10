package com.pape.alarm


import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.support.v4.content.WakefulBroadcastReceiver

/**
 * Created by zzy on 2017/8/3.
 * 通知服务
 */
class AlarmNotifyService : Service() {

    private val mBinder = LocalBinder()

    override fun onBind(intent: Intent?): IBinder? {
        return mBinder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val alarmId = intent?.getIntExtra(ARGS_ALARM_ID, 0)
        if (alarmId != null && alarmId > 0) {
            val alarm = AlarmRepository.getAlarmById(alarmId)
            if (alarm != null) {
                val notify = AlarmNotifyManager.createAlarmNotify(baseContext, alarm)
                startForeground(alarmId, notify)
            } else {
                AlarmAlertWakeLock.releaseCpuLock()
                return Service.START_NOT_STICKY
            }
        }
        WakefulBroadcastReceiver.completeWakefulIntent(intent)
        return Service.START_NOT_STICKY
    }

    inner class LocalBinder : Binder() {
        val service: AlarmNotifyService
            get() = this@AlarmNotifyService
    }
}