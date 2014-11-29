package speakeasy.com.speakeasy;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Eric on 11/20/14.
 */
public class TranscriptItemAdapter extends ArrayAdapter<Transcript> {

    private Context context;
    private int layoutResourceId;
    private List<Transcript> transcriptsList;

    public TranscriptItemAdapter(Context context, int layoutResourceId, List<Transcript> transcriptsList) {
        super(context, layoutResourceId, transcriptsList);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.transcriptsList = transcriptsList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(layoutResourceId, parent, false);
        TextView transcriptTitleText = (TextView) rowView.findViewById(R.id.transcript_title);
        Transcript transcript = transcriptsList.get(position);
        transcriptTitleText.setText(transcript.getTitle());
        return rowView;
    }

}
