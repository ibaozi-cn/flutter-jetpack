package com.pape.camera.option


/**
 * 当前会话应使用哪个摄像头传感器.
 *
 * @see CameraView.setFacing
 */
enum class Facing(private val value: Int) : Control {

    /**
     * Back-facing camera sensor.
     */
    BACK(0),

    /**
     * Front-facing camera sensor.
     */
    FRONT(1);

    fun value(): Int {
        return value
    }

    companion object {

        val DEFAULT = BACK

        fun fromValue(value: Int): Facing? {
            val list = Facing.values()
            return list.firstOrNull { it.value() == value }
        }
    }
}
