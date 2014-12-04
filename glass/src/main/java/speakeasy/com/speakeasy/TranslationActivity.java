package speakeasy.com.speakeasy;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.WindowManager;

import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;

/**
 * Created by Eric on 12/2/14.
 */
public class TranslationActivity extends BaseSpeakEasyActivity {

    private GestureDetector mGestureDetector;

    String spanishText;
    String englishText;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.translation_activity);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //getWindow().requestFeature(WindowUtils.FEATURE_VOICE_COMMANDS);
        mGestureDetector = createGestureDetector(this);
    }

    /*

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        if (featureId == WindowUtils.FEATURE_VOICE_COMMANDS) {
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        // Pass through to super to setup touch menu.
        return super.onCreatePanelMenu(featureId, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        TextView textView = (TextView)findViewById(R.id.translation_text_view);
        if (featureId == WindowUtils.FEATURE_VOICE_COMMANDS) {
            switch (item.getItemId()) {
                case R.id.thank_you:
                    spanishText = "Muchas gracias";
                    englishText = "Thank you";
                    break;
                case R.id.how_are_you:
                    spanishText = "Hola. ¿Cómo estás?";
                    englishText = "Hello. How are you?";
                    break;
                default:
                    return true;
            }
            textView.setText(englishText);
            return true;
        }
        // Good practice to pass through to super if not handled
        return super.onMenuItemSelected(featureId, item);
    }
    */

    private GestureDetector createGestureDetector(Context context) {
        GestureDetector gestureDetector = new GestureDetector(context);
        //Create a base listener for generic gestures
        gestureDetector.setBaseListener( new GestureDetector.BaseListener() {
            @Override
            public boolean onGesture(Gesture gesture) {
                if (gesture == Gesture.TAP) {
                    /*
                    TextView textView = (TextView)findViewById(R.id.translation_text_view);
                    String currentText = (String)textView.getText();
                    if(currentText.equals(spanishText)) {
                        textView.setText(englishText);
                    } else if(currentText.equals(englishText)) {
                        textView.setText(spanishText);
                    }
                    */
                    finish();
                    return true;
                } else if (gesture == Gesture.TWO_TAP) {
                    // do something on two finger tap
                    finish();
                    return true;
                } else if (gesture == Gesture.SWIPE_RIGHT) {
                    // do something on right (forward) swipe
                    finish();
                    return true;
                } else if (gesture == Gesture.SWIPE_LEFT) {
                    // do something on left (backwards) swipe
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
