package com.pape.adapter

import com.pape.adapter.ItemSorted
import com.pape.adapter.ItemViewHolder

/**
 * Created by zzy on 2017/8/5.
 */
interface ItemViewModel : ItemSorted {

    fun getItemViewLayoutId(): Int

    /**
     * 返回item类型，默认返回ItemViewLayoutId
     */
    fun getItemType(): Int {
        return getItemViewLayoutId()
    }

    fun bindData(holder: ItemViewHolder)

}