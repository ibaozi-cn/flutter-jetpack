package one.hundred.lifestyle.data.local

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Room
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import one.hundred.lifestyle.App
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by zzy on 2017/9/16.
 */
class DatabaseCreator(application: Application) {

    private val mIsDatabaseCreated = MutableLiveData<Boolean>()

    var database: AppDatabase? = null
        private set

    private val mInitializing = AtomicBoolean(true)

    init {
        createDb(application)
    }

    /**
     * 用于观察数据库初始化的时间
     */
    val isDatabaseCreated: LiveData<Boolean>
        get() = mIsDatabaseCreated

    /**
     * 创建或返回先前创建的数据库
     *
     * 虽然这使用了AsyncTask，它目前使用串行执行器，但是它是线程安全的.
     */
    private fun createDb(context: Context) {

        Log.d("DatabaseCreator", "Creating DB from " + Thread.currentThread().name)

        if (!mInitializing.compareAndSet(true, false)) {
            return  //已初始化
        }

        mIsDatabaseCreated.value = false// 触发更新以显示加载屏幕。
        object : AsyncTask<Context, Void, AppDatabase>() {

            override fun doInBackground(vararg params: Context): AppDatabase {
                Log.d("DatabaseCreator",
                        "Starting bg job " + Thread.currentThread().name)

                val context = params[0].applicationContext
                // 构建数据库！
                return Room.databaseBuilder(context,
                        AppDatabase::class.java, AppDatabase.DATABASE_NAME).build()
            }

            override fun onPostExecute(appDatabase: AppDatabase) {
                super.onPostExecute(appDatabase)
                database = appDatabase
                //现在在主线程上，通知观察者数据库已创建并准备就绪。
                mIsDatabaseCreated.value = true
            }
        }.execute(context.applicationContext)
    }

    companion object {

        private var sInstance: DatabaseCreator? = null

        // 对于单例实例化
        private val LOCK = Any()

        val instance: DatabaseCreator
            @Synchronized get() = getInstance(App.instance())

        @Synchronized
        private fun getInstance(application: Application): DatabaseCreator =
                sInstance ?: synchronized(LOCK) {
                    sInstance ?: DatabaseCreator(application).also {
                        sInstance = it
                    }
                }

    }
}