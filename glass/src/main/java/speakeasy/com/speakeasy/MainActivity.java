package speakeasy.com.speakeasy;

import android.os.Bundle;
import android.view.Menu;
import android.view.WindowManager;

/**
 * Created by Eric on 12/1/14.
 */
public class MainActivity extends BaseSpeakEasyActivity {

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        openOptionsMenu();
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.main_activity);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
        finish();
    }
}
