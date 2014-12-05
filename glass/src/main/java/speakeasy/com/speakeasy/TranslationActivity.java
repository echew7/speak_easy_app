package speakeasy.com.speakeasy;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Eric on 12/2/14.
 */
public class TranslationActivity extends BaseSpeakEasyActivity implements VoiceRecognitionRunnable.OnVoiceRecognizedListener {

    private GestureDetector mGestureDetector;
    private VoiceRecognitionRunnable voiceRecognitionRunnable;
    private HashMap<String, String> translationToPhrase;
    private TextView translation;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.translation_activity);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        initializeVoiceRecognition();

        translation = (TextView) findViewById(R.id.translation_text_view);
        mGestureDetector = createGestureDetector(this);
    }

    private GestureDetector createGestureDetector(Context context) {
        GestureDetector gestureDetector = new GestureDetector(context);
        //Create a base listener for generic gestures
        gestureDetector.setBaseListener(new GestureDetector.BaseListener() {
            @Override
            public boolean onGesture(Gesture gesture) {

                if (gesture == Gesture.TAP
                        || gesture == Gesture.TWO_TAP
                        || gesture == Gesture.SWIPE_RIGHT
                        || gesture == Gesture.SWIPE_LEFT) {
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

    private void initializeVoiceRecognition() {
        translationToPhrase = new HashMap<String, String>();
        translationToPhrase.put("Bienvenido a nuestro restaurante", "Welcome to our restaurant");
        translationToPhrase.put("Quieres algo de beber", "Would you like something to drink");
        translationToPhrase.put("Por supuesto", "Of course");
        translationToPhrase.put("Que quieres comer", "What would you like to eat");
        translationToPhrase.put("Muy bien", "Very well");
        voiceRecognitionRunnable = new VoiceRecognitionRunnable(this, extractTranslations());
        voiceRecognitionRunnable.setOnVoiceRecognizedListener(this);
        Thread thread = new Thread(voiceRecognitionRunnable);
        thread.start();
    }


    private ArrayList<String> extractTranslations() {
        //Extract voice commands from the map's keys
        ArrayList<String> phrases = new ArrayList<String>();
        for (String phrase : translationToPhrase.keySet()) {
            phrases.add(phrase);
        }
        return phrases;
    }

    @Override
    public void onVoiceRecognized(String recognized) {
        translation.setText(translationToPhrase.get(recognized));
    }
}
