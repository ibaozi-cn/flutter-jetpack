package com.pape.lifecycle

/**
 * Created by zzy on 2017/9/14.
 * Activity的生命周期适配器
 */
interface LifecycleAdapter : LifecycleObserver {

     override  fun onStateChanged(lifecycleOwner: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_CREATE -> onCreate(lifecycleOwner)
            Lifecycle.Event.ON_DESTROY -> onDestroy(lifecycleOwner)
            Lifecycle.Event.ON_PAUSE -> onPause(lifecycleOwner)
            Lifecycle.Event.ON_RESUME -> onResume(lifecycleOwner)
            Lifecycle.Event.ON_START -> onStart(lifecycleOwner)
            Lifecycle.Event.ON_STOP -> onStop(lifecycleOwner)
        }
    }

    fun onCreate(lifecycleOwner: LifecycleOwner) {

    }

    fun onStart(lifecycleOwner: LifecycleOwner) {

    }

    fun onResume(lifecycleOwner: LifecycleOwner) {

    }

    fun onPause(lifecycleOwner: LifecycleOwner) {

    }

    fun onStop(lifecycleOwner: LifecycleOwner) {

    }

    fun onDestroy(lifecycleOwner: LifecycleOwner) {

    }
}