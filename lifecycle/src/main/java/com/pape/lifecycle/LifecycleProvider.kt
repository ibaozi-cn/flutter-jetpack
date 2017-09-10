package com.pape.lifecycle

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.support.v7.app.AppCompatDelegate
import com.pape.lifecycle.LifecycleDispatcher

/**
 * 通过这个小技巧 免去 在application 中初始化的操作
 *
 * 主要是为了调用 LifecycleDispatcher.logInit(context) 函数 注册 ActivityLifecycleCallbacks回掉监听
 */
class LifecycleProvider : ContentProvider() {

    override fun onCreate(): Boolean {
        LifecycleDispatcher.init(context)
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_AUTO)
        return true
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? {
        return ""
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return Uri.EMPTY
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?,
                       selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        return null
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?,
                        selectionArgs: Array<String>?): Int {
        return 0
    }
}
