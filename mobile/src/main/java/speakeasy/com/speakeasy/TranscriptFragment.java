package speakeasy.com.speakeasy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Eric on 11/18/14.
 */
public class TranscriptFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Transcript Fragment", "onCreateView");
        return inflater.inflate(R.layout.transcript_fragment, container, false);
    }
}
