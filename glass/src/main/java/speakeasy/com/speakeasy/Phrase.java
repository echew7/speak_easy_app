package speakeasy.com.speakeasy;

/**
 * Created by Eric on 12/4/14.
 */
public class Phrase {

    private String nativePhrase;

    private String translatedPhrase;

    public Phrase(String nativePhrase, String translatedPhrase) {
        this.nativePhrase = nativePhrase;
        this.translatedPhrase = translatedPhrase;
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

}
