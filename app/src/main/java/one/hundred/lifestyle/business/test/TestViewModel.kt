package one.hundred.lifestyle.business.test

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import one.hundred.lifestyle.App
import one.hundred.lifestyle.data.TestRepository
import one.hundred.lifestyle.data.local.DbTest

/**
 * Created by zzy on 2017/9/16.
 */
class TestViewModel(private val repository: TestRepository) : AndroidViewModel(App.instance()) {

    fun getLiveData(id:Long): LiveData<DbTest> {
        return repository.getDbTest(id)
    }

    fun refreshTest(dbTest: DbTest) {
        repository.refreshTest(dbTest)
    }

}