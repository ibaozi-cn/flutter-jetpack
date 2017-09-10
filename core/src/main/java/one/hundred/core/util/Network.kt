package one.hundred.core.util

import android.content.Context
import android.net.ConnectivityManager



/**
 * Created by zzy on 2017/8/4.
 */
object Network {
    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}