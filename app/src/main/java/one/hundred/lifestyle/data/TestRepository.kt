package one.hundred.lifestyle.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.content.Context
import one.hundred.core.log.logD
import one.hundred.core.log.logE
import one.hundred.core.net.ServiceFactory
import one.hundred.lifestyle.data.bean.Test
import one.hundred.lifestyle.data.local.DatabaseCreator
import one.hundred.lifestyle.data.server.ApiService
import one.hundred.lifestyle.data.server.RemoteCallback
import java.util.*


/**
 * Created by zzy on 2017/9/16.
 */
class TestRepository(context: Context) : Repository {

    private val apiService: ApiService = ServiceFactory.createRetrofitService("http://www.google.com", ApiService::class.java, context)
    private val dao = DatabaseCreator.instance.database?.getDbTestDao()

    private var data: LiveData<List<Test>>? = null

    /**
     * 获取测试数据
     */
    fun getTestList(id: Long): LiveData<List<Test>> {

        val localData = dao?.getDataList()

        data = Transformations.map(localData, {
            it.map {
                Test(it.id, it.name, it.code, it.createTime)
            }
        })

        val serverData = MutableLiveData<List<Test>>()

        apiService.getList(id).enqueue(object : RemoteCallback<List<Test>>() {

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
        return data ?: serverData
    }

    /**
     * 刷新测试数据
     */
    fun refreshTest(id: Long) {
        val test = dao?.getData(id)
        if (test != null) {
            test.createTime = Date()
            dao?.saveData(test)
        }
    }
}