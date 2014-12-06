package speakeasy.com.speakeasy;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.google.android.glass.touchpad.Gesture;

import java.util.ArrayList;

/**
 * Created by Eric on 12/2/14.
 */
public class TutorialActivity extends BaseSpeakEasyActivity
        implements SuggestedPhrasesListView.OnDisplayedPhraseChangedListener, SuggestedPhrasesListView.OnGestureListener {

    private SuggestedPhrasesListView suggestedPhrasesList;
    private PhraseItemAdapter phraseItemAdapter;
    private TextSwitcher prevPhrase;
    private TextSwitcher nextPhrase;

    private int numSwipes = 0;
    private int numTaps = 0;
    private int numTwoTaps = 0;
    private Boolean swipingTut = false;
    private Boolean translateTut = false;
    private Boolean pronunciationTut = false;

    @Override
    public void onDisplayedPhraseChanged() {
        Log.d("R", "Registering Gesture");
        updateDisplayedPhrases();

    }

    @Override
    public void onGesture(Gesture gesture) {
        if (gesture == Gesture.TAP) {
            // do something on tap
            numTaps++;
        } else if (gesture == Gesture.TWO_TAP) {
            // do something on two finger tap
            numTwoTaps++;
        } else if (gesture == Gesture.SWIPE_RIGHT) {
            // do something on right (forward) swipe
            numSwipes++;
        } else if (gesture == Gesture.SWIPE_LEFT) {
            // do something on left (backwards) swipe
            numSwipes++;
        }

        if (numSwipes >= 3 && !swipingTut) {
            Intent translateTutCard = new Intent(this, TranslateTutorialCard.class);
            startActivity(translateTutCard);
            swipingTut = true;
            numSwipes = 0;
            numTaps = 0;
            numTwoTaps = 0;
        }

        if (numTaps >= 1 && numSwipes >= 2 && !translateTut) {
            Intent pronunciationTutCard = new Intent(this, PronunciationTutorialCard.class);
            startActivity(pronunciationTutCard);
            translateTut = true;
            numTaps = 0;
            numSwipes = 0;
            numTwoTaps = 0;
        }

        if (numTwoTaps >= 1 && numSwipes >= 2 && !pronunciationTut) {
            Intent translationTutCard = new Intent(this, LiveTranslationTutorialCard.class);
            startActivity(translationTutCard);
            finish();
            pronunciationTut = true;
            numTaps = 0;
            numSwipes = 0;
            numTwoTaps = 0;
        }

    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.tutorial_activity);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        initialize();
        Intent swipeTutCard = new Intent(this, SwipeTutorialCard.class);
        startActivity(swipeTutCard);
    }

    @Override
    protected void onResume() {
        super.onResume();
        suggestedPhrasesList.reset();
    }

    private void initialize() {

        suggestedPhrasesList = (SuggestedPhrasesListView) this.findViewById(R.id.tut_suggested_phrases);

        ArrayList<Phrase> phrases = new ArrayList<Phrase>();
        phrases.add(new Phrase("I'm ready to order", "Estoy listo para ordenar", "Estoy Listo Para Ordenar.m4a"));
        phrases.add(new Phrase("I am still not ready to order", "Todavia no estoy listo para ordenar", "Todavia No Estoy Listo Para Ordenar.m4a"));
        phrases.add(new Phrase("Thank you", "Gracias", "Gracias.m4a"));

        phraseItemAdapter = new PhraseItemAdapter(this, R.layout.suggested_phrase_item, phrases);
        suggestedPhrasesList.setAdapter(phraseItemAdapter);
        suggestedPhrasesList.setRowHeight((int) getResources().getDimension(R.dimen.suggested_phrase_item_height));
        suggestedPhrasesList.setDisplayedPhraseChangedCallback(this);
        suggestedPhrasesList.setGestureCallback(this);


        prevPhrase = (TextSwitcher) findViewById(R.id.tut_prev_phrase);
        nextPhrase = (TextSwitcher) findViewById(R.id.tut_next_phrase);

        // Set the ViewFactory of the TextSwitcher that will create TextView object when asked
        prevPhrase.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {
                TextView myText = new TextView(TutorialActivity.this);
                myText.setEllipsize(TextUtils.TruncateAt.END);
                myText.setSingleLine();
                myText.setTextColor(0x803498db);
                myText.setGravity(Gravity.CENTER);
                return myText;
            }
        });
        nextPhrase.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {
                TextView myText = new TextView(TutorialActivity.this);
                myText.setEllipsize(TextUtils.TruncateAt.END);
                myText.setSingleLine();
                myText.setTextColor(0x803498db);
                myText.setGravity(Gravity.CENTER);
                return myText;
            }
        });

        // Declare the in and out animations and initialize them
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(this,android.R.anim.fade_out);

        // Set the animation type of textSwitcher
        nextPhrase.setInAnimation(in);
        nextPhrase.setOutAnimation(out);
        prevPhrase.setInAnimation(in);
        prevPhrase.setOutAnimation(out);

        updateDisplayedPhrases();
    }

    private void updateDisplayedPhrases() {
        prevPhrase.setText(suggestedPhrasesList.getPreviouslyDisplayedPhrase());
        nextPhrase.setText(suggestedPhrasesList.getNextDisplayedPhrase());
    }

}
