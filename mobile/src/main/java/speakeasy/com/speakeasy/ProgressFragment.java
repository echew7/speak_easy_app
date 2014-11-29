package speakeasy.com.speakeasy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;


/**
 * Created by Eric on 11/18/14.
 */
public class ProgressFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Progress Fragment", "onCreateView");
        final View progressView = inflater.inflate(R.layout.progress_fragment, container, false);
        Spinner newWordsSpinner = (Spinner) progressView.findViewById(R.id.new_words_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.time_choices_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        newWordsSpinner.setAdapter(adapter);
        newWordsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                String choice = (String)parent.getItemAtPosition(pos);
                ImageView newWordsImage = (ImageView)progressView.findViewById(R.id.new_words_image);
                if(choice.equals("By day")) {
                    newWordsImage.setImageResource(R.drawable.new_words_daily);
                } else if(choice.equals("By month")) {
                    newWordsImage.setImageResource(R.drawable.new_words_by_month);
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
                // nothing
            }

        });

        Spinner missedWordsSpinner = (Spinner) progressView.findViewById(R.id.missed_words_spinner);
        missedWordsSpinner.setAdapter(adapter);
        missedWordsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                String choice = (String)parent.getItemAtPosition(pos);
                ImageView missedWordsImage = (ImageView)progressView.findViewById(R.id.missed_words_image);
                if(choice.equals("By day")) {
                    missedWordsImage.setImageResource(R.drawable.missed_words_daily);
                } else if(choice.equals("By month")) {
                    missedWordsImage.setImageResource(R.drawable.missed_words_by_month);
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
                // nothing
            }

        });

        return progressView;
    }
}
