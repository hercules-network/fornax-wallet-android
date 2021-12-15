package com.fornaxwallet.app.ui.widget.entity;

import android.content.Context;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

import static android.view.MotionEvent.*;

public class DappBrowserSwipeLayout extends SwipeRefreshLayout {
    private float trackMove;
    private boolean alwaysDown;
    private float lastY;
    private boolean canRefresh;
    private DappBrowserSwipeInterface refreshInterface;

    public DappBrowserSwipeLayout(Context context) {
        super(context);
    }

    public DappBrowserSwipeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setRefreshInterface(DappBrowserSwipeInterface refresh) {
        refreshInterface = refresh;
        alwaysDown = true;
        trackMove = 0.0f;
        canRefresh = true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case ACTION_DOWN:
                trackMove = ev.getY();
                canRefresh = (refreshInterface.getCurrentScrollPosition() == 0 && trackMove < 300);
                lastY = trackMove;
                alwaysDown = true;
                break;
            case ACTION_UP:
                float flingDistance = ev.getY() - trackMove;
                if (canRefresh && alwaysDown && flingDistance > 500 && (ev.getEventTime() - ev.getDownTime()) < 500) //User wants a swipe refresh
                {
                    refreshInterface.RefreshEvent();
                }
                break;
            case ACTION_MOVE:
                if ((ev.getY() - lastY) < 0) alwaysDown = false;
                lastY = ev.getY();
                break;
            default:
                break;
        }

        return false;
    }
}