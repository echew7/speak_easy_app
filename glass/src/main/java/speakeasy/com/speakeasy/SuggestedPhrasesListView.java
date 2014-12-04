package speakeasy.com.speakeasy;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListAdapter;

import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;

/**
 * Created by Eric on 12/4/14.
 */
public class SuggestedPhrasesListView extends SwipeListView {

    interface onDisplayedPhraseChangedListener {
        void onDisplayedPhraseChanged();
    }

    private int currentPosition = 0;
    private GestureDetector mGestureDetector;
    private onDisplayedPhraseChangedListener callback;

    public SuggestedPhrasesListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeGestureDetector(context);

    }

    public SuggestedPhrasesListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeGestureDetector(context);
    }

    public SuggestedPhrasesListView(Context context) {
        super(context);
        initializeGestureDetector(context);
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        assert(adapter instanceof PhraseItemAdapter);
        super.setAdapter(adapter);
    }

    @Override
    public void smoothScrollToNextItem() {
        super.smoothScrollToNextItem();
        if (currentPosition < (getCount() - 1)) {
            currentPosition++;
            callback.onDisplayedPhraseChanged();
        }
    }

    @Override
    public void smoothScrollToPreviousItem() {
        super.smoothScrollToPreviousItem();
        if (currentPosition > 0) {
            currentPosition--;
            callback.onDisplayedPhraseChanged();
        }
    }

    public String getCurrentlyDisplayedPhrase() {
        return getPhraseAtPosition(currentPosition);
    }

    public String getNextDisplayedPhrase() {
        return currentPosition < (getCount()-1) ? getPhraseAtPosition(currentPosition + 1) : "";
    }

    public String getPreviouslyDisplayedPhrase() {
        return currentPosition > 0 ? getPhraseAtPosition(currentPosition - 1) : "";
    }

    public String getPhraseAtPosition(int position) {
        Phrase phrase = (Phrase) this.getItemAtPosition(position);
        return ((PhraseItemAdapter)this.getAdapter()).isNativeMode()
                ? phrase.getNativePhrase() : phrase.getTranslatedPhrase();
    }

    public void setDisplayedPhraseChangedCallback(onDisplayedPhraseChangedListener callback) {
        this.callback = callback;
    }

    private void initializeGestureDetector(Context context) {
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
                    changeDisplayMode();
                    return true;
                } else if (gesture == Gesture.TWO_TAP) {
                    // do something on two finger tap
                    return true;
                } else if (gesture == Gesture.SWIPE_RIGHT) {
                    // do something on right (forward) swipe
                    return true;
                } else if (gesture == Gesture.SWIPE_LEFT) {
                    // do something on left (backwards) swipe
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

    private void changeDisplayMode() {
        PhraseItemAdapter adapter = (PhraseItemAdapter) getAdapter();
        adapter.toggleMode();
        adapter.notifyDataSetChanged();
        callback.onDisplayedPhraseChanged();
    }
}
