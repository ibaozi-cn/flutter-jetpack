package com.pape.adapter

import android.support.v7.util.SortedList
import android.support.v7.widget.RecyclerView

/**
 * Created by zzy on 2017/8/5.
 */
class SortListCallBack<T : ItemSorted>(
        val adapter: RecyclerView.Adapter<ItemViewHolder>,
        private val adapterSequence: AdapterSequence = AdapterSequence.NOSC)
    : SortedList.Callback<T>() {


    override fun onChanged(position: Int, count: Int) {
        adapter.notifyItemRangeChanged(position, count)
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return false
    }

    override fun onRemoved(position: Int, count: Int) {
        adapter.notifyItemRangeRemoved(position, count)
    }

    override fun compare(o1: T, o2: T): Int {
        if (adapterSequence == AdapterSequence.NOSC) return 0
        if (o1.getSortedId() < o2.getSortedId()) {
            return if (adapterSequence == AdapterSequence.ASC) -1
            else 1
        } else if (o1.getSortedId() > o2.getSortedId()) {
            return if (adapterSequence == AdapterSequence.ASC) 1
            else -1
        }
        return 0
    }

    override fun onInserted(position: Int, count: Int) {
        adapter.notifyItemRangeInserted(position, count)
    }

    override fun onMoved(fromPosition: Int, toPosition: Int) {
        adapter.notifyItemMoved(fromPosition, toPosition)
    }

    override fun areItemsTheSame(item1: T, item2: T): Boolean {
        return item1.getItemUUID() == item2.getItemUUID()
    }
}