package com.pape.camera.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;

import com.pape.camera.AspectRatio;
import com.pape.camera.Size;

public abstract class CameraPreview<T extends View, Output> {

    // After that, CameraView will need a new layout pass to adapt to the Preview size.
    public interface SurfaceCallback {
        void onSurfaceAvailable();

        void onSurfaceChanged();
    }

    private SurfaceCallback mSurfaceCallback;
    private T mView;
    private boolean mCropping;

    // As far as I can see, these are the view/surface dimensions.
    // This live in the 'View' orientation.
    private int mSurfaceWidth;
    private int mSurfaceHeight;

    // As far as I can see, these are the actual preview dimensions, as set in CameraParameters.
    private int mDesiredWidth;
    private int mDesiredHeight;

    CameraPreview(Context context, ViewGroup parent, SurfaceCallback callback) {
        mView = onCreateView(context, parent);
        mSurfaceCallback = callback;
    }

    @NonNull
    protected abstract T onCreateView(Context context, ViewGroup parent);

    abstract Surface getSurface();

    @NonNull
    public final T getView() {
        return mView;
    }

    public abstract Class<Output> getOutputClass();

    public abstract Output getOutput();

    // As far as I can see, these are the actual preview dimensions, as set in CameraParameters.
    // This is called by the CameraImpl.
    // These must be alredy rotated, if needed, to be consistent with surface/view sizes.
    public void setDesiredSize(int width, int height) {
        this.mDesiredWidth = width;
        this.mDesiredHeight = height;
        crop();
    }

    final Size getDesiredSize() {
        return new Size(mDesiredWidth, mDesiredHeight);
    }

    public final Size getSurfaceSize() {
        return new Size(mSurfaceWidth, mSurfaceHeight);
    }

    public final void setSurfaceCallback(SurfaceCallback callback) {
        mSurfaceCallback = callback;
        // If surface already available, dispatch.
        if (mSurfaceWidth != 0 || mSurfaceHeight != 0) {
            mSurfaceCallback.onSurfaceAvailable();
        }
    }


    protected final void onSurfaceAvailable(int width, int height) {
        mSurfaceWidth = width;
        mSurfaceHeight = height;
        crop();
        mSurfaceCallback.onSurfaceAvailable();
    }


    // As far as I can see, these are the view/surface dimensions.
    // This is called by subclasses.
    protected final void onSurfaceSizeChanged(int width, int height) {
        if (width != mSurfaceWidth || height != mSurfaceHeight) {
            mSurfaceWidth = width;
            mSurfaceHeight = height;
            crop();
            mSurfaceCallback.onSurfaceChanged();
        }
    }

    protected final void onSurfaceDestroyed() {
        mSurfaceWidth = 0;
        mSurfaceHeight = 0;
    }

    public final boolean isReady() {
        return mSurfaceWidth > 0 && mSurfaceHeight > 0;
    }

    /**
     * Here we must crop the visible part by applying a > 1 scale to one of our
     * dimensions. This way our internal aspect ratio (mSurfaceWidth / mSurfaceHeight)
     * will match the preview size aspect ratio (mDesiredWidth / mDesiredHeight).
     * <p>
     * There might still be some absolute difference (e.g. same ratio but bigger / smaller).
     * However that should be already managed by the framework.
     */
    private final void crop() {

        if (!supportsCropping()) {
            return;
        }

        getView().post(new Runnable() {
            @Override
            public void run() {
                if (mDesiredHeight == 0 || mDesiredWidth == 0 ||
                        mSurfaceHeight == 0 || mSurfaceWidth == 0) {
                    return;
                }

                float scaleX = 1f, scaleY = 1f;
                AspectRatio current = AspectRatio.of(mSurfaceWidth, mSurfaceHeight);
                AspectRatio target = AspectRatio.of(mDesiredWidth, mDesiredHeight);
                if (current.toFloat() >= target.toFloat()) {
                    // We are too short. Must increase height.
                    scaleY = current.toFloat() / target.toFloat();
                } else {
                    // We must increase width.
                    scaleX = target.toFloat() / current.toFloat();
                }
                applyCrop(scaleX, scaleY);
                mCropping = scaleX > 1.02f || scaleY > 1.02f;
            }
        });
    }

    protected void applyCrop(float scaleX, float scaleY) {
        getView().setScaleX(scaleX);
        getView().setScaleY(scaleY);
    }

    public boolean supportsCropping() {
        return true;
    }

    /**
     * Whether we are cropping the output.
     * If false, this means that the output image will match the visible bounds.
     *
     * @return true if cropping
     */
    /* not final for tests */
    public boolean isCropping() {
        return mCropping;
    }
}
