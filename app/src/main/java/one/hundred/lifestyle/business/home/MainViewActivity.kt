package one.hundred.lifestyle.business.home

import android.os.Bundle
import com.pape.net.ApiFactory
import com.pape.net.LifecycleCall
import com.pape.net.await
import one.hundred.core.base.BaseActivity
import one.hundred.experimental.ui.onClick
import one.hundred.lifestyle.business.test.TestActivity
import org.jetbrains.anko.button
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.relativeLayout
import retrofit2.http.GET

class MainViewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        relativeLayout {
            button("startTestActivity") {
                onClick {
                    val str = ApiFactory.instance(this.context).createApi(Api::class.java,"http://www.baidu.com").getBaidu().await()
                    startActivity(intentFor<TestActivity>())
                }
            }.lparams(matchParent, matchParent)
        }
        super.onCreate(savedInstanceState)
    }

    override fun onActivityBack() {
    }
}


interface Api {

    @GET("/")
    fun getBaidu(): LifecycleCall<String>

}

