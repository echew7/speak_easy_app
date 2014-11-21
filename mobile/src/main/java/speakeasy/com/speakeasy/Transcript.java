package speakeasy.com.speakeasy;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by Eric on 11/20/14.
 */
public class Transcript implements Serializable {

    private String title;
    private Drawable content;

    public Transcript(String title, Drawable content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public Drawable getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(Drawable content) {
        this.content = content;
    }
}
