package one.hundred.core.extend

import android.content.Context
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Toast
import org.jetbrains.anko.runOnUiThread

/**
 * @project: MarketingDemo
 * @description: //Activity Fragment通知扩展
 * @author zzy05
 * @date 2017/6/22 16:20
 * @updateUser zzy05
 * @update 2017/6/22 16:20
 * @version V1.0
 */
fun showSnackBar(view: View, content: String, action: String? = "", onClickListener: View.OnClickListener? = null, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(view, content, duration).setAction(action, onClickListener).show()
}

