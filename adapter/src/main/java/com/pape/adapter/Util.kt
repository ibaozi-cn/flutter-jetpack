package com.pape.adapter

import java.lang.reflect.ParameterizedType

/**
 * Created by zzy05 on 2017/8/30.
 */
/**
 * 获取ViewHolder类型
 */
fun viewHolderType(javaClass: Class<*>): Class<*> {
    return (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<*>
}