package speakeasy.com.speakeasy;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Eric on 11/20/14.
 */
public class MissedWordItemAdapter extends ArrayAdapter<MissedWord> {

    private Context context;
    private int layoutResourceId;
    private List<MissedWord> missedWordsList;
    private MediaPlayer mp;

    public MissedWordItemAdapter(Context context, int layoutResourceId, List<MissedWord> missedWordsList) {
        super(context, layoutResourceId, missedWordsList);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.missedWordsList = missedWordsList;
        this.mp = new MediaPlayer();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(layoutResourceId, parent, false);
        TextView missedWordText = (TextView) rowView.findViewById(R.id.missed_word);
        TextView translationText = (TextView) rowView.findViewById(R.id.translation);
        ImageView audioButton = (ImageView) rowView.findViewById(R.id.audio_button);

        final MissedWord missedWord = missedWordsList.get(position);
        missedWordText.setText(missedWord.getMissedWord());
        translationText.setText(missedWord.getTranslation());

        audioButton.setClickable(true);
        audioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mp.stop();
                    mp.reset();
                    AssetFileDescriptor afd;
                    afd = context.getAssets().openFd(missedWord.getAudioFile());
                    mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                    mp.prepare();
                    mp.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        return rowView;
    }
}
