package com.pape.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView.ViewHolder
import android.util.SparseArray
import android.view.View

/**
 * Created by zzy on 2017/8/5.
 */
open class ItemViewHolder(itemView: View) : ViewHolder(itemView) {

    val context: Context = itemView.context

    private val mViews: SparseArray<View> by lazy {
        SparseArray<View>()
    }

    @Suppress("UNCHECKED_CAST") /**
     * 通过viewId获取控件
     * @param viewId
     * @return
     */
    fun <T : View> getView(viewId: Int): T {
        var view: View? = mViews.get(viewId)
        if (view == null) {
            view = itemView.findViewById(viewId)
            mViews.put(viewId, view)
        }
        return view as T
    }
}