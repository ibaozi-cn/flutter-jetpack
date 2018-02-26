package one.hundred.experimental

import UI
import kotlinx.coroutines.experimental.*


/**
 * Created by zzy on 2017/8/21.
 *
 * 协程上下文：
 * 1. coroutineContext--------工作在主线程中 特点: 会让子协程跟随父协程cancel而取消
 * 2. CommonPool--------工作在子线程中 特点: 不会根随父协程cancel而取消
 * 3. Unconfined--------跟随当前线程 特点： 如果在主线程中执行调用delay以后便会切换到子线程kotlinx.coroutines.DefaultExecutor中执行
 * 4. newSingleThreadContext("MyOwnThread")---------自定义线程"MyOwnThread"
 *
 * 协程特点：
 * 1. 同一线程可以有多个协程
 * 2. 同一协程可以运行在不同上下文中 通过runBlocking 包子协程 run 实现 ： runBlocking(ctx1){ run(ctx2){ }  }
 */
// ---------------------------------------------------------------------------------------------------------------------------
/**
 * 根据当前处理器个数，并获得合理的线程池大小，作为异步协程的上下文
 */
val ThreadPool = newFixedThreadPoolContext(Runtime.getRuntime().availableProcessors() * 2, "ThreadPool")

/**
 * 在主线程中顺序执行，属于顶级协程函数，一般用于最外层
 *
 * 注意：该函数会阻塞代码继续执行
 */
fun taskBlockOnMainThread(delayTime: Long = 0, job: suspend () -> Unit) = runBlocking {
    delay(delayTime)
    job()
}

/**
 * 在工作线程中顺序执行，属于顶级协程函数，一般用于最外层
 *
 * 注意：该函数会阻塞代码继续执行
 */
fun taskBlockOnWorkThread(delayTime: Long = 0, job: suspend () -> Unit) = runBlocking(ThreadPool) {
    delay(delayTime)
    job()
}

/**
 * 并发执行，常用于最外层
 * 特点带返回值
 */
fun <T> taskAsync(delayTime: Long = 0, job: suspend () -> T) = async(ThreadPool) {
    delay(delayTime)
    job()
}

/**
 * 并发执行，常用于最外层
 * 特点不带返回值
 */
fun <T> taskLaunch(delayTime: Long = 0, job: suspend () -> T) = launch(ThreadPool) {
    delay(delayTime)
    job()
}

/**
 * 在Android UI线程中执行，可以用于最外层
 * 此方法用于协程上下文调度，目前主要用于切换到android UI线程
 * 参数添加CoroutineStart.UNDISPATCHED的话表示立即执行
 */
fun <T> taskOnUiThread(delayTime: Long = 0,  job:suspend () -> T): Deferred<T> = async(UI) {
    delay(delayTime)
    job()
}

/**
 * 顺序执行函数，不能用于最外层
 */
suspend inline fun <T> taskOrder(delayTime: Long = 0, crossinline job: () -> T) {
    delay(delayTime)
    job()
}

/**
 * 心跳执行 默认重复次数1次，不能用于最外层
 */
suspend inline fun <T> taskHeartbeat(times: Int = 1, delayTime: Long = 0, crossinline job: () -> T) = repeat(times) {
    delay(delayTime)
    job()
}

