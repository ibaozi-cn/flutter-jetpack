package one.hundred.core.log

import one.hundred.core.BuildConfig
import timber.log.Timber

/**
 * Created by zzy on 2017/8/4.
 */
fun logInit() {
    if (BuildConfig.DEBUG) {
        Timber.plant(Timber.DebugTree())
    }
}

fun logD(s: String, vararg objects: Any) {
    Timber.d(s, *objects)
}

fun logD(throwable: Throwable, s: String, vararg objects: Any) {
    Timber.d(throwable, s, *objects)
}

fun logI(s: String, vararg objects: Any) {
    Timber.i(s, *objects)
}

fun logI(throwable: Throwable, s: String, vararg objects: Any) {
    Timber.i(throwable, s, *objects)
}

fun logW(s: String, vararg objects: Any) {
    Timber.w(s, *objects)
}

fun logW(throwable: Throwable, s: String, vararg objects: Any) {
    Timber.w(throwable, s, *objects)
}

fun logE(s: String, vararg objects: Any) {
    Timber.e(s, *objects)
}

fun logE(throwable: Throwable, s: String, vararg objects: Any) {
    Timber.e(throwable, s, *objects)
}
