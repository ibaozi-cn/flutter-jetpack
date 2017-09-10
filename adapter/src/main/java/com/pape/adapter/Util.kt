package com.pape.adapter

import java.lang.reflect.ParameterizedType

/**
 * Created by zzy05 on 2017/8/30.
 */
/**
 * 获取ViewHolder类型
 */
fun <VH> viewHolderType(javaClass: Class<*>): Class<out VH> {
    return (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<out VH>
}