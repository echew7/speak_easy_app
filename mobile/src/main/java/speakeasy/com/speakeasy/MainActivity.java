package speakeasy.com.speakeasy;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener, TranscriptListFragment.OnTranscriptSelectedListener {

    ViewPager activityTabs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityTabs = (ViewPager) findViewById(R.id.activity_tabs);

        final ActionBar actionBar=getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        addTabs(actionBar);
        activityTabs.setAdapter(new TabsAdapter(getSupportFragmentManager()));

        activityTabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
                Log.d("Main Activity","onPageScrolled "+ i + " " + v + " " + i2);
            }

            @Override
            public void onPageSelected(int i) {
                actionBar.setSelectedNavigationItem(i);
                Log.d("Main Activity","onPageSelected "+i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                if(i==ViewPager.SCROLL_STATE_IDLE)
                    Log.d("Main Activity","onPageScrollStateChanged scroll state idle "+i);
                if(i==ViewPager.SCROLL_STATE_DRAGGING)
                    Log.d("Main Activity","onPageScrollStateChanged scroll state dragging "+i);
                if(i==ViewPager.SCROLL_STATE_SETTLING)
                    Log.d("Main Activity","onPageScrollStateChanged scroll state settling "+i);
            }
        });

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        activityTabs.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    private void addTabs(ActionBar actionBar)
    {
        ActionBar.Tab tab1=actionBar.newTab();
        tab1.setIcon(R.drawable.progress_tab);
        tab1.setTabListener(this);

        ActionBar.Tab tab2=actionBar.newTab();
        tab2.setIcon(R.drawable.missed_words_tab);
        tab2.setTabListener(this);

        ActionBar.Tab tab3=actionBar.newTab();
        tab3.setIcon(R.drawable.transcripts_tab);
        tab3.setTabListener(this);


        actionBar.addTab(tab1);
        actionBar.addTab(tab2);
        actionBar.addTab(tab3);
    }

    @Override
    public void onTranscriptSelected(Transcript transcript) {
        TranscriptImageFragment imageFragment = new TranscriptImageFragment();
        Bundle args = new Bundle();
        args.putSerializable(TranscriptImageFragment.TRANSCRIPT, transcript);
        imageFragment.setArguments(args);

        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.transcript_container, imageFragment);
        transaction.addToBackStack(null);

        transaction.commit();



    }
}
