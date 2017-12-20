package one.hundred.lifestyle.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.support.v7.app.AppCompatActivity
import com.pape.net.ApiFactory
import one.hundred.experimental.taskLaunch
import one.hundred.lifestyle.data.bean.Test
import one.hundred.lifestyle.data.local.DatabaseCreator
import one.hundred.lifestyle.data.local.DbTest
import one.hundred.lifestyle.data.server.ApiService


/**
 * Created by zzy on 2017/9/16.
 */
class TestRepository(context: AppCompatActivity) : Repository {

    private val apiService: ApiService = ApiFactory.instance(context).createApi(ApiService::class.java, "http://www.google.com")

    private val dao = DatabaseCreator.instance.database?.getDbTestDao()

    private val call by lazy {
        apiService.getList(0)
    }
    /**
     * 获取数据库测试数据
     */
    fun getDbTest(id: Long): LiveData<DbTest> {
        return dao!!.getData(id)
    }

    /**
     * 获取网络测试数据
     */
    suspend fun getServerTestList(): LiveData<List<Test>> {
        val serverData = MutableLiveData<List<Test>>()
//        serverData.value = call.await()
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