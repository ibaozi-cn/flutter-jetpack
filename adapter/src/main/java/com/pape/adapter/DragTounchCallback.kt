package com.pape.adapter

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper

open class DragTounchCallback : ItemTouchHelper.SimpleCallback {

    interface ItemDragCallback {
        fun itemTouchOnMove(oldPosition: Int, newPosition: Int): Boolean
    }

    interface ItemTouchHelperViewHolder {

        fun onItemSelected()

        fun onItemClear()
    }

    interface IDraggable<T> {

        val isDraggable: Boolean

        fun setDraggable(draggable: Boolean): T
    }

    //our callback
    private var mCallbackItemTouch: ItemDragCallback? = null // interface
    private var mIsDragEnabled = true

    private var mDirections = UP_DOWN

    constructor() : super(UP_DOWN, 0) {}

    constructor(directions: Int) : super(directions, 0) {
        this.mDirections = directions
    }

    constructor(directions: Int, itemTouchCallback: ItemDragCallback) : super(directions, 0) {
        this.mDirections = directions
        this.mCallbackItemTouch = itemTouchCallback
    }

    constructor(itemTouchCallback: ItemDragCallback) : super(UP_DOWN, 0) {
        this.mCallbackItemTouch = itemTouchCallback
    }

    fun setIsDragEnabled(mIsDragEnabled: Boolean) {
        this.mIsDragEnabled = mIsDragEnabled
    }

    override fun isLongPressDragEnabled(): Boolean {
        return mIsDragEnabled
    }

    override fun onMove(recyclerView: RecyclerView, source: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        if (mCallbackItemTouch == null) {
            val adapter = recyclerView.adapter
            if (adapter is ItemDragCallback) {
                val itemAdapter = adapter as ItemDragCallback
                itemAdapter.itemTouchOnMove(source.adapterPosition, target.adapterPosition)
                return true
            }
            throw RuntimeException("DragTounchCallback without an callback is only allowed when using the DragAdapter")
        }
        return mCallbackItemTouch!!.itemTouchOnMove(source.adapterPosition, target.adapterPosition) // information to the interface
    }

    override fun getDragDirs(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int {
        return if (viewHolder!!.itemView.tag is IDraggable<*>) {
            if ((viewHolder.itemView.tag as IDraggable<*>).isDraggable) {
                super.getDragDirs(recyclerView, viewHolder)
            } else {
                0
            }
        } else {
            mDirections
        }
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        // swiped disabled
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        // We only want the active item to change
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (viewHolder is ItemTouchHelperViewHolder) {
                // Let the view holder know that this item is being moved or dragged
                val itemViewHolder = viewHolder as ItemTouchHelperViewHolder?
                itemViewHolder!!.onItemSelected()
            }
        }

        super.onSelectedChanged(viewHolder, actionState)
    }

    override fun clearView(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)

        viewHolder.itemView.alpha = 1f

        if (viewHolder is ItemTouchHelperViewHolder) {
            // Tell the view holder it's time to restore the idle state
            val itemViewHolder = viewHolder as ItemTouchHelperViewHolder
            itemViewHolder.onItemClear()
        }
    }

    //获取拖动
    override fun chooseDropTarget(selected: RecyclerView.ViewHolder, dropTargets: List<RecyclerView.ViewHolder>, curX: Int, curY: Int): RecyclerView.ViewHolder {
        return super.chooseDropTarget(selected, dropTargets, curX, curY)
    }

    //返回值作为用户视为拖动的距离
    override fun getMoveThreshold(viewHolder: RecyclerView.ViewHolder?): Float {
        return super.getMoveThreshold(viewHolder)
    }

    //返回值滑动消失的距离，滑动小于这个值不消失，大于消失
    override fun getSwipeEscapeVelocity(defaultValue: Float): Float {
        return super.getSwipeEscapeVelocity(defaultValue)
    }

    //设置手指离开后ViewHolder的动画时间
    override fun getAnimationDuration(recyclerView: RecyclerView, animationType: Int, animateDx: Float, animateDy: Float): Long {
        return super.getAnimationDuration(recyclerView, animationType, animateDx, animateDy)
    }

    //返回值滑动消失的距离, 这里是相对于RecycleView的宽度，0.5f表示为RecycleView的宽度的一半，取值为0~1f之间
    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder?): Float {
        return super.getSwipeThreshold(viewHolder)
    }

    //返回值作为滑动的流程程度，越小越难滑动，越大越好滑动
    override fun getSwipeVelocityThreshold(defaultValue: Float): Float {
        return 0.5f
    }

    //当用户拖动一个视图出界的ItemTouchHelper调用
    override fun interpolateOutOfBoundsScroll(recyclerView: RecyclerView, viewSize: Int, viewSizeOutOfBounds: Int, totalSize: Int, msSinceStartScroll: Long): Int {
        return super.interpolateOutOfBoundsScroll(recyclerView, viewSize, viewSizeOutOfBounds, totalSize, msSinceStartScroll)
    }

    companion object {
        val ALL = ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        val UP_DOWN = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val LEFT_RIGHT = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    }
}