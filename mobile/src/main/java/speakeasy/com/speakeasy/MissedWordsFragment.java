package speakeasy.com.speakeasy;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Eric on 11/18/14.
 */
public class MissedWordsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Missed Words Fragment", "onCreateView");
        return inflater.inflate(R.layout.missed_words_fragment, container, false);
    }
}
