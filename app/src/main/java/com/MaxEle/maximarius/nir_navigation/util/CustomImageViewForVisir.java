package com.MaxEle.maximarius.nir_navigation.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatImageView;

public class CustomImageViewForVisir extends AppCompatImageView {
    float dX = 0;
    float dY = 0;

    public CustomImageViewForVisir(Context context) {
        super(context);
    }

    public CustomImageViewForVisir(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                dX = this.getX() - event.getRawX();
                dY = this.getY() - event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                this.animate()
                        .y(event.getRawY() + dY)
                        .setDuration(0)
                        .start();
                break;
            case MotionEvent.ACTION_UP:
                performClick();
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    public boolean performClick() {
        super.performClick();
        return true;
    }
}
