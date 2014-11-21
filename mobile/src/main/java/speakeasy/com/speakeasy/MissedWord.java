package speakeasy.com.speakeasy;

/**
 * Created by Eric on 11/20/14.
 */
public class MissedWord {

    private String missedWord;
    private String translation;
    private String audioFile;

    public MissedWord(String missedWord, String translation, String audioFile) {
        this.missedWord = missedWord;
        this.translation = translation;
        this.audioFile = audioFile;
    }

    public String getMissedWord() {
        return missedWord;
    }

    public String getTranslation() {
        return translation;
    }

    public String getAudioFile() {
        return audioFile;
    }

    public void setMissedWord(String missedWord) {
        this.missedWord = missedWord;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public void setAudioFile(String audioFile) {
        this.translation = audioFile;
    }
}
