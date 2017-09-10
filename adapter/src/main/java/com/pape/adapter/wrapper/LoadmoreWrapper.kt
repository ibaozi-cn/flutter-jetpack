package com.pape.adapter.wrapper

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.pape.adapter.ItemViewHolder


/**
 * Created by zhy on 16/6/23.
 */
class LoadmoreWrapper<T>(private val mInnerAdapter: RecyclerView.Adapter<ItemViewHolder>) : RecyclerView.Adapter<ItemViewHolder>() {

    private var mLoadMoreView: View? = null
    private var mLoadMoreLayoutId: Int = 0

    private fun hasLoadMore(): Boolean {
        return mLoadMoreView != null || mLoadMoreLayoutId != 0
    }


    private fun isShowLoadMore(position: Int): Boolean {
        return hasLoadMore() && position >= mInnerAdapter.itemCount
    }

    override fun getItemViewType(position: Int): Int {
        return if (isShowLoadMore(position)) {
            ITEM_TYPE_LOAD_MORE
        } else mInnerAdapter.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        if (viewType == ITEM_TYPE_LOAD_MORE) {
            val holder: ItemViewHolder
            if (mLoadMoreView != null) {
                holder = ItemViewHolder(mLoadMoreView!!)
            } else {
                holder = ItemViewHolder(LayoutInflater.from(parent.context).inflate(mLoadMoreLayoutId, parent, false))
            }
            return holder
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        if (isShowLoadMore(position)) {
            if (mOnLoadMoreListener != null) {
                mOnLoadMoreListener!!.onLoadMoreRequested()
            }
            return
        }
        mInnerAdapter.onBindViewHolder(holder, position)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        WrapperUtils.onAttachedToRecyclerView(mInnerAdapter, recyclerView, { layoutManager, oldLookup, position ->
            if (isShowLoadMore(position)) {
                layoutManager.spanCount
            } else {
                oldLookup?.getSpanSize(position) ?: 1
            }
        })
    }


    override fun onViewAttachedToWindow(holder: ItemViewHolder?) {
        mInnerAdapter.onViewAttachedToWindow(holder)

        if (isShowLoadMore(holder!!.layoutPosition)) {
            setFullSpan(holder)
        }
    }

    private fun setFullSpan(holder: ItemViewHolder) {
        val lp = holder.itemView.layoutParams

        if (lp != null && lp is StaggeredGridLayoutManager.LayoutParams) {

            lp.isFullSpan = true
        }
    }

    override fun getItemCount(): Int {
        return mInnerAdapter.itemCount + if (hasLoadMore()) 1 else 0
    }


    interface OnLoadMoreListener {
        fun onLoadMoreRequested()
    }

    private var mOnLoadMoreListener: OnLoadMoreListener? = null

    fun setOnLoadMoreListener(loadMoreListener: OnLoadMoreListener?): LoadmoreWrapper<*> {
        if (loadMoreListener != null) {
            mOnLoadMoreListener = loadMoreListener
        }
        return this
    }

    fun setLoadMoreView(loadMoreView: View): LoadmoreWrapper<*> {
        mLoadMoreView = loadMoreView
        return this
    }

    fun setLoadMoreView(layoutId: Int): LoadmoreWrapper<*> {
        mLoadMoreLayoutId = layoutId
        return this
    }

    companion object {
        val ITEM_TYPE_LOAD_MORE = Integer.MAX_VALUE - 2
    }
}
