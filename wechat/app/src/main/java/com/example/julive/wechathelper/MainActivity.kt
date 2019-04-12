package com.example.julive.wechathelper

import android.Manifest
import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import pub.devrel.easypermissions.EasyPermissions

class MainActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        log("onPermissionsDenied" + perms.toString())
        hasPermissions = false
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        log("onPermissionsGranted" + perms.toString())
        hasPermissions = true
    }

    private var hasPermissions = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FileUtil.writeLog(logPath, "0", false, "utf-8")
        if (!EasyPermissions.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.WAKE_LOCK,Manifest.permission.DISABLE_KEYGUARD))
            requestPermissions()
        else{
            hasPermissions = true
            Handler().postDelayed({
                wakeAndUnlock()
            },3000)
        }
    }

    fun autoAddFriend(view: View) {
        if (requestPermissions()) return
        FileUtil.writeLog(logPath, "1", false, "utf-8")
        openWechat()
    }

    private fun requestPermissions(): Boolean {
        if (!hasPermissions) {
            EasyPermissions.requestPermissions(this, "需要读写内存卡权限，请允许才能继续", 100, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.WAKE_LOCK,Manifest.permission.DISABLE_KEYGUARD)
            return true
        }
        return false
    }

    fun autoSendImage(view: View) {
        if (requestPermissions()) return
        FileUtil.writeLog(logPath, "2", false, "utf-8")
        openWechat()
    }

    @SuppressLint("WrongConstant")
    fun autoShareMiniPrograms(view: View) {
        if (requestPermissions()) return
        FileUtil.writeLog(logPath, "3", false, "utf-8")
        openWechat()
    }

    fun autoPullFriend(view: View) {
        if (requestPermissions()) return
        FileUtil.writeLog(logPath, "4", false, "utf-8")
        openWechat()
    }

    fun autoPushFriend(view: View) {
        if (requestPermissions()) return
        FileUtil.writeLog(logPath, "5", false, "utf-8")
        openWechat()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
