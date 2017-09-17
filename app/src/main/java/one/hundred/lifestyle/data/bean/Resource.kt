package one.hundred.lifestyle.data.bean


/**
 * Created by zzy on 2017/9/16.
 */
class Resource<T> private constructor(val status: Status, val data: T, val message: String) {

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, "")
        }

        fun <T> error(msg: String, data: T): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T): Resource<T> {
            return Resource(Status.LOADING, data, "")
        }
    }

    enum class Status {
        SUCCESS, ERROR, LOADING
    }
}
