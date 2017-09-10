package com.pape.alarm

import android.app.job.JobParameters
import android.app.job.JobService

/**
 * Created by zzy on 2017/8/2.
 * 5.0以上手机 定时启动提醒
 */
class PlanJobService : JobService() {

    override fun onStopJob(jobParameters: JobParameters?): Boolean {
        return false
    }

    override fun onStartJob(jobParameters: JobParameters?): Boolean {
        return true
    }

}