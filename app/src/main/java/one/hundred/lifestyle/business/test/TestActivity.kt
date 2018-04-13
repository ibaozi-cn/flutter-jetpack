package one.hundred.lifestyle.business.test

import one.hundred.core.base.BaseListActivity
import one.hundred.lifestyle.data.bean.Test
import one.hundred.lifestyle.item.ItemReplay
import one.hundred.lifestyle.item.ItemTest


/**
 * Created by zzy on 2017/9/16.
 */
class TestActivity : BaseListActivity() {

    var a: Long = 0


    override fun initData() {

        addItemViewModel(ItemTest(Test(id = -1, name = "测试刷新数据"), {
            if (a % 2 == 0L) {
                addItemViewModel(ItemTest(Test(a++), {

                }).also {
                    it.uuid = "1"
                })
            } else {
                addItemViewModel(ItemReplay().also {
                    it.uuid = "1"
                })
            }
        }))

    }

    override fun toolBarTitle(): String {
        return "测试"
    }

    override fun onActivityBack() {

    }


}