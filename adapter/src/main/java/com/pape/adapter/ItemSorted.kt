package com.pape.adapter

import java.util.*

/**
 * Created by zzy on 2017/8/5.
 */
interface ItemSorted {
    /**
     * 排序ID
     * 只有在有序的时候生效
     * 适配器默认是无序的
     */
    fun getSortedId(): Long {
        return 0
    }

    /**
     * 利用该返回值控制数据在列表中的唯一性
     * 默认为每一项添加不同的ID
     */
    fun getItemUUID(): String {
        return UUID.randomUUID().toString()
    }

}