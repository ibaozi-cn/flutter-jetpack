package com.pape.alarm


import android.annotation.SuppressLint
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi
import com.pape.alarm.PlanJobService
import java.util.concurrent.TimeUnit


/**
 * Created by zzy on 2017/8/2.
 */

val Context.jobScheduler: JobScheduler
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    get() = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun finishAllJobService(context: Context) {
    val jobScheduler = context.jobScheduler
    val allPendingJobs = jobScheduler.allPendingJobs
    if (allPendingJobs.isNotEmpty()) {
        allPendingJobs.forEach {
            jobScheduler.cancel(it.id)
        }
    }
}

@SuppressLint("MissingPermission")
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun doWork(context: Context) {
    val jobScheduler = context.jobScheduler
    val componentName = ComponentName(context, PlanJobService::class.java)
    val jobInfoBuilder = JobInfo.Builder(1, componentName)
//             .setMinimumLatency(TimeUnit.MILLISECONDS.toMillis(10)) //执行最少延迟时间
            // .setOverrideDeadline(TimeUnit.MILLISECONDS.toMillis(15)) // 执行最长延迟时间
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_NONE) //网络任何状态
            .setBackoffCriteria(TimeUnit.MILLISECONDS.toMillis(10), JobInfo.BACKOFF_POLICY_LINEAR)//线性重试方案
            .setPeriodic(TimeUnit.MILLISECONDS.toMillis(10)) // 设置十分钟重复
            .setPersisted(true) //设置重启后继续执行
//            .setRequiresCharging(false)  // true 只有当设备在充电时这个任务才会被执行
//            .setRequiresDeviceIdle(false)   //true 只有当用户没有在使用该设备且有一段时间没有使用时才会启动该任务

    jobScheduler.schedule(jobInfoBuilder.build())

}

fun startPlanJobService(context: Context) {
    context.startService(Intent(context, PlanJobService::class.java))
}


