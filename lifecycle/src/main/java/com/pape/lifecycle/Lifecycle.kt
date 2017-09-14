package com.pape.lifecycle


/**
 * @project: LifecycleHelper
 * @description: //生命周期的 枚举类
 * @author zzy05 zhangzhanyong@feinno.com
 * @date 2017/7/21 9:02
 * @updateUser zzy05
 * @update 2017/7/21 9:02
 * @version V1.0
 */
interface Lifecycle {

    enum class Event {
        ON_ANY, ON_CREATE, ON_DESTROY, ON_PAUSE, ON_RESUME, ON_START, ON_STOP
    }

    enum class State {
        CREATED, DESTROYED, INITIALIZED, RESUMED, STARTED;

        fun isAtLeast(state: State): Boolean {
            return compareTo(state) > 0
        }
    }

    fun addObserver(lifecycleObserver: LifecycleObserver)

    fun getCurrentState(): State

    fun removeObserver(lifecycleObserver: LifecycleObserver)

}