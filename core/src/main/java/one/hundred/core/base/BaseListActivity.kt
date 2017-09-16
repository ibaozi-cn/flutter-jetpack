package one.hundred.core.base

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.widget.LinearLayoutManager
import com.pape.adapter.ItemViewModel
import com.pape.adapter.MultiTypeAdapter
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.dip
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView

/**
 * Created by zzy on 2017/9/16.
 */
abstract class BaseListActivity : BaseActivity() {

    private var adapter = MultiTypeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        coordinatorLayout {
            fitsSystemWindows = true
            appBarLayout {
                fitsSystemWindows = true
                toolbar {
                    title = toolBarTitle()
                    setSupportActionBar(this)
                }.lparams(matchParent, dip(50)) {
                    scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
                }
            }
            recyclerView {
                layoutManager = LinearLayoutManager(this@BaseListActivity)
                adapter = this@BaseListActivity.adapter
            }.lparams {
                behavior = AppBarLayout.Behavior()
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
        adapter.addItem(itemViewModel)
    }

    fun addItemViewModelList(list: List<ItemViewModel>) {
        adapter.addListItem(list)
    }

}