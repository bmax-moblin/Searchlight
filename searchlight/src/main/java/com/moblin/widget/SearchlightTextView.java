package com.moblin.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Text-view that is animated in a manner that resembles a searchlight.
 * Custom attributes:
 * centerColor - color at the center of the gradient
 * edgeColor - color at the edge of the gradient
 */
public class SearchlightTextView extends AppCompatTextView {
    private int mCenterColor, mEdgeColor;
    private float mPosX, mStep;
    private boolean mDirectionRight = true;
    private Matrix mLocalMatrix = new Matrix();

    /**
     * Constructor that is called when inflating a view from XML.
     * This is called when a view is being constructed from an XML file,
     * supplying attributes that were specified in the XML file.
     */
    public SearchlightTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // The logical density of the display is a scaling factor
        // for the Density Independent Pixel unit. It is used here
        // as the step size to ensure that the animation speed is
        // constant for all densities.
        mStep = getResources().getDisplayMetrics().density;
        readColors(attrs);
    }

    /** View methods */

    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        super.onSizeChanged(width, height, oldw, oldh);
        // Setup the gradient shader using the measured dimensions.
        getPaint().setShader(new RadialGradient(
                mPosX,                  // x-coordinate of the center of the radius
                height * 0.5f,          // y-coordinate of the center of the radius
                width * 0.5f,           // radius of the circle
                mCenterColor,           // color at the center of the circle
                mEdgeColor,             // color at the edge of the circle
                Shader.TileMode.CLAMP   // tiling mode
        ));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Update the gradient's center position and the direction.
        if (mDirectionRight) {
            if (mPosX < getWidth()) {
                mPosX += mStep;
            } else {
                mDirectionRight = false;
            }
        } else {
            if (mPosX > 0) {
                mPosX -= mStep;
            } else {
                mDirectionRight = true;
            }
        }
        // Apply the new position.
        mLocalMatrix.reset();
        mLocalMatrix.setTranslate(mPosX, 0f);
        getPaint().getShader().setLocalMatrix(mLocalMatrix);
        // Ask to redraw
        invalidate();
    }

    /** Private methods */

    private void readColors(AttributeSet attrs) {
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs, R.styleable.SearchlightTextView, 0, 0);
        mCenterColor = a.getColor(R.styleable.SearchlightTextView_centerColor,
                Color.CYAN);
        mEdgeColor = a.getColor(R.styleable.SearchlightTextView_edgeColor,
                Color.BLACK);
        a.recycle();
    }
}
