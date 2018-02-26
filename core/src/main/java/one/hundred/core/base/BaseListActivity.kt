package one.hundred.core.base

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.widget.LinearLayoutManager
import com.pape.adapter.AdapterSequence
import com.pape.adapter.ItemViewModel
import com.pape.adapter.MultiTypeAdapter
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.dip
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.wrapContent

/**
 * Created by zzy on 2017/9/16.
 */
abstract class BaseListActivity : BaseActivity() {

     var adapter = MultiTypeAdapter(AdapterSequence.ASC)

    override fun onCreate(savedInstanceState: Bundle?) {
        coordinatorLayout {
            appBarLayout {
                fitsSystemWindows = true
                toolbar {
                    title = toolBarTitle()
                    setSupportActionBar(this)
                }.lparams(matchParent, dip(50)) {
                    scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
                }
            }.lparams(matchParent, wrapContent)
            recyclerView {
                layoutManager = LinearLayoutManager(this@BaseListActivity)
                adapter = this@BaseListActivity.adapter
            }.lparams(matchParent, wrapContent) {
                behavior = AppBarLayout.ScrollingViewBehavior()
            }
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(isDisplayHomeAsUpEnabled())
        super.onCreate(savedInstanceState)
    }


    override fun onStart() {
        initData()
        super.onStart()
    }

    abstract fun toolBarTitle(): String

    abstract fun initData()

    fun isDisplayHomeAsUpEnabled(): Boolean = true

    fun addItemViewModel(itemViewModel: ItemViewModel) {
        adapter.addOrUpdateItem(itemViewModel)
    }

    fun addItemViewModelList(list: List<ItemViewModel>) {
        adapter.addOrUpdateListItem(list)
    }

    fun getFirstViewModel(): ItemViewModel? {
        return adapter.getItem(0)
    }

}