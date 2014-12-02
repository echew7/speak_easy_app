package speakeasy.com.speakeasy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

/**
 * Created by Eric on 12/1/14.
 */
public class MainActivity extends Activity {

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
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        // TODO: Add some way to detect if this is coming from the MainActivity
        // This seems like its a bit janky
        if (this instanceof MainActivity) {
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.tutorial_mode:
                // Tutorial selected
                Intent tutorial = new Intent(this, TutorialActivity.class);
                startActivity(tutorial);
                return true;
            case R.id.phrases_mode:
                // Phrases selected
                Intent phrases = new Intent(this, PhrasesActivity.class);
                startActivity(phrases);
                return true;
            case R.id.translation_mode:
                // Translation selected
                Intent translation = new Intent(this, TranslationActivity.class);
                startActivity(translation);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
