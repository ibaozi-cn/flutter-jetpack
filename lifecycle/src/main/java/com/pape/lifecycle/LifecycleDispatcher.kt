package com.pape.lifecycle


import android.app.Application
import android.content.Context
import com.pape.lifecycle.DispatcherActivityCallback

/**
 * @project: LifecycleHelper
 * @description: //注册了一个 ActivityLifecycleCallbacks
 * @author zzy05 zhangzhanyong@feinno.com
 * @date 2017/7/21 8:53
 * @updateUser zzy05
 * @update 2017/7/21 8:53
 * @version V1.0
 */
object LifecycleDispatcher {

    fun init(context: Context) {
        (context.applicationContext as Application).registerActivityLifecycleCallbacks(DispatcherActivityCallback())
    }

}