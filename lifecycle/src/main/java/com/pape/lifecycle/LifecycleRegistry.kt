package com.pape.lifecycle

import com.pape.lifecycle.Lifecycle
import com.pape.lifecycle.LifecycleObserver
import com.pape.lifecycle.LifecycleOwner
import java.util.*

/**
 * @project: LifecycleHelper
 * @description: //被观察者 通知管理
 * @author zzy05 zhangzhanyong@feinno.com
 * @date 2017/7/21 9:11
 * @updateUser zzy05
 * @update 2017/7/21 9:11
 * @version V1.0
 */
class LifecycleRegistry(val lifecycleOwner: LifecycleOwner
                        , var state: Lifecycle.State = Lifecycle.State.INITIALIZED
                        , var lastEvent: Lifecycle.Event = Lifecycle.Event.ON_ANY
                        , var list: ArrayList<LifecycleObserver> = arrayListOf()) : Lifecycle {

    /**
     * 添加一个LifecycleObserver，当LifecycleOwner更改状态时，它将被通知。
     */
    override fun addObserver(lifecycleObserver: LifecycleObserver) {
        if (!list.contains(lifecycleObserver)) list.add(lifecycleObserver)
    }

    /**
     * 返回生命周期的当前状态。
     */
    override fun getCurrentState(): Lifecycle.State {
        return this.state
    }

    /**
     * 观察员人数。
     */
    fun getObserverCount(): Int {
        return list.size
    }

    /**
     * 设置当前状态并通知观察者。
     */
    fun handleLifecycleEvent(event: Lifecycle.Event) {
        if (lastEvent != event) {
            lastEvent = event
            state = getStateAfter(event)
            list.forEach {
                it.onStateChanged(lifecycleOwner, event)
            }
        }

    }

    private fun getStateAfter(event: Lifecycle.Event): Lifecycle.State {
        when (event.ordinal) {
            1, 2 -> return Lifecycle.State.CREATED
            3, 4 -> return Lifecycle.State.STARTED
            5 -> return Lifecycle.State.RESUMED
            6 -> return Lifecycle.State.DESTROYED
            else -> throw  IllegalArgumentException("Unexpected event value " + event)
        }
    }

    /**
     * 只将当前状态标记为给定值
     */
    fun markState(state: Lifecycle.State) {
        this.state = state
    }

    /**
     * removeObserver（LifecycleObserver观察者）
     */
    override fun removeObserver(lifecycleObserver: LifecycleObserver) {
        if (list.contains(lifecycleObserver)) list.remove(lifecycleObserver)
    }

}