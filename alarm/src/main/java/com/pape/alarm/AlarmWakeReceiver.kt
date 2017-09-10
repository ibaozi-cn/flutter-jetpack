package com.pape.alarm


import android.content.Context
import android.content.Intent
import android.support.v4.content.WakefulBroadcastReceiver

/**
 * Created by zzy on 2017/8/3.
 * 自动创建和管理唤醒锁 PARTIAL_WAKE_LOCK 来执行任务. 确保耗时任务执行完毕之前设备不会休眠.
 *
 * 收到广播后一般会启动 Service (通常用 IntentService 来处理耗时任务),
 * 同时确保设备在整个 Service 执行过程中保持唤醒状态.
 * 不然的话, 对于耗时任务, 设备可能在你完成任务之前就休眠了.
 * 通过 startWakefulService(Context, Intent) 启动 Service
 * 而不是 startService(). WakefulBroadcastReceiver
 * 启动 Service 的时候会自动创建唤醒锁,
 * 并在 Intent 附上唤醒锁的 ID 来判断这个唤醒锁.
 * 最后必须在 Service 中调用 completeWakefulIntent(intent) 释放唤醒锁.
 */
class AlarmWakeReceiver : WakefulBroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val serviceIntent = Intent(context, AlarmNotifyService::class.java)
        serviceIntent.putExtras(intent)
        startWakefulService(context, serviceIntent)
    }
}