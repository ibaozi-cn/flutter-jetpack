package one.hundred.core.delegate

import kotlin.reflect.KProperty

/**
 * @project: KotlinMvpDemo
 * @description: //对单例的非空检查
 * @author zzy05 zhangzhanyong@feinno.com
 * @date 2017/6/23 17:45
 * @updateUser zzy05
 * @update 2017/6/23 17:45
 * @version V1.0
 */
class NotNullSingleValueVar<T> {

    private var value: T? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value ?: throw IllegalStateException("${property.name} not initialized")
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = if (this.value == null) value
        else throw IllegalStateException("${property.name} already initialized")
    }
}