package com.pape.camera.option


/**
 * Gestures actions are actions over camera controls that can be mapped to certain gestures over
 * the screen, using XML attributes or [CameraView.mapGesture].
 *
 * Not every gesture can control a certain action. For example, pinch gestures can only control
 * continuous values, such as zoom or AE correction. Single point gestures, on the other hand,
 * can only control point actions such as focusing or capturing a picture.
 */
enum class GestureAction(private val value: Int) {

    /**
     * No action. This can be mapped to any gesture to disable it.
     */
    NONE(0),

    /**
     * Auto focus control, typically assigned to the tap gesture.
     * This action can be mapped to:
     *
     * - [Gesture.TAP]
     * - [Gesture.LONG_TAP]
     */
    FOCUS(1),

    /**
     * Auto focus control, typically assigned to the tap gesture.
     * On top of [.FOCUS], this will draw a default marker on screen.
     * This action can be mapped to:
     *
     * - [Gesture.TAP]
     * - [Gesture.LONG_TAP]
     */
    FOCUS_WITH_MARKER(2),

    /**
     * When triggered, this action will fire a picture shoot.
     * This action can be mapped to:
     *
     * - [Gesture.TAP]
     * - [Gesture.LONG_TAP]
     */
    CAPTURE(3),

    /**
     * Zoom control, typically assigned to the pinch gesture.
     * This action can be mapped to:
     *
     * - [Gesture.PINCH]
     * - [Gesture.SCROLL_HORIZONTAL]
     * - [Gesture.SCROLL_VERTICAL]
     */
    ZOOM(4),

    /**
     * Exposure correction control.
     * This action can be mapped to:
     *
     * - [Gesture.PINCH]
     * - [Gesture.SCROLL_HORIZONTAL]
     * - [Gesture.SCROLL_VERTICAL]
     */
    EXPOSURE_CORRECTION(5);

    fun value(): Int {
        return value
    }

    companion object {

        val DEFAULT_PINCH = NONE
        val DEFAULT_TAP = NONE
        val DEFAULT_LONG_TAP = NONE
        val DEFAULT_SCROLL_HORIZONTAL = NONE
        val DEFAULT_SCROLL_VERTICAL = NONE

        fun fromValue(value: Int): GestureAction? {
            val list = GestureAction.values()
            return list.firstOrNull { it.value() == value }
        }
    }
}
