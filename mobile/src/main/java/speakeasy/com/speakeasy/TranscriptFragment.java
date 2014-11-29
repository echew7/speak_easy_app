package speakeasy.com.speakeasy;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * Created by Eric on 11/18/14.
 */
public class TranscriptFragment extends Fragment {


    OnTranscriptSelectedListener callback;

    public interface OnTranscriptSelectedListener {
        public void onTranscriptSelected(Transcript transcript);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            callback = (OnTranscriptSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnTranscriptSelectedListener");
        }
    }

    private List<Transcript> transcripts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        transcripts = new ArrayList<Transcript>();
        transcripts.add(new Transcript("El Centro Comercial", getResources().getDrawable(R.drawable.shopping)));
        transcripts.add(new Transcript("Casa Zapata", getResources().getDrawable(R.drawable.door)));
        transcripts.add(new Transcript("Subway", getResources().getDrawable(R.drawable.restaurant)));
        transcripts.add(new Transcript("Playa del Carmen", getResources().getDrawable(R.drawable.shopping)));
        transcripts.add(new Transcript("Palenque", getResources().getDrawable(R.drawable.door)));
        transcripts.add(new Transcript("Tulum", getResources().getDrawable(R.drawable.restaurant)));
        transcripts.add(new Transcript("Teotihuacan", getResources().getDrawable(R.drawable.shopping)));
        transcripts.add(new Transcript("Cabo San Lucas", getResources().getDrawable(R.drawable.door)));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Transcript Fragment", "onCreateView");
        View view = inflater.inflate(R.layout.transcript_fragment, container, false);

        ListView transcriptsList = (ListView) view.findViewById(R.id.transcript_list);
        TranscriptItemAdapter transcriptItemAdapter =
                new TranscriptItemAdapter(this.getActivity(), R.layout.transcript_item, transcripts);
        transcriptsList.setAdapter(transcriptItemAdapter);
        transcriptsList.setOnItemClickListener(new ListView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                //transcriptContent.setImageDrawable(transcripts.get(position).getContent());
                callback.onTranscriptSelected(transcripts.get(position));
            }
        });
        return view;
    }

}
