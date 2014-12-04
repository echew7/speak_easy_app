package speakeasy.com.speakeasy;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Eric on 12/4/14.
 */
public class ModeManager {

    interface OnModeChangedListener {
        void onModeChanged();
    }

    private static ModeManager modeManager;
    private static Timer modeManagerScheduler;
    private static Handler mHandler;
    private static ArrayList<OnModeChangedListener> callbacks;

    private final static long WAIT_TIME = 3000;

    private ModeManager() {
        Log.d("ModeManager", "Construction ModeManager");
        mHandler = new Handler(Looper.getMainLooper()) {
            /*
            * handleMessage() defines the operations to perform when
            * the Handler receives a new Message to process.
            */
            @Override
            public void handleMessage(Message message) {
                for (OnModeChangedListener callback : callbacks) {
                    if (callback != null) callback.onModeChanged();
                }
            }
        };
        callbacks = new ArrayList<OnModeChangedListener>();
    }

    public static ModeManager getInstance() {
        if (modeManager == null) modeManager = new ModeManager();
        return modeManager;
    }

    public void beginPhrasesMode() {
        startScheduling();
    }

    public void endPhrasesMode() {
        stopScheduling();
    }

    public void registerGesture() {
        restartScheduling();
    }

    public void registerCallback(OnModeChangedListener callback) {
        callbacks.add(callback);
    }

    private void startScheduling() {
        modeManagerScheduler = new Timer();
        modeManagerScheduler.schedule(new ModeManagerRunnable(mHandler), WAIT_TIME);
    }

    private void stopScheduling() {
        modeManagerScheduler.cancel();
    }

    private void restartScheduling() {
        stopScheduling();
        startScheduling();
    }

    private class ModeManagerRunnable extends TimerTask {

            private Handler modeManagerHandler;

            public ModeManagerRunnable(Handler modeManagerHandler) {
                this.modeManagerHandler = modeManagerHandler;
            }

            public void run() {
                // Moves the current Thread into the background
                android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
                modeManagerHandler.dispatchMessage(new Message());
                Log.d("D", "Dispatching message");
            }

    }

}
