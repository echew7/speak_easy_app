package speakeasy.com.speakeasy;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.glass.touchpad.GestureDetector;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Eric on 12/2/14.
 */
public class TranslationActivity extends BaseSpeakEasyActivity implements VoiceRecognitionRunnable.OnVoiceRecognizedListener {

    private GestureDetector mGestureDetector;
    private VoiceRecognitionRunnable voiceRecognitionRunnable;
    private HashMap<String, Phrase> translationToPhrase;
    private TextView translation;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.translation_activity);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        initializeVoiceRecognition();

        translation = (TextView) findViewById(R.id.translation_text_view);
    }

    private void initializeVoiceRecognition() {
        translationToPhrase = new HashMap<String, Phrase>();
        translationToPhrase.put("Hola", new Phrase("Hello", "Hola"));
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
        Phrase phrase = translationToPhrase.get(recognized);
        translation.setText(phrase.getNativePhrase());
    }
}
