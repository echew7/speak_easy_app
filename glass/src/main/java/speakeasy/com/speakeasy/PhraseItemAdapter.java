package speakeasy.com.speakeasy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Eric on 12/4/14.
 */
public class PhraseItemAdapter extends ArrayAdapter<Phrase> {
    private Context context;
    private int layoutResourceId;
    private List<Phrase> phraseList;
    private Boolean nativeMode = true;

    public PhraseItemAdapter(Context context, int layoutResourceId, List<Phrase> phraseList) {
        super(context, layoutResourceId, phraseList);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.phraseList = phraseList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(layoutResourceId, parent, false);
        TextView suggestedPhrase = (TextView) rowView.findViewById(R.id.suggested_phrase);

        String phrase = nativeMode ?
                phraseList.get(position).getNativePhrase() : phraseList.get(position).getTranslatedPhrase();
        suggestedPhrase.setText(phrase);

        return rowView;
    }

    public void toggleMode() {
        nativeMode = !nativeMode;
    }

    public Boolean isNativeMode() {
        return nativeMode;
    }
}
