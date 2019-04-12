package com.example.julive.wechathelper

import android.content.Context
import android.accessibilityservice.AccessibilityServiceInfo
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ComponentName
import android.view.accessibility.AccessibilityManager
import android.content.Intent
import android.provider.Settings
import android.widget.Toast
import java.lang.Exception
import android.support.v4.content.ContextCompat.getSystemService
import android.app.KeyguardManager
import android.os.PowerManager


/**
 * 检测辅助服务是否可用
 */
fun Context.checkAccessibilityEnabled(serviceName: String): Boolean {
    val am = getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
    val accessibilityServices =
        am.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_GENERIC)
    for (info in accessibilityServices) {
        if (info.id == serviceName) {
            return true
        }
    }
    return false
}

/**
 * 前往开启辅助服务界面
 */
fun Context.openAccessSetting() {
    try {
        val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

/**
 * 打开微信扫一扫
 */
fun Activity.openWechatScan() {
    try {
        val intent = Intent()
        intent.component = ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI")
        intent.putExtra("LauncherUI.From.Scaner.Shortcut", true)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.action = "android.intent.action.VIEW"
        startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(this, "请安装微信", Toast.LENGTH_LONG).show()
    }
}

fun Activity.openWechat() {
    val WECHAT_PACKAGE_NAME = "com.tencent.mm"
    val UI_LUANCHER = "$WECHAT_PACKAGE_NAME.ui.LauncherUI"
    try {
        val intent = Intent()
        val cmp = ComponentName(WECHAT_PACKAGE_NAME, UI_LUANCHER)
        intent.action = Intent.ACTION_MAIN
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.component = cmp
        startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(this, "请安装微信", Toast.LENGTH_LONG).show()
    }
}

@SuppressLint("InvalidWakeLockTag", "MissingPermission")
fun Context.wakeAndUnlock() {
    val kl: KeyguardManager.KeyguardLock
    //获取电源管理器对象
    val pm = getSystemService(Context.POWER_SERVICE) as PowerManager?

    //获取PowerManager.WakeLock对象，后面的参数|表示同时传入两个值，最后的是调试用的Tag
    val wl = pm?.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP or PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "bright")

    //点亮屏幕
    wl?.acquire(1000)

    //得到键盘锁管理器对象
    val km = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager?
    kl = km!!.newKeyguardLock("unLock")

    //解锁
    kl.disableKeyguard()

}
