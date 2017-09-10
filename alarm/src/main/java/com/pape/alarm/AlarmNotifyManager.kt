package com.pape.alarm


import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import com.pape.alarm.R
import com.pape.alarm.db.Alarm
import com.pape.alarm.getDayAndTimeAlarmDisplayString


/**
 * Created by zzy on 2017/8/3.
 */
object AlarmNotifyManager {

    fun createAlarmNotify(context: Context, alarm: Alarm): Notification {

        val builder = NotificationCompat.Builder(context)

        builder.setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle(alarm.title)
                .setSubText(alarm.title)
                .setContentText(getDayAndTimeAlarmDisplayString(context, alarm.startTime))
                .setOngoing(false)
                .setAutoCancel(false)
                .priority = Notification.PRIORITY_DEFAULT

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.setVisibility(Notification.VISIBILITY_PRIVATE)
        }

        val startIntent = Intent("android.intent.action.Alarm")
        startIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startIntent.putExtra(ARGS_ALARM_ID, alarm.uuid)

        val contentIntent = PendingIntent.getActivity(context,
                alarm.uuid, startIntent, 0)

        builder.setContentIntent(contentIntent)

        return builder.build()
    }

}