package speakeasy.com.speakeasy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;

/**
 * Created by Eric on 11/18/14.
 */
public class PracticeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Practice Fragment", "onCreateView");
        return inflater.inflate(R.layout.practice_fragment, container, false);
    }
}
