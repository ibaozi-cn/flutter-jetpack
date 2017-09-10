package com.pape.adapter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper

class SwipeDragTounchCallback @JvmOverloads constructor(itemTouchCallback: DragTounchCallback.ItemDragCallback, itemSwipeCallback: SwipeTounchCallback.ItemSwipeCallback, leaveBehindDrawable: Drawable, swipeDirs: Int = ItemTouchHelper.LEFT, @ColorInt bgColor: Int = Color.RED) : DragTounchCallback(itemTouchCallback) {

    private val simpleSwipeCallback: SwipeTounchCallback

    init {
        setDefaultSwipeDirs(swipeDirs)
        simpleSwipeCallback = SwipeTounchCallback(itemSwipeCallback, leaveBehindDrawable, swipeDirs, bgColor)
    }

    fun withLeaveBehindSwipeLeft(d: Drawable): SwipeDragTounchCallback {
        setDefaultSwipeDirs(getSwipeDirs(null, null) or ItemTouchHelper.LEFT)
        simpleSwipeCallback.withLeaveBehindSwipeLeft(d)
        return this
    }

    fun withLeaveBehindSwipeRight(d: Drawable): SwipeDragTounchCallback {
        setDefaultSwipeDirs(getSwipeDirs(null, null) or ItemTouchHelper.RIGHT)
        simpleSwipeCallback.withLeaveBehindSwipeRight(d)
        return this
    }

    fun withHorizontalMarginDp(ctx: Context, dp: Int): SwipeDragTounchCallback {
        simpleSwipeCallback.withHorizontalMarginDp(ctx, dp)
        return this
    }

    fun withHorizontalMarginPx(px: Int): SwipeDragTounchCallback {
        simpleSwipeCallback.withHorizontalMarginPx(px)
        return this
    }

    fun withBackgroundSwipeLeft(@ColorInt bgColor: Int): SwipeDragTounchCallback {
        simpleSwipeCallback.withBackgroundSwipeLeft(bgColor)
        return this
    }

    fun withBackgroundSwipeRight(@ColorInt bgColor: Int): SwipeDragTounchCallback {
        simpleSwipeCallback.withBackgroundSwipeRight(bgColor)
        return this
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        simpleSwipeCallback.onSwiped(viewHolder, direction)
    }

    override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        simpleSwipeCallback.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        //Happen to know that our direct parent class doesn't (currently) draw anything...
        //super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}
