package one.hundred.core.extend

import android.animation.Animator
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import com.bumptech.glide.Glide

val View.ctx: Context
    get() = context

var TextView.textColor: Int
    get() = currentTextColor
    set(v) = setTextColor(v)

fun View.slideExit() {
    if (translationY == 0f) animate().translationY(-height.toFloat())
}

fun View.slideEnter() {
    if (translationY < 0f) animate().translationY(0f)
}

fun ImageView.loadImageFromUrl(url: String) {
    Glide.with(context).load(url).into(this)
}

fun ImageView.loadImageFromUrl(resourceId: Int) {
    Glide.with(context).load(resourceId)
            .into(this)
}

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
fun View.animCircularVisible(startPoint: () -> Int = { 0 }) {
    LOLLIPOP {
        val cx = width / 2
        val cy = height / 2

        // get the final radius for the clipping circle
        val finalRadius = Math.max(width, height).toFloat()

        visibility = View.VISIBLE

        post {
            val anim = ViewAnimationUtils.createCircularReveal(this, cx, cy, startPoint().toFloat(), finalRadius)
            anim.duration = 2000
            anim.start()
        }
    }
}

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
fun View.animCircularGone(entPoint: () -> Int = { 0 }) {
    LOLLIPOP {
        val cx = width / 2
        val cy = height / 2

        val initialRadius = width.toFloat()

        post {
            val anim = ViewAnimationUtils.createCircularReveal(this, cx, cy, initialRadius, entPoint().toFloat())
            anim.addListener(AnimatorListenerAdapter(this))
            anim.duration = 2000
            anim.start()
        }
    }
}

private class AnimatorListenerAdapter(val view: View) : Animator.AnimatorListener {

    override fun onAnimationRepeat(p0: Animator?) {
    }

    override fun onAnimationEnd(p0: Animator?) {
        view.visibility = View.GONE
        p0?.removeListener(this)
    }

    override fun onAnimationCancel(p0: Animator?) {
    }

    override fun onAnimationStart(p0: Animator?) {
    }

}

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
inline fun LOLLIPOP(inL: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        inL()
    }
}


fun Spinner.setOnItemSelectedListener(selected: (view: View?, position: Int) -> Unit) {
    onItemSelectedListener = onItemSelected(selected)
}

class onItemSelected(val selected: (view: View?, position: Int) -> Unit) : AdapterView.OnItemSelectedListener {

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        selected(view, position)
    }
}