package com.pape.lifecycle

import com.pape.lifecycle.LifecycleOwner
import com.pape.lifecycle.LifecycleRegistry

/**
 * @project: LifecycleHelper
 * @description: //观察者 实现
 * @author zzy05 zhangzhanyong@feinno.com
 * @date 2017/7/21 9:11
 * @updateUser zzy05
 * @update 2017/7/21 9:11
 * @version V1.0
 */
interface LifecycleRegistryOwner : LifecycleOwner {
    override fun getLifecycle(): LifecycleRegistry
}