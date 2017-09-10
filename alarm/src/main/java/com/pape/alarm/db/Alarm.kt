package com.pape.alarm.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * Created by zzy on 2017/8/3.
 */
@Entity
data class Alarm(
        @PrimaryKey
        val uuid: Int = Math.abs(UUID.randomUUID().leastSignificantBits).toInt(),
        val title: String,
        val startTime: Long = System.currentTimeMillis() + 15000,
        val isEnable: Boolean = true,
        val daysOfWeek: Int = 0
)