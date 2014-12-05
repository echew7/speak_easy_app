package speakeasy.com.speakeasy;

/**
 * Created by Eric on 12/4/14.
 */
public class Phrase {

    private String nativePhrase;
    private String translatedPhrase;
    private String nativePhraseAudio;
    private String translatedPhraseAudio;

    public Phrase(String nativePhrase, String translatedPhrase) {
        this.nativePhrase = nativePhrase;
        this.translatedPhrase = translatedPhrase;
    }

    public Phrase(String nativePhrase, String translatedPhrase, String nativePhraseAudio, String translatedPhraseAudio) {
        this.nativePhrase = nativePhrase;
        this.translatedPhrase = translatedPhrase;
        this.nativePhraseAudio = nativePhraseAudio;
        this.translatedPhraseAudio = translatedPhraseAudio;
    }

    public String getNativePhrase() {
        return nativePhrase;
    }

    public void setNativePhrase(String nativePhrase) {
        this.nativePhrase = nativePhrase;
    }

    public String getTranslatedPhrase() {
        return translatedPhrase;
    }

    public void setTranslatedPhrase(String translatedPhrase) {
        this.translatedPhrase = translatedPhrase;
    }

    public String getTranslatedPhraseAudio() {
        return translatedPhraseAudio;
    }

    public void setTranslatedPhraseAudio(String translatedPhraseAudio) {
        this.translatedPhraseAudio = translatedPhraseAudio;
    }

    public String getNativePhraseAudio() {
        return nativePhraseAudio;
    }

    public void setNativePhraseAudio(String nativePhraseAudio) {
        this.nativePhraseAudio = nativePhraseAudio;
    }

}
