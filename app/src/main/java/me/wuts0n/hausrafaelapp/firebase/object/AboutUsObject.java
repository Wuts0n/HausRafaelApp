package me.wuts0n.hausrafaelapp.firebase.object;


public class AboutUsObject {
    private String heading;
    private String text;


    public AboutUsObject() {
    }

    public AboutUsObject(String heading, String text) {
        this.heading = heading;
        this.text = text;
    }


    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
