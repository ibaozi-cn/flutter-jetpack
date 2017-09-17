package one.hundred.lifestyle.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.support.v7.app.AppCompatActivity
import one.hundred.core.log.logD
import one.hundred.core.log.logE
import one.hundred.core.net.ServiceFactory
import one.hundred.experimental.taskLaunch
import one.hundred.lifestyle.data.bean.Test
import one.hundred.lifestyle.data.local.DatabaseCreator
import one.hundred.lifestyle.data.local.DbTest
import one.hundred.lifestyle.data.server.ApiService
import one.hundred.lifestyle.data.server.RemoteCallback


/**
 * Created by zzy on 2017/9/16.
 */
class TestRepository(private val context: AppCompatActivity) : Repository {

    private val apiService: ApiService = ServiceFactory.createRetrofitService("http://www.google.com", ApiService::class.java, context)
    private val dao = DatabaseCreator.instance.database?.getDbTestDao()

    /**
     * 获取数据库测试数据
     */
    fun getDbTest(id: Long): LiveData<DbTest> {
        return dao!!.getData(id)
    }

    /**
     * 获取网络测试数据
     */
    fun getServerTestList(): LiveData<List<Test>> {

        val serverData = MutableLiveData<List<Test>>()

        apiService.getList(0).enqueue(object : RemoteCallback<List<Test>>() {

            override fun onSuccess(response: List<Test>) {
                serverData.value = response
            }

            override fun onUnauthorized() {
                logD("未授权")
            }

            override fun onFailed(throwable: Throwable) {
                logE(throwable.localizedMessage)
            }

        })
        return serverData
    }

    /**
     * 刷新测试数据
     */
    fun refreshTest(dbTest: DbTest) {
        taskLaunch {
            dao?.updateData(dbTest)
        }
    }
}