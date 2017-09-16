package one.hundred.lifestyle.business.home

import android.os.Bundle
import one.hundred.core.base.BaseActivity
import one.hundred.experimental.ui.onClick
import one.hundred.lifestyle.business.test.TestActivity
import org.jetbrains.anko.button
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.relativeLayout

class MainViewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        relativeLayout {
            button("click") {
                onClick {
                    startActivity(intentFor<TestActivity>())
                }
            }.lparams(matchParent, matchParent)
        }
        super.onCreate(savedInstanceState)
    }

    override fun onActivityBack() {
    }
}
