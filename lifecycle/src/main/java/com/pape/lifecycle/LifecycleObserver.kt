package com.pape.lifecycle


/**
 * @project: LifecycleHelper
 * @description: //观察者
 * @author zzy05 zhangzhanyong@feinno.com
 * @date 2017/7/21 9:03
 * @updateUser zzy05
 * @update 2017/7/21 9:03
 * @version V1.0
 */
interface LifecycleObserver {

    fun onStateChanged(lifecycleOwner: LifecycleOwner, event: Lifecycle.Event)

}