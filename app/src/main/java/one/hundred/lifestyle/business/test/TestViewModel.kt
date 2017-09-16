package one.hundred.lifestyle.business.test

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.pape.adapter.ItemViewModel
import one.hundred.lifestyle.base.BaseViewModel
import one.hundred.lifestyle.data.Repository
import one.hundred.lifestyle.data.TestRepository
import one.hundred.lifestyle.data.bean.Test

/**
 * Created by zzy on 2017/9/16.
 */
class TestViewModel(context: Application, private val dataRepository: Repository) : BaseViewModel(context, dataRepository) {


    fun getLiveData(): LiveData<List<ItemViewModel>> {

        val liveData: LiveData<List<Test>> = MutableLiveData<List<Test>>()

        if (dataRepository is TestRepository) {

        }
        return MutableLiveData()
    }


}