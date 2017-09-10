package com.pape.lifecycle

import android.app.Fragment
import android.os.Bundle

/**
 * @project: LifecycleHelper
 * @description: // fragment生命周期内 负责通知观察者状态变更
 * @author zzy05 zhangzhanyong@feinno.com
 * @date 2017/7/21 10:31
 * @updateUser zzy05
 * @update 2017/7/21 10:31
 * @version V1.0
 */
class ReportFragment : Fragment() {

    var activityInitializationListener: ActivityInitializationListener? = null

    interface ActivityInitializationListener {

        fun onCreate()

        fun onStart()

        fun onResume()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activityInitializationListener?.onCreate()
        dispatch(Lifecycle.Event.ON_CREATE)
    }

    override fun onStart() {
        super.onStart()
        activityInitializationListener?.onStart()
        dispatch(Lifecycle.Event.ON_START)
    }

    override fun onResume() {
        super.onResume()
        activityInitializationListener?.onResume()
        dispatch(Lifecycle.Event.ON_RESUME)
    }

    override fun onPause() {
        super.onPause()
        dispatch(Lifecycle.Event.ON_PAUSE)
    }

    override fun onStop() {
        super.onStop()
        dispatch(Lifecycle.Event.ON_STOP)
    }

    override fun onDestroy() {
        super.onDestroy()
        dispatch(Lifecycle.Event.ON_DESTROY)
        activityInitializationListener = null
    }

    fun dispatch(event: Lifecycle.Event) {
        if (activity is LifecycleRegistryOwner) {
            val lifecycleOwner = activity as LifecycleRegistryOwner
            lifecycleOwner.getLifecycle().handleLifecycleEvent(event)
        }
    }
}

