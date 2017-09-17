package one.hundred.lifestyle

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import one.hundred.lifestyle.business.test.TestViewModel
import one.hundred.lifestyle.data.Repository
import one.hundred.lifestyle.data.TestRepository

@Suppress("UNCHECKED_CAST")
/**
 * Created by zzy on 2017/9/17.
 */
class ViewModelFactory(
        private val repository: Repository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            with(modelClass)
            {
                when {
                    isAssignableFrom(TestViewModel::class.java) && repository is TestRepository ->
                        TestViewModel(repository)
                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T
}