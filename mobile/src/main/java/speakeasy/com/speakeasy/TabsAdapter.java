package speakeasy.com.speakeasy;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

/**
 * Created by Eric on 11/19/14.
 */
public class TabsAdapter extends FragmentStatePagerAdapter {
    public TabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int id) {
        Log.d("TabsAdapter", "Getting item " + id);
        Fragment fragment = null;
        switch (id) {
            case 0:
                return new ProgressFragment();
            case 1:
                return new MissedWordsFragment();
            case 2:
                return new TranscriptFragment();
            case 3:
                return new PracticeFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        Log.d("TabsAdapter", "Getting count");
        return 4;
    }
}
