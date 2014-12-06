package speakeasy.com.speakeasy;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;

/**
 * Created by Eric on 12/3/14.
 */
public class SwipeListView extends ListView {

    protected Context context;

    private GestureDetector mGestureDetector;
    private int rowHeight;

    final static private int ANIMATION_TIME = 500;

    public SwipeListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initializeList();

    }

    public SwipeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initializeList();
    }

    public SwipeListView(Context context) {
        super(context);
        this.context = context;
        initializeList();
    }

    public void setRowHeight(int height) {
        rowHeight = height;
    }

    public void smoothScrollToNextItem() {
        smoothScrollBy(rowHeight, ANIMATION_TIME);
    }

    public void smoothScrollToPreviousItem() {
        smoothScrollBy(-rowHeight, ANIMATION_TIME);
    }

    private void initializeList() {
        mGestureDetector = createGestureDetector(context);
    }

    private GestureDetector createGestureDetector(Context context) {
        GestureDetector gestureDetector = new GestureDetector(context);
        //Create a base listener for generic gestures
        gestureDetector.setBaseListener(new GestureDetector.BaseListener() {
            @Override
            public boolean onGesture(Gesture gesture) {

                if (gesture == Gesture.TAP) {
                    // do something on tap
                    return true;
                } else if (gesture == Gesture.TWO_TAP) {
                    // do something on two finger tap
                    return true;
                } else if (gesture == Gesture.SWIPE_RIGHT) {
                    // do something on right (forward) swipe
                    smoothScrollToNextItem();
                    return true;
                } else if (gesture == Gesture.SWIPE_LEFT) {
                    // do something on left (backwards) swipe
                    smoothScrollToPreviousItem();
                    return true;
                }
                return false;
            }
        });
        gestureDetector.setFingerListener(new GestureDetector.FingerListener() {
            @Override
            public void onFingerCountChanged(int previousCount, int currentCount) {
                // do something on finger count changes
            }
        });
        gestureDetector.setScrollListener(new GestureDetector.ScrollListener() {
            @Override
            public boolean onScroll(float displacement, float delta, float velocity) {
                // do something on scrolling
                return true;
            }
        });
        return gestureDetector;
    }

    /*
     * Send generic motion events to the gesture detector
     */
    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        super.onGenericMotionEvent(event);
        if (mGestureDetector != null) {
            return mGestureDetector.onMotionEvent(event);
        }
        return false;
    }
}
