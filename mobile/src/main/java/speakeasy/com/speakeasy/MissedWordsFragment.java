package speakeasy.com.speakeasy;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Eric on 11/18/14.
 */
public class MissedWordsFragment extends Fragment {

    private List<MissedWord> missedWords;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        missedWords = new ArrayList<MissedWord>();
        missedWords.add(new MissedWord("Queso", "Cheese", "Queso.m4a"));
        missedWords.add(new MissedWord("Hevir", "To boil", "Hervir.m4a"));
        missedWords.add(new MissedWord("Llamar", "To call", "Llamar.m4a"));
        missedWords.add(new MissedWord("Delicado", "Delicate", "Delicado.m4a"));
        missedWords.add(new MissedWord("Verduras", "Vegetables", "Verduras.m4a"));
        missedWords.add(new MissedWord("Salida", "Exit", "Salida.m4a"));
        missedWords.add(new MissedWord("Hola", "Hello", "Hola.m4a"));
        missedWords.add(new MissedWord("Gracias", "Thank you", "Gracias.m4a"));
        missedWords.add(new MissedWord("Agua", "Water", "Agua.m4a"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Missed Words Fragment", "onCreateView");
        View view = inflater.inflate(R.layout.missed_words_fragment, container, false);

        ListView missedWordsList = (ListView) view.findViewById(R.id.missed_words_list);
        MissedWordItemAdapter missedWordItemAdapter =
                new MissedWordItemAdapter(this.getActivity(), R.layout.missed_word_item, missedWords);
        missedWordsList.setAdapter(missedWordItemAdapter);

        return view;
    }
}
