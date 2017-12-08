package one.hundred.lifestyle.business.test

import one.hundred.core.base.BaseListActivity
import one.hundred.lifestyle.data.bean.Test
import one.hundred.lifestyle.item.ItemTest


/**
 * Created by zzy on 2017/9/16.
 */
class TestActivity : BaseListActivity() {

    var a: Long = 0

    override fun initData() {

        addItemViewModel(ItemTest(Test(name = "测试刷新数据"), {

            addItemViewModel(ItemTest(Test(a++), {
                addItemViewModel(ItemTest(Test(1)).also {
                    it.sortId = 0
                })
            }))

        }))
    }

    override fun toolBarTitle(): String {
        return "测试"
    }

    override fun onActivityBack() {
    }

}