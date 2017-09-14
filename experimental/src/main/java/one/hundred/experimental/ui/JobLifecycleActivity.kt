package one.hundred.experimental.ui

import android.app.Activity
import kotlinx.coroutines.experimental.Job

/**
 * Created by zzy05 on 2017/8/23.
 * 此activity会主动取消掉当前UI click中使用的协程
 */
open class JobLifecycleActivity(override val job: Job = Job()) : Activity(), JobHolder {

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

}