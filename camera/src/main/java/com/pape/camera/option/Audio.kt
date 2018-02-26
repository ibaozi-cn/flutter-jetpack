package com.pape.camera.option


/**
 * 音频值指示录制视频时是否记录音频流
 *
 * @see CameraView.setAudio
 */
enum class Audio(private val value: Int) : Control {

    /**
     * No Audio.
     */
    OFF(0),

    /**
     * With Audio.
     */
    ON(1);

    fun value(): Int {
        return value
    }

    companion object {

        val DEFAULT = ON

        fun fromValue(value: Int): Audio? {
            val list = Audio.values()
            return list.firstOrNull { it.value() == value }
        }
    }
}
