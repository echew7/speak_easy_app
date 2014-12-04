package speakeasy.com.speakeasy;

import android.os.Bundle;
import android.view.WindowManager;

/**
 * Created by Eric on 12/2/14.
 */
public class TranslationActivity extends BaseSpeakEasyActivity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.translation_activity);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

}
