package com.example.julive.wechathelper

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast
import android.view.accessibility.AccessibilityNodeInfo
import android.util.Log
import android.app.PendingIntent
import android.app.Notification
import android.content.Context
import android.graphics.Path
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.annotation.RequiresApi
import android.util.DisplayMetrics
import android.view.WindowManager


class WechatService : AccessibilityService() {

    private val handler = Handler()

    override fun onInterrupt() {
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        val pkgName = event?.packageName.toString()
        val eventType = event?.eventType
        val className = event?.className
        val config = FileUtil.readLogByString(logPath, "0")
        log("\nconfig $config")
        log("className==\"$className\"")
        when (eventType) {
            AccessibilityEvent.TYPE_WINDOWS_CHANGED -> {
            }
            AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED -> {
                when (config) {
                    "1" -> {
                        autoAddFriend(className)
                    }
                    "2" -> {
                        autoSendImage(className)
                    }
                    "3" -> {
//                        autoShareMiniPrograms(className)
                    }
                    "0" -> {

                    }
                }
            }
        }
    }

    private fun autoShareMiniPrograms(className: CharSequence?) {
        if (className == "com.tencent.mm.ui.LauncherUI") {
            click("发现")
            handler.postDelayed({
                click("小程序")
            }, 1000)
        }
        if (className == "com.tencent.mm.plugin.appbrand.ui.AppBrandLauncherUI") {
            if (isOneTime)
                performMenuDoubleClick {
                    click("搜索")
                    handler.postDelayed({
                        input("爆文来了")

                    }, 1000)
                }
        }
    }

    private fun autoSendImage(className: CharSequence?) {
        if (className == "com.tencent.mm.ui.LauncherUI") {
            click("发现")
            handler.postDelayed({
                click("朋友圈")
            }, 1000)
        }
        if (className == "com.tencent.mm.plugin.sns.ui.SnsTimeLineUI") {
            click("拍照分享")
            click("从相册选择")
        }
        if (className == "com.tencent.mm.plugin.gallery.ui.AlbumPreviewUI") {
            if (isOneTime)
                performMenuDoubleClick {
                    choosePicture(0, 2)
                }
        }
        if (className == "com.tencent.mm.plugin.sns.ui.SnsUploadUI") {
            click("发表")
            isOneTime = true
            resetConfig()
        }
    }

    private fun autoAddFriend(className: CharSequence?) {
        if (className == "com.tencent.mm.ui.LauncherUI") {
            click("微信")
            click("更多")
            click("添加朋友")
        }
        if (className == "com.tencent.mm.plugin.subapp.ui.pluginapp.AddMoreFriendsUI") {
            click("手机号")
        }
        if (className == "com.tencent.mm.plugin.fts.ui.FTSAddWw") {
            handler.postDelayed({
                input("13261103711")
                handler.postDelayed({
                    clickById("com.tencent.mm:id/px")
                }, 1000)
            }, 1000)
        }
        if (className == "com.tencent.mm.plugin.profile.ui.ContactInfoUI") {
            click("添加到通讯录")
        }
        if (className == "com.tencent.mm.plugin.profile.ui.SayHiWithSnsPermissionUI") {
            Toast.makeText(this, "请手动编辑，十五秒后自动点击发送", Toast.LENGTH_SHORT).show()
            resetConfig()
        }
    }

    /**
     * 关闭监控
     */
    private fun resetConfig() {
        FileUtil.writeLog(logPath, "0", false, "utf-8")
        stopSelf() //走完一次流程 关闭自己，防止一直监控
    }

    /**
     * 点击匹配的nodeInfo
     * @param str text关键字
     */
    private fun click(str: String, action: Int = AccessibilityNodeInfo.ACTION_CLICK) {
        handler.postDelayed({
            val nodeInfo = rootInActiveWindow
            if (nodeInfo == null) {
                Toast.makeText(this, "rootWindow为空", Toast.LENGTH_SHORT).show()
                return@postDelayed
            }
            val list = nodeInfo.findAccessibilityNodeInfosByText(str)
            log(list.toString())
            if (list != null && list.size > 0) {
                list[list.size - 1].performAction(action)
                list[list.size - 1].parent?.performAction(action)
            } else {
                Toast.makeText(this, "click 找不到有效的节点", Toast.LENGTH_SHORT).show()
            }
            nodeInfo.recycle()
        }, 1000)
    }

    /**
     * 点击匹配的nodeInfo
     * @param str text关键字
     */
    private fun clickById(str: String, action: Int = AccessibilityNodeInfo.ACTION_CLICK) {
        handler.postDelayed({
            val nodeInfo = rootInActiveWindow
            if (nodeInfo == null) {
                Toast.makeText(this, "rootWindow为空", Toast.LENGTH_SHORT).show()
                return@postDelayed
            }
            val list = nodeInfo.findAccessibilityNodeInfosByViewId(str)
            log(list.toString())
            if (list != null && list.size > 0) {
                list[list.size - 1].performAction(action)
                list[list.size - 1].parent?.performAction(action)
            } else {
                Toast.makeText(this, "clickById 找不到有效的节点", Toast.LENGTH_SHORT).show()
            }
            nodeInfo.recycle()
        }, 1000)
    }

