package com.pape.camera.option


/**
 * 要打开或移动到的会话的类型。
 * 会话类型对捕捉和预览大小有影响，能够拍摄图片，
 * 对焦模式，所需的运行时权限。
 *
 * @see CameraView.setSessionType
 */
enum class SessionType(private val value: Int) : Control {

    /**
     * Session optimized to capture pictures.
     *
     * - Trying to take videos in this session will throw an exception
     * - Only the camera permission is requested
     * - Capture size is chosen according to the current picture size selector
     */
    PICTURE(0),

    /**
     * Session optimized to capture videos.
     *
     * - Trying to take pictures in this session will work, though with lower quality
     * - Trying to take pictures while recording a video will work if supported
     * - Camera and audio record permissions are requested
     * - Capture size is chosen trying to respect the [VideoQuality] aspect ratio
     *
     * @see CameraOptions.isVideoSnapshotSupported
     */
    VIDEO(1);

    fun value(): Int {
        return value
    }

    companion object {

        val DEFAULT = PICTURE

        fun fromValue(value: Int): SessionType? {
            val list = SessionType.values()
            return list.firstOrNull { it.value() == value }
        }
    }
}
