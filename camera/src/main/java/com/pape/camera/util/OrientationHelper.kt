package com.pape.camera.util

import android.content.Context
import android.hardware.SensorManager
import android.view.Display
import android.view.OrientationEventListener
import android.view.Surface
import android.view.WindowManager

class OrientationHelper(context: Context, private val mCallback: Callback) {

    private val mListener: OrientationEventListener
    internal var deviceOrientation = -1
        private set
    var displayOffset = -1
        private set

    interface Callback {
        fun onDeviceOrientationChanged(deviceOrientation: Int)
    }

    init {
        mListener = object : OrientationEventListener(context, SensorManager.SENSOR_DELAY_NORMAL) {

            override fun onOrientationChanged(orientation: Int) {
                var or = 0
                if (orientation == OrientationEventListener.ORIENTATION_UNKNOWN) {
                    or = 0
                } else if (orientation >= 315 || orientation < 45) {
                    or = 0
                } else if (orientation in 45..134) {
                    or = 90
                } else if (orientation in 135..224) {
                    or = 180
                } else if (orientation in 225..314) {
                    or = 270
                }

                if (or != deviceOrientation) {
                    deviceOrientation = or
                    mCallback.onDeviceOrientationChanged(deviceOrientation)
                }
            }
        }
    }

    fun enable(context: Context) {
        val display = (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
        displayOffset = when (display.rotation) {
            Surface.ROTATION_0 -> 0
            Surface.ROTATION_90 -> 90
            Surface.ROTATION_180 -> 180
            Surface.ROTATION_270 -> 270
            else -> 0
        }
        mListener.enable()
    }

    fun disable() {
        mListener.disable()
        displayOffset = -1
        deviceOrientation = -1
    }
}