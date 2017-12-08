package one.hundred.lifestyle.item

import android.view.View
import android.widget.TextView
import com.pape.adapter.ItemViewHolder
import com.pape.adapter.ItemViewModel
import one.hundred.lifestyle.R
import one.hundred.lifestyle.data.bean.Test

/**
 * Created by zzy on 2017/9/16.
 */
class ItemTest(val test: Test, private val onClickListener: (View) -> Unit = {}) : ItemViewModel(
        uuid = test.id.toString()
) {

    override fun bindData(holder: ItemViewHolder) {

        val name = holder.getView<TextView>(R.id.name)
        val code = holder.getView<TextView>(R.id.code)
        val time = holder.getView<TextView>(R.id.time)

        name.text = test.name
        code.text = test.code
        time.text = test.createTime.toString()

        holder.itemView.setOnClickListener {
            onClickListener(it)
        }
    }

    override fun getItemViewLayoutId(): Int {
        return R.layout.item_test_view
    }
}