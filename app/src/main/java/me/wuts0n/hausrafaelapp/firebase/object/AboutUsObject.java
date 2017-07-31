package me.wuts0n.hausrafaelapp.firebase.object;


import android.support.annotation.NonNull;

public class AboutUsObject implements Comparable {
    private String heading;
    private String text;
    private int importance;


    public AboutUsObject() {
    }

    public AboutUsObject(String heading, String text, int importance) {
        this.heading = heading;
        this.text = text;
        this.importance = importance;
    }


    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading.replaceAll("\\\\n", "\n");
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text.replaceAll("\\\\n", "\n");
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }


    @Override
    public int compareTo(@NonNull Object obj) {
        if (obj instanceof AboutUsObject) {
            AboutUsObject otherOne = (AboutUsObject) obj;
            return otherOne.getImportance() - this.getImportance();
        }
        return 1;
    }

}
