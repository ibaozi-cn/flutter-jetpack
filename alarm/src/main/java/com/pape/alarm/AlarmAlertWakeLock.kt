package com.pape.alarm


import android.content.Context
import android.os.PowerManager

/**
 * Created by zzy on 2017/8/3.
 */
object AlarmAlertWakeLock {

    private var sCpuWakeLock: PowerManager.WakeLock? = null

    fun createPartialWakeLock(context: Context): PowerManager.WakeLock {
        val pw = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        return pw.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "AlarmAlertWakeLock")
    }

    fun acquireCpuWakeLock(context: Context) {
        if (sCpuWakeLock != null) {
            return
        }
        sCpuWakeLock = createPartialWakeLock(context)
        sCpuWakeLock!!.acquire()
    }

    fun releaseCpuLock() {
        if (sCpuWakeLock != null) {
            sCpuWakeLock!!.release()
            sCpuWakeLock = null
        }
    }
}