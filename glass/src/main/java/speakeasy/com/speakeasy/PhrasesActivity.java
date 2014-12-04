package speakeasy.com.speakeasy;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

import java.util.ArrayList;

/**
 * Created by Eric on 12/2/14.
 */
public class PhrasesActivity extends BaseSpeakEasyActivity implements SuggestedPhrasesListView.onDisplayedPhraseChangedListener {

    private SuggestedPhrasesListView suggestedPhrasesList;
    private PhraseItemAdapter phraseItemAdapter;
    private TextView currentPhrase;
    private TextSwitcher prevPhrase;
    private TextSwitcher nextPhrase;

    @Override
    public void onDisplayedPhraseChanged() {
        updateDisplayedPhrases();
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.phrases_activity);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        initialize();
    }

    private void initialize() {
        suggestedPhrasesList = (SuggestedPhrasesListView) this.findViewById(R.id.suggested_phrases);
        ArrayList<Phrase> phrases = new ArrayList<Phrase>();
        phrases.add(new Phrase("Hello", "Hola"));
        phrases.add(new Phrase("Goodbye", "Adios"));
        phrases.add(new Phrase("How are you?", "Como estas?"));
        phraseItemAdapter = new PhraseItemAdapter(this, R.layout.suggested_phrase_item, phrases);
        suggestedPhrasesList.setAdapter(phraseItemAdapter);
        suggestedPhrasesList.setRowHeight((int) getResources().getDimension(R.dimen.suggested_phrase_item_height));
        suggestedPhrasesList.setDisplayedPhraseChangedCallback(this);

        currentPhrase = (TextView) findViewById(R.id.current_phrase);
        prevPhrase = (TextSwitcher) findViewById(R.id.prev_phrase);
        nextPhrase = (TextSwitcher) findViewById(R.id.next_phrase);

        // Set the ViewFactory of the TextSwitcher that will create TextView object when asked
        prevPhrase.setFactory(new ViewFactory() {

            public View makeView() {
                TextView myText = new TextView(PhrasesActivity.this);
                myText.setEllipsize(TextUtils.TruncateAt.END);
                myText.setSingleLine();
                myText.setTextColor(0x803498db);
                myText.setGravity(Gravity.CENTER);
                return myText;
            }
        });
        nextPhrase.setFactory(new ViewFactory() {

            public View makeView() {
                TextView myText = new TextView(PhrasesActivity.this);
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
        currentPhrase.setText(suggestedPhrasesList.getCurrentlyDisplayedPhrase());
        prevPhrase.setText(suggestedPhrasesList.getPreviouslyDisplayedPhrase());
        nextPhrase.setText(suggestedPhrasesList.getNextDisplayedPhrase());
    }

}