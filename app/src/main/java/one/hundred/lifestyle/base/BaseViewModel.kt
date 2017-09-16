package one.hundred.lifestyle.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import one.hundred.lifestyle.data.Repository

/**
 * Created by zzy on 2017/9/15.
 */
open class BaseViewModel(context: Application, repository: Repository) : AndroidViewModel(context)