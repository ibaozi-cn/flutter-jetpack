package com.pape.camera.util

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.hardware.Camera
import android.support.media.ExifInterface
import com.pape.camera.Mapper

import com.pape.camera.option.Facing
import one.hundred.experimental.taskAsync
import one.hundred.experimental.taskOnUiThread

import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStream

/**
 * Static utilities for dealing with camera I/O, orientations, etc.
 */
object CameraUtils {

    fun Context.hasCameras(): Boolean {
        return packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA) || packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)
    }


    fun hasCameraFacing(facing: Facing): Boolean {
        val internal = Mapper.Mapper1().map<Int>(facing)
        val cameraInfo = Camera.CameraInfo()
        var i = 0
        val count = Camera.getNumberOfCameras()
        while (i < count) {
            Camera.getCameraInfo(i, cameraInfo)
            if (cameraInfo.facing == internal) return true
            i++
        }
        return false
    }

    fun decodeBitmap(source: ByteArray, onBitmapReady: (Bitmap) -> Unit) {
        decodeBitmap(source, Integer.MAX_VALUE, Integer.MAX_VALUE, onBitmapReady)
    }

    fun decodeBitmap(source: ByteArray, maxWidth: Int, maxHeight: Int, onBitmapReady: (Bitmap) -> Unit) {
        val bitmap = taskAsync {
            decodeBitmap(source, maxWidth, maxHeight)
        }
        taskOnUiThread {
            onBitmapReady(bitmap.await())
        }
    }


    fun decodeBitmap(source: ByteArray, maxWidth: Int, maxHeight: Int): Bitmap {

        var maxWidth = maxWidth
        var maxHeight = maxHeight

        if (maxWidth <= 0) maxWidth = Integer.MAX_VALUE
        if (maxHeight <= 0) maxHeight = Integer.MAX_VALUE
        
        var orientation: Int
        var flip: Boolean
        var stream: InputStream? = null
        try {
            // http://sylvana.net/jpegcrop/exif_orientation.html
            stream = ByteArrayInputStream(source)
            val exif = ExifInterface(stream)
            val exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
            when (exifOrientation) {
                ExifInterface.ORIENTATION_NORMAL, ExifInterface.ORIENTATION_FLIP_HORIZONTAL -> orientation = 0

                ExifInterface.ORIENTATION_ROTATE_180, ExifInterface.ORIENTATION_FLIP_VERTICAL -> orientation = 180

                ExifInterface.ORIENTATION_ROTATE_90, ExifInterface.ORIENTATION_TRANSPOSE -> orientation = 90

                ExifInterface.ORIENTATION_ROTATE_270, ExifInterface.ORIENTATION_TRANSVERSE -> orientation = 270

                else -> orientation = 0
            }

            flip = exifOrientation == ExifInterface.ORIENTATION_FLIP_HORIZONTAL ||
                    exifOrientation == ExifInterface.ORIENTATION_FLIP_VERTICAL ||
                    exifOrientation == ExifInterface.ORIENTATION_TRANSPOSE ||
                    exifOrientation == ExifInterface.ORIENTATION_TRANSVERSE

        } catch (e: IOException) {
            e.printStackTrace()
            orientation = 0
            flip = false
        } finally {
            if (stream != null) {
                try {
                    stream.close()
                } catch (ignored: Exception) {
                }

            }
        }

        var bitmap: Bitmap
        if (maxWidth < Integer.MAX_VALUE || maxHeight < Integer.MAX_VALUE) {
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeByteArray(source, 0, source.size, options)

            var outHeight = options.outHeight
            var outWidth = options.outWidth
            if (orientation % 180 != 0) {
                outHeight = options.outWidth
                outWidth = options.outHeight
            }

            options.inSampleSize = computeSampleSize(outWidth, outHeight, maxWidth, maxHeight)
            options.inJustDecodeBounds = false
            bitmap = BitmapFactory.decodeByteArray(source, 0, source.size, options)
        } else {
            bitmap = BitmapFactory.decodeByteArray(source, 0, source.size)
        }

        if (orientation != 0 || flip) {
            val matrix = Matrix()
            matrix.setRotate(orientation.toFloat())
            // matrix.postScale(1, -1) Flip... needs testing.
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
            bitmap.recycle()
        }
        return bitmap
    }


    private fun computeSampleSize(width: Int, height: Int, maxWidth: Int, maxHeight: Int): Int {
        // https://developer.android.com/topic/performance/graphics/load-bitmap.html
        var inSampleSize = 1
        if (height > maxHeight || width > maxWidth) {
            while (height / inSampleSize >= maxHeight || width / inSampleSize >= maxWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }
}