    //自动输入打招呼内容
    private fun input(hello: String) {
        handler.postDelayed({
            val nodeInfo = rootInActiveWindow
            if (nodeInfo == null) {
                Toast.makeText(this, "rootWindow为空", Toast.LENGTH_SHORT).show()
                return@postDelayed
            }
            //找到当前获取焦点的view
            val target = nodeInfo.findFocus(AccessibilityNodeInfo.FOCUS_INPUT)

            if (target == null) {
                log("input: null")
                return@postDelayed
            }
            val arguments = Bundle()
            arguments.putCharSequence(
                AccessibilityNodeInfo
                    .ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, hello
            )
            target.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments)
            nodeInfo.recycle()
        }, 1000)
    }

    private fun log(config: String?) {
        Log.d("AccessibilityNodeInfo", config)
    }

    /**
     * 打开通知栏消息
     */
    private fun openNotification(event: AccessibilityEvent) {
        if (event.parcelableData == null || event.parcelableData !is Notification) {
            return
        }
        //将通知栏消息打开
        val notification = event.parcelableData as Notification
        val pendingIntent = notification.contentIntent
        try {
            pendingIntent.send()
        } catch (e: PendingIntent.CanceledException) {
            e.printStackTrace()
        }
    }

    /**
     * 点击回退按钮
     */
    private fun performBackClick() {
        handler.postDelayed({ performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK) }, 1300L)
    }

    /**
     * 回主页
     */
    private fun performHomeClick() {
        handler.postDelayed({
            performGlobalAction(AccessibilityService.GLOBAL_ACTION_HOME)
        }, 1300L)
    }

    /**
     * 点击菜单按钮
     */
    private fun performMenuClick() {
        handler.postDelayed({
            performGlobalAction(AccessibilityService.GLOBAL_ACTION_RECENTS)
        }, 1300L)
    }

    /**
     * 控制该动作只操作一次
     */
    private var isOneTime = true

    /**
     * 点击菜单按钮后一秒再点击按钮返回
     * 目的为了刷新当前页面，拿到当前页根节点
     */
    private fun performMenuDoubleClick(doubleCallBack: () -> Unit) {
        performGlobalAction(AccessibilityService.GLOBAL_ACTION_RECENTS)
        handler.postDelayed({
            performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK)
            doubleCallBack()
            isOneTime = false
        }, 1000)
    }

    /**
     * 点击选项框
     */
    private fun performClickBtn(accessibilityNodeInfoList: List<AccessibilityNodeInfo>?): Boolean {
        if (accessibilityNodeInfoList != null && accessibilityNodeInfoList.isNotEmpty()) {
            for (i in accessibilityNodeInfoList.indices) {
                val accessibilityNodeInfo = accessibilityNodeInfoList[i]
                if (accessibilityNodeInfo.isClickable && accessibilityNodeInfo.isEnabled) {
                    accessibilityNodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                    return true
                }
            }
        }
        return false
    }

    /**
     * 选择图片
     *
     * @param startPicIndex 从第startPicIndex张开始选
     * @param picCount      总共选picCount张
     */
    private fun choosePicture(startPicIndex: Int, picCount: Int) {
        handler.postDelayed({
            val accessibilityNodeInfo = rootInActiveWindow
            if (accessibilityNodeInfo == null) {
                Toast.makeText(this, "accessibilityNodeInfo is null", Toast.LENGTH_SHORT).show()
                return@postDelayed
            }
            val accessibilityNodeInfoList = accessibilityNodeInfo.findAccessibilityNodeInfosByText("预览")
            if (accessibilityNodeInfoList == null ||
                accessibilityNodeInfoList.size == 0 ||
                accessibilityNodeInfoList[0].parent == null ||
                accessibilityNodeInfoList[0].parent.childCount == 0
            ) {
                return@postDelayed
            }
            val tempInfo = accessibilityNodeInfoList[0].parent.getChild(3)

            for (j in startPicIndex until startPicIndex + picCount) {
                val childNodeInfo = tempInfo.getChild(j)
                if (childNodeInfo != null) {
                    for (k in 0 until childNodeInfo.childCount) {
                        if (childNodeInfo.getChild(k).isEnabled && childNodeInfo.getChild(k).isClickable) {
                            childNodeInfo.getChild(k).performAction(AccessibilityNodeInfo.ACTION_CLICK)//选中图片
                        }
                    }
                }
            }
            val finishList = accessibilityNodeInfo.findAccessibilityNodeInfosByText("完成($picCount/9)")//点击确定
            performClickBtn(finishList)
        }, 2000)
    }


    /**
     * 垂直滑动
     * 滑动比例 0~20
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private fun slideVertical(startSlideRatio: Int, stopSlideRatio: Int) {
        log("slideVertical")
        val screenHeight = getScreenHeight(this)
        val screenWidth = getScreenWidth(this)
        log("screenHeight $screenHeight")
        log("screenWidth $screenWidth")
        val path = Path()
        val start = screenHeight / 20 * startSlideRatio
        val stop = screenHeight / 20 * stopSlideRatio
        path.moveTo((screenWidth / 2).toFloat(), start.toFloat())//如果只是设置moveTo就是点击
        path.lineTo((screenWidth / 2).toFloat(), stop.toFloat())//如果设置这句就是滑动
        val builder = GestureDescription.Builder()
        val gestureDescription = builder
            .addStroke(
                GestureDescription.StrokeDescription(
                    path,
                    0,
                    500
                )
            )
            .build()

        dispatchGesture(gestureDescription, object : AccessibilityService.GestureResultCallback() {
            override fun onCompleted(gestureDescription: GestureDescription) {
                super.onCompleted(gestureDescription)
                log("onCompleted")
            }

            override fun onCancelled(gestureDescription: GestureDescription) {
                super.onCancelled(gestureDescription)
                log("onCancelled")
            }
        }, null)
    }

    private fun getScreenWidth(context: Context): Int {
        val wm = context
            .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        return outMetrics.widthPixels
    }

    private fun getScreenHeight(context: Context): Int {
        val wm = context
            .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        wm.defaultDisplay.getMetrics(outMetrics)
        return outMetrics.heightPixels
    }
}
