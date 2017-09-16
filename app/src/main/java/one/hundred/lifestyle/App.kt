package one.hundred.lifestyle

import android.app.Application
import one.hundred.core.log.logD
import one.hundred.core.log.logInit
import one.hundred.experimental.taskLaunch
import one.hundred.lifestyle.data.local.DatabaseCreator
import one.hundred.lifestyle.data.local.DbDataUtil

/**
 * Created by zzy on 2017/9/16.
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        sInstance = this
        logInit(BuildConfig.DEBUG)
        /**
         * 监听数据库是否创建成功，创建成功后插入测试数据
         */
        DatabaseCreator.instance.isDatabaseCreated.observeForever {
            if (it != null) {
                if (it) {
                    logD("DatabaseCreator saveTestData")
                    taskLaunch {
                        DatabaseCreator.instance.database?.getDbTestDao()
                                ?.saveData(DbDataUtil.getTestDataList())
                    }
                }
            }
        }
    }

    companion object {
        private var sInstance: App? = null
        fun instance(): App =
                sInstance ?: synchronized(App::class.java) {
                    sInstance ?: App().also {
                        sInstance = it
                    }
                }
    }
}