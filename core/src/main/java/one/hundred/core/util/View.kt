package one.hundred.core.util

import android.content.Context
import android.content.res.Resources
import android.graphics.PorterDuff
import android.support.v4.content.ContextCompat
import android.graphics.drawable.Drawable
import one.hundred.core.R


/**
 * Created by zzy on 2017/8/4.
 */
object View {
    fun pxToDp(px: Float): Float {
        val densityDpi = Resources.getSystem().getDisplayMetrics().densityDpi
        return px / (densityDpi / 160f)
    }

    fun dpToPx(dp: Float): Int {
        val density = Resources.getSystem().getDisplayMetrics().density
        return Math.round(dp * density)
    }

    fun changeIconDrawableToGray(context: Context, drawable: Drawable?) {
        if (drawable != null) {
            drawable.mutate()
            drawable.setColorFilter(ContextCompat
                    .getColor(context, R.color.dark_gray), PorterDuff.Mode.SRC_ATOP)
        }
    }
}