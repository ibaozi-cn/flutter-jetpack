package com.pape.net

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent

/**
 * author: zhangzhanyong
 * created on: 2017/12/19 下午5:07
 * description:
 */
interface LifecycleAdapter : LifecycleObserver {

//    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
//    fun onCreate() {
//        if (BuildConfig.DEBUG)
//            Log.d("net", "onCreate")
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_START)
//    fun onStart() {
//        if (BuildConfig.DEBUG)
//            Log.d("net", "onStart")
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
//    fun onResume() {
//        if (BuildConfig.DEBUG)
//            Log.d("net", "onResume")
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
//    fun onPause() {
//        if (BuildConfig.DEBUG)
//            Log.d("net", "onPause")
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
//    fun onStop() {
//        if (BuildConfig.DEBUG)
//            Log.d("net", "onStop")
//    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy()

}