package speakeasy.com.speakeasy;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
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
public class PhrasesActivity extends BaseSpeakEasyActivity implements SuggestedPhrasesListView.OnDisplayedPhraseChangedListener, ModeManager.OnModeChangedListener {

    private ModeManager modeManager;
    private SuggestedPhrasesListView suggestedPhrasesList;
    private PhraseItemAdapter phraseItemAdapter;
    private TextSwitcher prevPhrase;
    private TextSwitcher nextPhrase;

    @Override
    public void onDisplayedPhraseChanged() {
        Log.d("R", "Registering Gesture");
        updateDisplayedPhrases();
    }

    @Override
    public void onModeChanged() {
        Log.d("S", "Starting activity translate");
        Intent translation = new Intent(this, TranslationActivity.class);
        startActivity(translation);
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.phrases_activity);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        initialize();
    }

    @Override
    protected void onResume() {
        super.onResume();
        modeManager.beginPhrasesMode();
    }

    @Override
    protected void onPause() {
        super.onResume();
        Log.d("P", "Pausing");
        modeManager.endPhrasesMode();
    }

    private void initialize() {

        modeManager = getModeManager();
        modeManager.registerCallback(this);

        suggestedPhrasesList = (SuggestedPhrasesListView) this.findViewById(R.id.suggested_phrases);
        ArrayList<Phrase> phrases = new ArrayList<Phrase>();
        phrases.add(new Phrase("I'm ready to order", "Estoy listo para ordenar", "Estoy Listo Para Ordenar.m4a"));
        phrases.add(new Phrase("I am still not ready to order", "Todavia no estoy listo para ordenar", "Todavia No Estoy Listo Para Ordenar.m4a"));
        phrases.add(new Phrase("Thank you", "Gracias", "Gracias.m4a"));
        phrases.add(new Phrase("No thank you", "No gracias", "No Gracias.m4a"));
        phrases.add(new Phrase("Can I have a coffee please?", "Me puede dar cafe por favor?", "Me puede Dar Cafe Por Favor.m4a"));
        phrases.add(new Phrase("Can I have the chicken sandwich?", "Me puede dar el sandwich de pollo?", "Me Puede Dar El Sandwich De Pollo.m4a"));
        phrases.add(new Phrase("Can you bring me a glass of wine?", "Me puede trader un vaso de vino?", "Me Puede Trader Un Vaso De Vino.m4a"));
        phrases.add(new Phrase("Can you bring me the check?", "Me puede traer la cuenta?", "Me Puede Traer La Cuenta.m4a"));
        phrases.add(new Phrase("Yes. Can I have some water?", "Si me puede dar agua?", "Si Me Puede Dar Agua.m4a"));

        phraseItemAdapter = new PhraseItemAdapter(this, R.layout.suggested_phrase_item, phrases);
        suggestedPhrasesList.setAdapter(phraseItemAdapter);
        suggestedPhrasesList.setRowHeight((int) getResources().getDimension(R.dimen.suggested_phrase_item_height));
        suggestedPhrasesList.setDisplayedPhraseChangedCallback(this);

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
        prevPhrase.setText(suggestedPhrasesList.getPreviouslyDisplayedPhrase());
        nextPhrase.setText(suggestedPhrasesList.getNextDisplayedPhrase());
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        super.onGenericMotionEvent(event);
        modeManager.registerGesture();
        return false;
    }
}