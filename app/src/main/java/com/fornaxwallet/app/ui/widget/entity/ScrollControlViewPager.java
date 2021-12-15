package com.fornaxwallet.app.ui.widget.entity;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;

import com.fornaxwallet.app.util.Utils;

public class ScrollControlViewPager extends ViewPager {
    private boolean isLocked = true; //locked by default

    public ScrollControlViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollControlViewPager(@NonNull Context context) {
        super(context);
    }

    public void lockPages(boolean locked) {
        isLocked = locked;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isLocked) {
            return false;
        } else {
            return super.onInterceptTouchEvent(ev);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isLocked) {
            return false;
        } else {
            return super.onTouchEvent(event);
        }
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }
}