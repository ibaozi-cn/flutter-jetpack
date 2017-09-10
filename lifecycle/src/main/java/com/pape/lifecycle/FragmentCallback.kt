package com.pape.lifecycle


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager


/**
 * @project: LifecycleHelper
 * @description: //v4 包里的FragmentCallback
 * @author zzy05 zhangzhanyong@feinno.com
 * @date 2017/7/21 8:59
 * @updateUser zzy05
 * @update 2017/7/21 8:59
 * @version V1.0
 */
class FragmentCallback : FragmentManager.FragmentLifecycleCallbacks() {

    override fun onFragmentCreated(fm: FragmentManager?, f: Fragment?, savedInstanceState: Bundle?) {
        super.onFragmentCreated(fm, f, savedInstanceState)
        dispatchIfLifecycleOwner(f, Lifecycle.Event.ON_CREATE)

        if (f is LifecycleRegistryOwner) {
            if (f.childFragmentManager.findFragmentByTag(REPORT_FRAGMENT_TAG) == null) {
                f.childFragmentManager.beginTransaction().add(DestructionReportFragment(),
                        REPORT_FRAGMENT_TAG).commit()
            }
        }

    }

    override fun onFragmentStarted(fm: FragmentManager?, f: Fragment?) {
        dispatchIfLifecycleOwner(f, Lifecycle.Event.ON_START)
    }

    override fun onFragmentResumed(fm: FragmentManager?, f: Fragment?) {
        dispatchIfLifecycleOwner(f, Lifecycle.Event.ON_RESUME)
    }

}

class DestructionReportFragment : Fragment() {

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
    }

    private fun dispatch(event: Lifecycle.Event) {
        dispatchIfLifecycleOwner(parentFragment, event)
    }
}

fun dispatchIfLifecycleOwner(parentFragment: Fragment?, event: Lifecycle.Event) {
    if (parentFragment is LifecycleRegistryOwner) {
        parentFragment.getLifecycle().handleLifecycleEvent(event)
    }
}