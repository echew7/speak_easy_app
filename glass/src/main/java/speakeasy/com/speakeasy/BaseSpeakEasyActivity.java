package speakeasy.com.speakeasy;

import android.app.Activity;

/**
 * Created by Eric on 12/2/14.
 */
public class BaseSpeakEasyActivity extends Activity {

    public ModeManager getModeManager() {
        return ModeManager.getInstance();
    }

}
