package com.pape.camera.option


/**
 * 白平衡值控制白平衡设置
 *
 * @see CameraView.setWhiteBalance
 */
enum class WhiteBalance(private val value: Int) : Control {

    /**
     * Automatic white balance selection (AWB).
     * This is not guaranteed to be supported.
     *
     * @see CameraOptions.getSupportedWhiteBalance
     */
    AUTO(0),

    /**
     * White balance appropriate for incandescent light.
     * This is not guaranteed to be supported.
     *
     * @see CameraOptions.getSupportedWhiteBalance
     */
    INCANDESCENT(1),

    /**
     * White balance appropriate for fluorescent light.
     * This is not guaranteed to be supported.
     *
     * @see CameraOptions.getSupportedWhiteBalance
     */
    FLUORESCENT(2),

    /**
     * White balance appropriate for daylight captures.
     * This is not guaranteed to be supported.
     *
     * @see CameraOptions.getSupportedWhiteBalance
     */
    DAYLIGHT(3),

    /**
     * White balance appropriate for pictures in cloudy conditions.
     * This is not guaranteed to be supported.
     *
     * @see CameraOptions.getSupportedWhiteBalance
     */
    CLOUDY(4);

    fun value(): Int {
        return value
    }

    companion object {

        val DEFAULT = AUTO

        fun fromValue(value: Int): WhiteBalance? {
            val list = WhiteBalance.values()
            return list.firstOrNull { it.value() == value }
        }
    }
}