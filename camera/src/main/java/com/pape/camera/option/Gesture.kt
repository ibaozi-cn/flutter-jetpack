package com.pape.camera.option


import java.util.Arrays


/**
 * Gestures listen to finger gestures over the [CameraView] bounds and can be mapped
 * to one or more camera controls using XML attributes or [CameraView.mapGesture].
 *
 * Not every gesture can control a certain action. For example, pinch gestures can only control
 * continuous values, such as zoom or AE correction. Single point gestures, on the other hand,
 * can only control point actions such as focusing or capturing a picture.
 */
enum class Gesture(vararg controls: GestureAction) {

    /**
     * Pinch gesture, typically assigned to the zoom control.
     * This gesture can be mapped to:
     *
     * - [GestureAction.ZOOM]
     * - [GestureAction.EXPOSURE_CORRECTION]
     * - [GestureAction.NONE]
     */
    PINCH(GestureAction.ZOOM, GestureAction.EXPOSURE_CORRECTION),

    /**
     * Single tap gesture, typically assigned to the focus control.
     * This gesture can be mapped to:
     *
     * - [GestureAction.FOCUS]
     * - [GestureAction.FOCUS_WITH_MARKER]
     * - [GestureAction.CAPTURE]
     * - [GestureAction.NONE]
     */
    TAP(GestureAction.FOCUS, GestureAction.FOCUS_WITH_MARKER, GestureAction.CAPTURE),
    // DOUBLE_TAP(GestureAction.FOCUS, GestureAction.FOCUS_WITH_MARKER, GestureAction.CAPTURE),

    /**
     * Long tap gesture.
     * This gesture can be mapped to:
     *
     * - [GestureAction.FOCUS]
     * - [GestureAction.FOCUS_WITH_MARKER]
     * - [GestureAction.CAPTURE]
     * - [GestureAction.NONE]
     */
    LONG_TAP(GestureAction.FOCUS, GestureAction.FOCUS_WITH_MARKER, GestureAction.CAPTURE),

    /**
     * Horizontal scroll gesture.
     * This gesture can be mapped to:
     *
     * - [GestureAction.ZOOM]
     * - [GestureAction.EXPOSURE_CORRECTION]
     * - [GestureAction.NONE]
     */
    SCROLL_HORIZONTAL(GestureAction.ZOOM, GestureAction.EXPOSURE_CORRECTION),

    /**
     * Vertical scroll gesture.
     * This gesture can be mapped to:
     *
     * - [GestureAction.ZOOM]
     * - [GestureAction.EXPOSURE_CORRECTION]
     * - [GestureAction.NONE]
     */
    SCROLL_VERTICAL(GestureAction.ZOOM, GestureAction.EXPOSURE_CORRECTION);

    private val mControls: List<GestureAction> = Arrays.asList(*controls)

    fun isAssignableTo(control: GestureAction): Boolean {
        return control == GestureAction.NONE || mControls.contains(control)
    }

}
