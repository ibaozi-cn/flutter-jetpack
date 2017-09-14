package com.pape.adapter

import android.view.View

/**
 * Created by zzy on 2017/8/5.
 */
interface ItemViewModel : ItemSorted {

    fun getItemViewLayoutId(): Int
    /**
     * 返回item类型，默认返回ItemViewLayoutId
     */
    fun getItemType(): Int = getItemViewLayoutId()

    fun getItemView(): View? = null

    fun bindData(holder: ItemViewHolder)

}