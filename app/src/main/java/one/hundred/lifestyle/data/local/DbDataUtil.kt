package one.hundred.lifestyle.data.local

import java.util.*

/**
 * Created by zzy on 2017/9/16.
 */
object DbDataUtil {

    fun getTestDataList(): List<DbTest> {
        return arrayListOf(
                DbTest(0, "name1", "code1", Date()),
                DbTest(1, "name2", "code2", Date()),
                DbTest(2, "name3", "code3", Date()),
                DbTest(3, "name4", "code4", Date()),
                DbTest(4, "name5", "code5", Date()),
                DbTest(5, "name6", "code6", Date()),
                DbTest(6, "name7", "code7", Date()),
                DbTest(7, "name8", "code8", Date()),
                DbTest(8, "name9", "code9", Date()),
                DbTest(9, "name0", "code0", Date()),
                DbTest(10, "name1", "code1", Date()),
                DbTest(11, "name2", "code2", Date()),
                DbTest(12, "name3", "code3", Date()),
                DbTest(13, "name4", "code4", Date()),
                DbTest(14, "name5", "code5", Date()),
                DbTest(15, "name6", "code6", Date()),
                DbTest(16, "name7", "code7", Date())
        )
    }

}