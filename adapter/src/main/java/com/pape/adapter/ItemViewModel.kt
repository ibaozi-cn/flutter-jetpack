package com.pape.adapter

import android.view.View
import java.util.*

/**
 * Created by zzy on 2017/8/5.
 */
abstract class ItemViewModel(var sortId: Long = 0, var uuid: String = UUID.randomUUID().toString()) : ItemSorted {

    abstract fun getItemViewLayoutId(): Int
    /**
     * 返回item类型，默认返回ItemViewLayoutId
     */
    fun getItemType(): Int = getItemViewLayoutId()

    fun getItemView(): View? = null

    abstract fun bindData(holder: ItemViewHolder)

    override fun getItemUUID(): String {
        return uuid
    }

    override fun getSortedId(): Long {
        return sortId
    }
}