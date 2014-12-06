package speakeasy.com.speakeasy;

import android.os.Bundle;

/**
 * Created by Eric on 12/5/14.
 */
public class PronunciationTutorialCard extends TutorialCardActivity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setScript("Super! You can also hear pronunciations of the phrases to help you out while speaking. Just use a two finger tap to hear them!");
    }
}
