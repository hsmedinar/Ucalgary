package ucalgary.stbig.com.ucalgary.objects;

import java.io.Serializable;

/**
 * Created by helbert on 22/10/15.
 */
public class Events implements Serializable {

    String title;
    String body;
    String date;
    String locate;
    String browser;

    public Events(String title, String body, String date, String locate, String browser){
        this.title=title;
        this.body=body;
        this.date=date;
        this.locate=locate;
        this.browser=browser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocate() {
        return locate;
    }

    public void setLocate(String locate) {
        this.locate = locate;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }
}
