package one.hundred.lifestyle.business.home

import android.arch.lifecycle.LifecycleRegistry
import android.os.Bundle
import android.util.Log
import com.pape.net.ApiFactory
import com.pape.net.await
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import one.hundred.core.base.BaseActivity
import one.hundred.experimental.ui.onClick
import one.hundred.lifestyle.business.test.TestActivity
import one.hundred.lifestyle.data.server.ApiService
import one.hundred.lifestyle.data.server.BASE_URL
import org.jetbrains.anko.button
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.relativeLayout

class MainViewActivity : BaseActivity() {

    val api by lazy {
        ApiFactory.instance(this@MainViewActivity)
                .createApi(ApiService::class.java, BASE_URL)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        relativeLayout {
            button("startTestActivity") {
                onClick {
                    startActivity(intentFor<TestActivity>())
                    async {
                        try {
                            val anime = api.getAnime(1).await()
                            Log.d("net", anime.toString())
                        } catch (exception: Exception) {
                            Log.d("net", exception.toString())
                        }
                    }
                    finish()
                }
            }.lparams(matchParent, matchParent)
        }
        super.onCreate(savedInstanceState)
    }

    override fun onActivityBack() {
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("net","activity onDestroy")
        val lifecycle = this.lifecycle as LifecycleRegistry
        Log.d("net","activity lifecycle observerCount${lifecycle.observerCount}")

    }
}

