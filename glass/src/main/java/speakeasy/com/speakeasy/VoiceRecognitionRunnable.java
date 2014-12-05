package speakeasy.com.speakeasy;

import android.content.Context;
import android.os.Handler;

import com.google.glass.logging.FormattingLogger;
import com.google.glass.logging.FormattingLoggers;
import com.google.glass.logging.Log;
import com.google.glass.voice.VoiceCommand;
import com.google.glass.voice.VoiceConfig;
import com.google.glass.voice.VoiceInputHelper;
import com.google.glass.voice.VoiceListener;

import java.util.ArrayList;

/**
 * Created by Eric on 12/4/14.
 */
public class VoiceRecognitionRunnable extends VoiceListener.SimpleVoiceListener implements Runnable {

    interface OnVoiceRecognizedListener {
        void onVoiceRecognized(String recognized);
    }

    // Globals
    private Context context;

    //Continuous listening
    private VoiceInputHelper voiceInputHelper;
    private VoiceConfig voiceConfig;
    private ArrayList<String> voiceConfigList;
    private ArrayList<String> commands;
    private OnVoiceRecognizedListener callback;

    //Handlers
    private Handler handler = null;

    public VoiceRecognitionRunnable(Context context, ArrayList<String> commands) {
        this.context = context;
        this.commands = commands;
    }

    /*
	 * This function starts the voice recognition thread.
	 * It also creates a handler which is associated with this thread's
	 * looper.
	 */
    @Override
    public void run() {
        initializeVoiceCommands();
    }

    public void setOnVoiceRecognizedListener(OnVoiceRecognizedListener callback) {
        this.callback = callback;
    }

    private void initializeVoiceCommands() {
        voiceConfigList = commands;
        voiceConfig = new VoiceConfig(voiceConfigList.toArray(new String[voiceConfigList.size()]));
        voiceConfig.setShouldSaveAudio(false);
        //Remove, replace, and restart the voice listener
        voiceInputHelper = new VoiceInputHelper(context, this);
        voiceInputHelper.setVoiceConfig(voiceConfig);
    }

    /*
     * Returns this thread's handler
     */
    public Handler getHandler() {
        return handler;
    }

    /*
	 * This function accepts a string and adds that string to
	 * the vocabulary used by the voice listener
	 */
    public void addToVocab(String newVoiceCommand) {
        //Add new command to global commands
        commands.add(newVoiceCommand);
        initializeVoiceCommands();
    }

    /*
     * This function accepts a string and removes that string from
     * the vocabulary used by the voice listener
     */
    public void removeFromVocab(String vcToRemove) {
        commands.remove(vcToRemove);
        initializeVoiceCommands();
    }

    /*
	 * This function accepts a hash map with keys of type String and values of type Runnable
	 * The key is what the voice listener will listen for and the Runnable value is what will
	 * be executed upon detection of the key.
	 */
    public void setVocab(ArrayList<String> vocab) {
        commands = vocab;
        initializeVoiceCommands();
    }

    //This method handles recognized voice commands
    @Override
    public VoiceConfig onVoiceCommand(VoiceCommand vc) {
        String recognizedStr = vc.getLiteral();

        Log.d("VoiceRecognitionThread", "Recognized text: " + recognizedStr);

        callback.onVoiceRecognized(recognizedStr);

        return null;
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public FormattingLogger getLogger() {
        return FormattingLoggers.getContextLogger();
    }
}