package com.example.electricalmaterial.add_material;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class NonSwipeableViewPager extends ViewPager {

    private boolean swipeEnabled = false;

    public NonSwipeableViewPager(@NonNull Context context) {
        super(context);
    }

    public NonSwipeableViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return swipeEnabled && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return swipeEnabled && super.onInterceptTouchEvent(ev);
    }

    public void setSwipeEnabled(boolean swipeEnabled) {
        this.swipeEnabled = swipeEnabled;
    }
}
