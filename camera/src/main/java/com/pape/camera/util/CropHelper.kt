package com.pape.camera.util

import android.graphics.Bitmap
import android.graphics.Rect
import android.graphics.YuvImage

import com.pape.camera.AspectRatio

import java.io.ByteArrayOutputStream

object CropHelper {


    fun cropToJpeg(yuv: YuvImage, targetRatio: AspectRatio, jpegCompression: Int): ByteArray {
        val crop = computeCrop(yuv.width, yuv.height, targetRatio)
        val out = ByteArrayOutputStream()
        yuv.compressToJpeg(crop, jpegCompression, out)
        return out.toByteArray()
    }


    // This reads a rotated Bitmap thanks to CameraUtils. Then crops and returns a byte array.
    // In doing so, EXIF data is deleted.
    fun cropToJpeg(jpeg: ByteArray, targetRatio: AspectRatio, jpegCompression: Int): ByteArray {
        val image = CameraUtils.decodeBitmap(jpeg, Integer.MAX_VALUE, Integer.MAX_VALUE)
        val cropRect = computeCrop(image.width, image.height, targetRatio)
        val crop = Bitmap.createBitmap(image, cropRect.left, cropRect.top, cropRect.width(), cropRect.height())
        image.recycle()
        val out = ByteArrayOutputStream()
        crop.compress(Bitmap.CompressFormat.JPEG, jpegCompression, out)
        crop.recycle()
        return out.toByteArray()
    }

    private fun computeCrop(currentWidth: Int, currentHeight: Int, targetRatio: AspectRatio): Rect {
        val currentRatio = AspectRatio.of(currentWidth, currentHeight)
        val x: Int
        val y: Int
        val width: Int
        val height: Int
        if (currentRatio.toFloat() > targetRatio.toFloat()) {
            height = currentHeight
            width = (height * targetRatio.toFloat()).toInt()
            y = 0
            x = (currentWidth - width) / 2
        } else {
            width = currentWidth
            height = (width / targetRatio.toFloat()).toInt()
            y = (currentHeight - height) / 2
            x = 0
        }
        return Rect(x, y, x + width, y + height)
    }
}
