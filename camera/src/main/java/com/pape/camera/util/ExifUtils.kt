package com.pape.camera.util


import android.support.media.ExifInterface

object ExifUtils {

    internal fun getOrientationTag(rotation: Int, flip: Boolean): Int {
        when (rotation) {
            90 -> return if (flip)
                ExifInterface.ORIENTATION_TRANSPOSE
            else
                ExifInterface.ORIENTATION_ROTATE_90

            180 -> return if (flip)
                ExifInterface.ORIENTATION_FLIP_VERTICAL
            else
                ExifInterface.ORIENTATION_ROTATE_180

            270 -> return if (flip)
                ExifInterface.ORIENTATION_TRANSVERSE
            else
                ExifInterface.ORIENTATION_ROTATE_270

            0 -> return if (flip)
                ExifInterface.ORIENTATION_FLIP_HORIZONTAL
            else
                ExifInterface.ORIENTATION_NORMAL
            else -> return if (flip)
                ExifInterface.ORIENTATION_FLIP_HORIZONTAL
            else
                ExifInterface.ORIENTATION_NORMAL
        }
    }
}
