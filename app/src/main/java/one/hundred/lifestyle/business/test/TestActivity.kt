package one.hundred.lifestyle.business.test

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import one.hundred.core.base.BaseListActivity
import one.hundred.lifestyle.ViewModelFactory
import one.hundred.lifestyle.data.TestRepository
import one.hundred.lifestyle.data.bean.Test
import one.hundred.lifestyle.data.local.DbTest
import one.hundred.lifestyle.item.ItemTest
import java.util.*

/**
 * Created by zzy on 2017/9/16.
 */
class TestActivity : BaseListActivity() {

    var a = 0

    override fun initData() {

        val model = ViewModelProviders.of(this, ViewModelFactory(TestRepository(this))).get(TestViewModel::class.java)

        model.getLiveData(9).observe(this, Observer {
            if (it != null)
                addItemViewModel(ItemTest(Test(it.id, it.name, it.code, it.createTime)))
        })

        addItemViewModel(ItemTest(Test(name = "测试刷新数据"), {
            model.refreshTest(DbTest(9, "测试变更数据${a++}", "code${a++}", Date()))
        }))
    }

    override fun toolBarTitle(): String {
        return "测试"
    }

    override fun onActivityBack() {
    }

}