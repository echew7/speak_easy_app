package speakeasy.com.speakeasy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

/**
 * Created by truongt on 11/27/14.
 */
public class TranscriptContainerFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.transcript_container, container, false);

        TranscriptListFragment transcriptListFragment = new TranscriptListFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.transcript_container, transcriptListFragment).commit();


        return view;
    }
}
