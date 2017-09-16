package one.hundred.lifestyle.item

import android.widget.TextView
import com.pape.adapter.ItemViewHolder
import com.pape.adapter.ItemViewModel
import one.hundred.lifestyle.R
import one.hundred.lifestyle.data.bean.Test

/**
 * Created by zzy on 2017/9/16.
 */
class ItemTest(val test: Test) : ItemViewModel {

    override fun bindData(holder: ItemViewHolder) {

        val name = holder.getView<TextView>(R.id.name)
        val code = holder.getView<TextView>(R.id.code)
        val time = holder.getView<TextView>(R.id.time)

        name?.text = test.name
        code?.text = test.code
        time?.text = test.createTime.toString()

    }

    override fun getItemViewLayoutId(): Int {
        return R.layout.item_test_view
    }
}