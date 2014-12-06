package speakeasy.com.speakeasy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;

/**
 * Created by Eric on 12/5/14.
 */
public class DoneTutorialCard extends TutorialCardActivity {

    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setScript("Great job! You're ready to graduate! If you ever need to go back into Phrases Mode from Translation Mode, just use any gesture on the touch pad to go back.");
        mGestureDetector = createGestureDetector(this);
    }

    private GestureDetector createGestureDetector(Context context) {
        GestureDetector gestureDetector = new GestureDetector(context);
        //Create a base listener for generic gestures
        gestureDetector.setBaseListener(new GestureDetector.BaseListener() {
            @Override
            public boolean onGesture(Gesture gesture) {

                if (gesture == Gesture.TWO_TAP) {
                    Intent phrasesActivity = new Intent(DoneTutorialCard.this, PhrasesActivity.class);
                    startActivity(phrasesActivity);
                    finish();
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
