package speakeasy.com.speakeasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Eric on 12/5/14.
 */
public class TutorialTranslationActivity extends BaseSpeakEasyActivity implements VoiceRecognitionRunnable.OnVoiceRecognizedListener {
    private VoiceRecognitionRunnable voiceRecognitionRunnable;
    private HashMap<String, String> translationToPhrase;
    private TextView translation;
    private Thread voiceRecognitionThread;
    private Boolean tutorialDone = false;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.translation_activity);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        initializeVoiceRecognition();
        translation = (TextView) findViewById(R.id.translation_text_view);
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            Thread.sleep(1000);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    private void initializeVoiceRecognition() {
        translationToPhrase = new HashMap<String, String>();
        translationToPhrase.put("Hello", "Hola");
        voiceRecognitionRunnable = new VoiceRecognitionRunnable(this, extractTranslations());
        voiceRecognitionRunnable.setOnVoiceRecognizedListener(this);
        voiceRecognitionThread = new Thread(voiceRecognitionRunnable);
        voiceRecognitionThread.start();
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
        if (!tutorialDone) {
            Intent doneActivity = new Intent(this, DoneTutorialCard.class);
            startActivity(doneActivity);
            tutorialDone = true;
        }
        voiceRecognitionRunnable.terminate();
        finish();
    }
}
