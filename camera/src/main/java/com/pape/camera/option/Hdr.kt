package com.pape.camera.option


/**
 * Hdr值指示在捕捉图片时是否使用高动态范围技术。
 *
 * @see CameraView.setHdr
 */
enum class Hdr(private val value: Int) : Control {

    /**
     * No HDR.
     */
    OFF(0),

    /**
     * Using HDR.
     */
    ON(1);

    fun value(): Int {
        return value
    }

    companion object {

        val DEFAULT = OFF

        fun fromValue(value: Int): Hdr? {
            val list = Hdr.values()
            return list.firstOrNull { it.value() == value }
        }
    }
}
