package com.pape.lifecycle

import com.pape.lifecycle.Lifecycle


/**
 * @project: LifecycleHelper
 * @description: //被观察者
 * @author zzy05 zhangzhanyong@feinno.com
 * @date 2017/7/21 9:04
 * @updateUser zzy05
 * @update 2017/7/21 9:04
 * @version V1.0
 */
interface LifecycleOwner {
    fun getLifecycle(): Lifecycle
}