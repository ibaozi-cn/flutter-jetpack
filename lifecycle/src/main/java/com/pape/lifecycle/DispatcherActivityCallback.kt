package com.pape.lifecycle


import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.FragmentActivity

/**
 * @project: LifecycleHelper
 * @description: //监听activity回调
 * @author zzy05 zhangzhanyong@feinno.com
 * @date 2017/7/21 8:56
 * @updateUser zzy05
 * @update 2017/7/21 8:56
 * @version V1.0
 */
class DispatcherActivityCallback(val fragmentCallback: FragmentCallback = FragmentCallback()) : Application.ActivityLifecycleCallbacks {

    override fun onActivitySaveInstanceState(activity: Activity?, bundle: Bundle?) {
        if (activity is FragmentActivity) {
            markState(activity, Lifecycle.State.CREATED)
        }
    }

    /**
     * 1. 如果是FragmentActivity 则添加 FragmentCallback 监听 生命周期
     *
     * 2. 往activity中添加空的ReportFragment 来负责监听生命周期
     */
    override fun onActivityCreated(activity: Activity?, bundle: Bundle?) {

        if (activity is FragmentActivity) {
            activity.supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentCallback, true)
        }

        val fragmentManager = activity?.fragmentManager

        if (fragmentManager != null) {
            if (fragmentManager.findFragmentByTag(REPORT_FRAGMENT_TAG) == null) {
                fragmentManager.beginTransaction().add(ReportFragment(), REPORT_FRAGMENT_TAG).commit()
                fragmentManager.executePendingTransactions()
            }
        }
    }

    override fun onActivityStopped(activity: Activity?) {
        if (activity is FragmentActivity) {
            markState(activity, Lifecycle.State.CREATED)
        }
    }

    /**
     * 设置当前activity的状态
     */
    private fun markState(activity: FragmentActivity, created: Lifecycle.State) {
        if (activity is LifecycleRegistryOwner) {
            activity.getLifecycle().markState(created)
        }
    }


    override fun onActivityPaused(activity: Activity?) {
    }

    override fun onActivityResumed(activity: Activity?) {
    }

    override fun onActivityStarted(activity: Activity?) {
    }

    override fun onActivityDestroyed(activity: Activity?) {
    }
}