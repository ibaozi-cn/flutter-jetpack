package one.hundred.core.base

import android.annotation.TargetApi
import android.app.ProgressDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.transition.Explode
import android.transition.Fade
import android.transition.Transition
import android.view.inputmethod.InputMethodManager
import one.hundred.core.extend.LOLLIPOP
import one.hundred.core.util.Common
import one.hundred.core.util.Network
import pub.devrel.easypermissions.EasyPermissions


/**
 * Created by zzy on 2017/8/2.
 */
abstract class BaseActivity : AppCompatActivity() {

    private val mProgressDialog: ProgressDialog by lazy {
        Common.showLoadingDialog(this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    fun showLoading() {
        hideLoading()
        mProgressDialog.show()
    }

    fun hideLoading() {
        if (mProgressDialog.isShowing) {
            mProgressDialog.cancel()
        }
    }

    fun isNetworkConnected(): Boolean {
        return Network.isNetworkConnected(applicationContext)
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onBackPressed() {
        onActivityBack()
        ActivityCompat.finishAfterTransition(this)
        super.onBackPressed()
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        LOLLIPOP {
            window.enterTransition = initEnterTransition()
            window.returnTransition = Fade()
            window.exitTransition = Fade()
        }
        super.onCreate(savedInstanceState)
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    open fun initEnterTransition(): Transition? {
        val explode = Explode()
        explode.duration = 1000
        return explode
    }

    abstract fun onActivityBack()

}