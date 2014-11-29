package speakeasy.com.speakeasy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * Created by truongt on 11/26/14.
 */
public class TranscriptImageFragment extends Fragment {
    public static final String TRANSCRIPT = "speakeasy.com.speakeasy.TranscriptImageFragment.TRANSCRIPT";
    Transcript transcript;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            transcript = (Transcript)savedInstanceState.getSerializable(TRANSCRIPT);
        }
        return inflater.inflate(R.layout.transcript_image, container, false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(TRANSCRIPT, transcript);
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle args = getArguments();
        if (args != null) {
            ImageView imageView = (ImageView) getActivity().findViewById(R.id.transcript_image_view);
            imageView.setImageDrawable(((Transcript)args.getSerializable(TRANSCRIPT)).getContent());
        }
    }
}
