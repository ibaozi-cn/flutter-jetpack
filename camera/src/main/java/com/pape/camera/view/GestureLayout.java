package com.pape.camera.view;

import android.content.Context;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.pape.camera.option.Gesture;

/**
 * Using Views to address the need to draw stuff, but think about it.
 * Simple classes could be enough.
 */
public abstract class GestureLayout extends FrameLayout {

    protected boolean mEnabled;
    protected Gesture mType;
    protected PointF[] mPoints;

    public GestureLayout(Context context) {
        super(context);
        onInitialize(context);
    }

    protected void onInitialize(Context context) {
    }

    public void enable(boolean enable) {
        mEnabled = enable;
    }

    public boolean enabled() {
        return mEnabled;
    }

    public abstract boolean onTouchEvent(MotionEvent event);

    public final Gesture getGestureType() {
        return mType;
    }

    // For tests.
    void setGestureType(Gesture type) {
        mType = type;
    }

    public final PointF[] getPoints() {
        return mPoints;
    }

    public abstract float scaleValue(float currValue, float minValue, float maxValue);
}
