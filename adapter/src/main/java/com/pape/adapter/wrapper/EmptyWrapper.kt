package com.pape.adapter.wrapper

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.pape.adapter.ItemViewHolder


/**
 * Created by zhy on 16/6/23.
 */
class EmptyWrapper(private val mInnerAdapter: RecyclerView.Adapter<ItemViewHolder>) : RecyclerView.Adapter<ItemViewHolder>() {

    private var mEmptyView: View? = null
    private var mEmptyLayoutId: Int = 0

    private val isEmpty: Boolean
        get() = (mEmptyView != null || mEmptyLayoutId != 0) && mInnerAdapter.itemCount == 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        if (isEmpty) {
            return if (mEmptyView != null) {
                ItemViewHolder(mEmptyView!!)
            } else {
                ItemViewHolder(LayoutInflater.from(parent.context).inflate(mEmptyLayoutId, parent, false))
            }
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        WrapperUtils.onAttachedToRecyclerView(mInnerAdapter, recyclerView, getSpanSize = { gridLayoutManager, oldLookup, position ->
            if (isEmpty) {
                gridLayoutManager.spanCount
            } else {
                oldLookup.getSpanSize(position)
            }
        })
    }

    override fun onViewAttachedToWindow(holder: ItemViewHolder) {
        mInnerAdapter.onViewAttachedToWindow(holder)
        if (isEmpty) {
            WrapperUtils.setFullSpan(holder)
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (isEmpty) {
            ITEM_TYPE_EMPTY
        } else mInnerAdapter.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        if (isEmpty) {
            return
        }
        mInnerAdapter.onBindViewHolder(holder, position)
    }

    override fun getItemCount(): Int {
        return if (isEmpty) 1 else mInnerAdapter.itemCount
    }


    fun setEmptyView(emptyView: View) {
        mEmptyView = emptyView
    }

    fun setEmptyView(layoutId: Int) {
        mEmptyLayoutId = layoutId
    }

    companion object {
        val ITEM_TYPE_EMPTY = Integer.MAX_VALUE - 1
    }

}
