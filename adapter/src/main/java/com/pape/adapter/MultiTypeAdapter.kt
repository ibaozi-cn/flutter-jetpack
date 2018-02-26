package com.pape.adapter

import android.support.v7.util.SortedList
import android.support.v7.util.SortedList.INVALID_POSITION
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by zzy on 2017/8/5.
 */
class MultiTypeAdapter(val adapterSequence: AdapterSequence = AdapterSequence.NOSC,
                       var onClickListener: ((view: View, position: Int) -> Unit)? = null,
                       var onLongClickListener: ((view: View, position: Int) -> Unit)? = null
) : RecyclerView.Adapter<ItemViewHolder>() {

    private val typeArray: SparseArray<ItemViewModel> = SparseArray()

    val sortedItemList: SortedList<ItemViewModel> by lazy {
        SortedList<ItemViewModel>(ItemViewModel::class.java, SortListCallBack(this, adapterSequence))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val item = getTypeItem(viewType)
        val view = item.getItemView()
        val holder = ItemViewHolder(view
                ?: LayoutInflater.from(parent.context).inflate(item.getItemViewLayoutId(), parent, false))
        holder.itemView.setOnClickListener { v ->
            onClickListener?.invoke(v, holder.adapterPosition)
        }
        holder.itemView.setOnLongClickListener { v ->
            onLongClickListener?.invoke(v, holder.adapterPosition)
            true
        }
        return holder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        item?.bindData(holder)
    }

    override fun getItemCount(): Int {
        return sortedItemList.size()
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position)?.getItemType()!!
    }

    private fun addItemType(item: ItemViewModel) {
        typeArray.append(item.getItemType(), item)
    }

    private fun getTypeItem(type: Int): ItemViewModel {
        return typeArray.get(type)
    }

    fun addOrUpdateItem(item: ItemViewModel) {
        addItemType(item)
        findItemByUUID(item.uuid, {
            if (it != null) {
                sortedItemList.updateItemAt(sortedItemList.indexOf(it), item)
            } else {
                sortedItemList.add(item)
            }
        })
    }

    fun addOrUpdateItems(vararg item: ItemViewModel) {
        sortedItemList.beginBatchedUpdates()
        item.forEach {
            addOrUpdateItem(it)
        }
        sortedItemList.endBatchedUpdates()
    }

    fun addOrUpdateListItem(itemList: List<ItemViewModel>) {
        itemList.forEach {
            addItemType(it)
        }
        sortedItemList.beginBatchedUpdates()
        itemList.forEach {
            addOrUpdateItem(it)
        }
        sortedItemList.endBatchedUpdates()
    }

    fun removeItem(item: ItemViewModel) {
        findItemByUUID(item.uuid, {
            if (it != null)
                sortedItemList.remove(it)
        })
    }

    fun removeItems(vararg item: ItemViewModel) {
        sortedItemList.beginBatchedUpdates()
        item.forEach {
            removeItem(it)
        }
        sortedItemList.endBatchedUpdates()
    }

    fun removeAll() {
        sortedItemList.clear()
        typeArray.clear()
    }

    fun replaceItem(item: ItemViewModel) {
        findItemByUUID(item.uuid, {
            if (it != null) {
                val index = sortedItemList.indexOf(it)
                if (index != INVALID_POSITION)
                    sortedItemList.updateItemAt(index, item)
            }
        })
    }

    fun move(from: Int, to: Int) {
        sortedItemList.beginBatchedUpdates()
        sortedItemList.recalculatePositionOfItemAt(from)
        sortedItemList.recalculatePositionOfItemAt(to)
        sortedItemList.endBatchedUpdates()
    }

    fun getItem(index: Int): ItemViewModel? {
        return sortedItemList.get(index)
    }

    fun contains(item: ItemViewModel): Boolean {
        return sortedItemList.indexOf(item) > -1
    }

    fun getAllItems(): SortedList<ItemViewModel> {
        return sortedItemList
    }

    private fun findItemByUUID(itemUUID: String, itemCallback: (ItemViewModel?) -> Unit) {
        (0 until sortedItemList.size()).forEach { i ->
            val item = sortedItemList[i]
            if (itemUUID == item.getItemUUID()) {
                itemCallback(item)
            } else {
                itemCallback(null)
            }
        }
        if (sortedItemList.size() < 1) {
            itemCallback(null)
        }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView?) {
        removeAll()
        super.onDetachedFromRecyclerView(recyclerView)
    }
}