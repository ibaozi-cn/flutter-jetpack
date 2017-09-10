package one.hundred.experimental

import java.lang.ref.WeakReference
import java.util.concurrent.CancellationException
import kotlin.coroutines.experimental.intrinsics.suspendCoroutineOrReturn

/**
 * Created by zzy05 on 2017/8/26.
 * 弱引用实现类，用来实例化WeakReference
 *
 */
class WeakRef<T> internal constructor(any: T) {

    private val weakRef = WeakReference(any)

    suspend operator fun invoke(): T {
        //获取suspend函数中的当前实例，并暂停当前正在运行的协程或立即返回结果，而不会暂停
        return suspendCoroutineOrReturn {
            val ref = weakRef.get() ?: throw CancellationException()
            ref
        }
    }
}

/**
 * 给任何类型扩展函数weakReference() ，实例化WeakRef类并传入当前对象
 */
fun <T : Any> T.weakReference() = WeakRef(this)