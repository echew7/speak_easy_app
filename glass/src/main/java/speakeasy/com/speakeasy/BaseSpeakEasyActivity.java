package speakeasy.com.speakeasy;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Eric on 12/2/14.
 */
public class BaseSpeakEasyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ModeManager getModeManager() {
        return ModeManager.getInstance();
    }

}
