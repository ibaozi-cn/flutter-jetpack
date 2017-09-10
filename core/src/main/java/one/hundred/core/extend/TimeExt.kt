@file:JvmName("TimeUtils")

package one.hundred.core.extend

import android.content.Context
import android.text.format.DateUtils
import one.hundred.core.R
import java.util.*

fun Long.getFriendlyTime(): String {
    val dateTime = Date(this * 1000)
    val sb = StringBuffer()
    val current = Calendar.getInstance().time
    var diffInSeconds = ((current.time - dateTime.time) / 1000).toInt()

    val sec = if (diffInSeconds >= 60) (diffInSeconds % 60).toInt() else diffInSeconds.toInt()
    diffInSeconds /= 60
    val min = if (diffInSeconds >= 60) (diffInSeconds % 60).toInt() else diffInSeconds.toInt()
    diffInSeconds /= 60
    val hrs = if (diffInSeconds >= 24) (diffInSeconds % 24).toInt() else diffInSeconds.toInt()
    diffInSeconds /= 24
    val days = if (diffInSeconds >= 30) (diffInSeconds % 30).toInt() else diffInSeconds.toInt()
    diffInSeconds /= 30
    val months = if (diffInSeconds >= 12) (diffInSeconds % 12).toInt() else diffInSeconds.toInt()
    diffInSeconds /= 12
    val years = diffInSeconds.toInt()

    if (years > 0) {
        if (years == 1) {
            sb.append("a year")
        } else {
            sb.append("$years years")
        }
        if (years <= 6 && months > 0) {
            if (months == 1) {
                sb.append(" and a month")
            } else {
                sb.append(" and $months months")
            }
        }
    } else if (months > 0) {
        if (months == 1) {
            sb.append("a month")
        } else {
            sb.append("$months months")
        }
        if (months <= 6 && days > 0) {
            if (days == 1) {
                sb.append(" and a day")
            } else {
                sb.append(" and $days days")
            }
        }
    } else if (days > 0) {
        if (days == 1) {
            sb.append("a day")
        } else {
            sb.append("$days days")
        }
        if (days <= 3 && hrs > 0) {
            if (hrs == 1) {
                sb.append(" and an hour")
            } else {
                sb.append(" and $hrs hours")
            }
        }
    } else if (hrs > 0) {
        if (hrs == 1) {
            sb.append("an hour")
        } else {
            sb.append("$hrs hours")
        }
        if (min > 1) {
            sb.append(" and $min minutes")
        }
    } else if (min > 0) {
        if (min == 1) {
            sb.append("a minute")
        } else {
            sb.append("$min minutes")
        }
        if (sec > 1) {
            sb.append(" and $sec seconds")
        }
    } else {
        if (sec <= 1) {
            sb.append("about a second")
        } else {
            sb.append("about $sec seconds")
        }
    }

    sb.append(" ago")

    return sb.toString()
}

fun getDayAndTimeAlarmDisplayString(context: Context, timeUntilAlarm: Long): String {
    return DateUtils.formatDateTime(context, timeUntilAlarm, DateUtils.FORMAT_SHOW_TIME or DateUtils.FORMAT_SHOW_WEEKDAY)
}

fun formatToast(context: Context, timeInMillis: Long): String {
    val delta = timeInMillis - System.currentTimeMillis()
    var hours = (delta / (1000 * 60 * 60)).toInt()
    val minutes = (delta / (1000 * 60) % 60).toInt()
    val days = hours / 24
    hours %= 24

    val daySeq = if (days == 0)
        ""
    else if (days == 1)
        context.getString(R.string.day)
    else
        context.getString(R.string.days, days.toString())

    val minSeq = if (minutes == 0)
        ""
    else if (minutes == 1)
        context.getString(R.string.minute)
    else
        context.getString(R.string.minutes, minutes.toString())

    val hourSeq = if (hours == 0)
        ""
    else if (hours == 1)
        context.getString(R.string.hour)
    else
        context.getString(R.string.hours, hours.toString())

    val dispDays = days > 0
    val dispHour = hours > 0
    val dispMinute = minutes > 0

    val index = (if (dispDays) 1 else 0) or
            (if (dispHour) 2 else 0) or
            if (dispMinute) 4 else 0

    val formats = context.resources.getStringArray(R.array.alarm_set)
    return String.format(formats[index], daySeq, hourSeq, minSeq)
}