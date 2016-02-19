package com.tse.fdemo.widget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ListView;

/**
 * Custom ListView handled interceptTouchevent
 *
 * @author Tse.y <Tse.y17o@gmail.com>
 */
public class ListViewForInter extends ListView {
    private final static int LONG_PRESS_TIMEOUT = ViewConfiguration.getLongPressTimeout();
    private int TOUCH_SLOP;
    private VelocityTracker tracker;
    private Context context;
    private int mPointId;
    private boolean mHasPerformedLongPress = false;
    private Handler handler;
    private Runnable runnable;
    /**
     * The X value associated with the the down motion event
     */
    private int mMotionX;
    /**
     * The Y value associated with the the down motion event
     */
    private int mMotionY;
    /**
     * ID of the active pointer. This is used to retain consistency during
     * drags/flings if multiple pointers are used.
     */
    private int mActivePointerId = -1;

    public ListViewForInter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    public ListViewForInter(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public ListViewForInter(Context context) {
        super(context);
        this.context = context;
        init();
    }

    private void init() {
        if (tracker == null)
            tracker = VelocityTracker.obtain();
        TOUCH_SLOP = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    private boolean mPerformLongClick() {
        return true;
    }

    /**
     * 模拟longclick  延时操作
     *
     * @param v 作为参照物的view
     */
    private void postDelayed(final View v) {
        mHasPerformedLongPress = false;
        if (handler == null) {
            handler = new Handler();
        }
        if (runnable == null)
            runnable = new Runnable() {
                @Override
                public void run() {
                    if (mPerformLongClick())
                        mHasPerformedLongPress = true;
                    Log.e("onInterceptTouchEvent", "mHasPerformedLongPress:" + mHasPerformedLongPress);
                }
            };
        handler.postDelayed(runnable, LONG_PRESS_TIMEOUT);

    }

    /**
     * This method is admitted to be deleted.
     * If you can figure out how the runnable run,you will konw why
     * 移出所有延时操作
     */
    private void removeCallBack() {
        if (handler != null && runnable != null) {
            handler.removeCallbacks(runnable);
        }
        handler = null;
        runnable = null;
    }

    private boolean startScrollIfNeeded(int x, int y) {
        //check if we have moved fast enough that it looks more
        //like a scroll than a tap
        final int deltaY = y - mMotionY;
        final int distance = Math.abs(deltaY);
        if (distance > TOUCH_SLOP) {
            Log.e("InterceptTouchEvent", "startScrollIfNeeded:" + true);
            return true;
        }
        return false;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("Listview intercept", ev.getAction() + "/" + super.onInterceptTouchEvent(ev));
        //将事件加入到VelocityTracker类的实例
        tracker.addMovement(ev);
        final int action = ev.getActionMasked();
        if (action == MotionEvent.ACTION_DOWN && ev.getEdgeFlags() != 0) {
            // 该事件可能不是我们的 因为是在listview的边缘
            return false;
        }

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mActivePointerId = ev.getPointerId(0);
                mMotionY = (int) ev.getY();
                mMotionX = (int) ev.getX();
                mHasPerformedLongPress = false;
                postDelayed(this);
                return false;
            case MotionEvent.ACTION_MOVE:
                //还没有触发虚拟的longpress事件
                //判断移动的距离是否大于我们设定的一个值
                //如果是的话就判定为listview的滑动而不将事件传递下去 而是直接给listview的ontouch
                if (!mHasPerformedLongPress) {
                    final int x = (int) ev.getX(mActivePointerId);
                    final int y = (int) ev.getY(mActivePointerId);
                    if (startScrollIfNeeded(x, y))
                        return true;
                } else {
                    Log.e("ListView intercept", "action move mHasPerformedLongPress:" + mHasPerformedLongPress);
                }
                return false;
            case MotionEvent.ACTION_UP:
                removeCallBack();
                return false;
            case MotionEvent.ACTION_CANCEL:
                removeCallBack();
                break;
        }

        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.e("Listview touch", ev.getAction() + "/" + super.onTouchEvent(ev));
        return super.onTouchEvent(ev);
    }
}
