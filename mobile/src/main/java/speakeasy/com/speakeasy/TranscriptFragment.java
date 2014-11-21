package speakeasy.com.speakeasy;

import java.util.ArrayList;
import java.util.List;

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

    private List<Transcript> transcripts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        transcripts = new ArrayList<Transcript>();
        transcripts.add(new Transcript("El Tressider", getResources().getDrawable(R.drawable.el_tressider)));
        transcripts.add(new Transcript("La Panda Express", getResources().getDrawable(R.drawable.la_panda_express)));
        transcripts.add(new Transcript("El Bar", getResources().getDrawable(R.drawable.el_tressider)));
        transcripts.add(new Transcript("Playa del Carmen", getResources().getDrawable(R.drawable.la_panda_express)));
        transcripts.add(new Transcript("Palenque", getResources().getDrawable(R.drawable.el_tressider)));
        transcripts.add(new Transcript("Tulum", getResources().getDrawable(R.drawable.la_panda_express)));
        transcripts.add(new Transcript("Teotihuacan", getResources().getDrawable(R.drawable.el_tressider)));
        transcripts.add(new Transcript("Cabo San Lucas", getResources().getDrawable(R.drawable.la_panda_express)));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Transcript Fragment", "onCreateView");
        View view = inflater.inflate(R.layout.transcript_fragment, container, false);

        final ImageView transcriptContent = (ImageView) view.findViewById(R.id.transcript_content);
        ListView transcriptsList = (ListView) view.findViewById(R.id.transcript_list);
        TranscriptItemAdapter transcriptItemAdapter =
                new TranscriptItemAdapter(this.getActivity(), R.layout.transcript_item, transcripts);
        transcriptsList.setAdapter(transcriptItemAdapter);
        transcriptsList.setOnItemClickListener(new ListView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                transcriptContent.setImageDrawable(transcripts.get(position).getContent());
            }
        });
        return view;
    }
}
