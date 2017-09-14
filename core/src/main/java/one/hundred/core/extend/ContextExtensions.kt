package one.hundred.core.extend

import android.content.BroadcastReceiver
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat

fun Context.checkAllSelfPermissionsGranted(vararg permissions: String): Boolean {
    return permissions.none { ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED }
}

fun Context.checkAnySelfPermissionsGranted(vararg permissions: String): Boolean {
    return permissions.any { ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED }
}

fun Context.unregisterReceiverSafe(receiver: BroadcastReceiver?): Boolean {
    if (receiver == null) return false
    return try {
        unregisterReceiver(receiver)
        true
    } catch (e: IllegalArgumentException) {
        false
    }
}
