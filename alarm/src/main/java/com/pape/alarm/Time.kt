package com.pape.alarm

import android.content.Context
import android.text.format.DateUtils

/**
 * Created by zzy05 on 2017/8/29.
 */
fun getDayAndTimeAlarmDisplayString(context: Context, timeUntilAlarm: Long): String {
    return DateUtils.formatDateTime(context, timeUntilAlarm, DateUtils.FORMAT_SHOW_TIME or DateUtils.FORMAT_SHOW_WEEKDAY)
}