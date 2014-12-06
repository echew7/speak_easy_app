package speakeasy.com.speakeasy;

import android.os.Bundle;

/**
 * Created by Eric on 12/5/14.
 */
public class SwipeTutorialCard extends TutorialCardActivity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setScript("Glad that you decided to join us! We'll start in Phrases Mode, which displays context sensitive phrases. Try it out! Swipe forward and backwards through the phrases.");
    }
}
