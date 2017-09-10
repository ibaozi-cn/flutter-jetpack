package one.hundred.core.extend


import android.support.v7.widget.RecyclerView

/**
 * 根据 itemId 找到adapter 的 position
 */
fun RecyclerView.Adapter<*>.findPositionByItemId(itemId: Long): Int {
    return (0 until itemCount).firstOrNull { getItemId(it) == itemId } ?: RecyclerView.NO_POSITION
}
