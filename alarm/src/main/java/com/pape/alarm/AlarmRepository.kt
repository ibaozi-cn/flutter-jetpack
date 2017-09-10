package com.pape.alarm

import com.pape.alarm.db.Alarm


/**
 * Created by zzy on 2017/8/3.
 */
object AlarmRepository {

    val map: MutableMap<Int, Alarm> = mutableMapOf()

    fun getAlarmList(): List<Alarm> {
        return map.values.toList()
    }

    fun getAlarmById(id: Int): Alarm? {
        return map[id]
    }

    fun saveAlarm(alarm: Alarm) {
        map.put(alarm.uuid, alarm)
    }

}