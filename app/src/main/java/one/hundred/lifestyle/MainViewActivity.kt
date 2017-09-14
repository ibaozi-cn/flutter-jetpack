package one.hundred.lifestyle

import android.app.Activity
import android.os.Bundle
import android.view.View
import org.jetbrains.anko.*

class MainViewActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        relativeLayout {
            button("click") {
                View.OnClickListener({
                    alert("aaa", "vcc") {
                        yesButton {
                            toast("aaaaaa")
                        }
                    }.show()
                })
            }.lparams(matchParent, matchParent)
        }
    }

    override fun onStart() {
        super.onStart()
    }
}
