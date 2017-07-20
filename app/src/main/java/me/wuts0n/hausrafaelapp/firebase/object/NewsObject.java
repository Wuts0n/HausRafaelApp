package me.wuts0n.hausrafaelapp.firebase.object;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NewsObject {

    private int severity;
    private String text;
    private String date;


    public NewsObject() {
    }

    public NewsObject(int severity, String text, String date) {
        this.severity = severity;
        this.text = text;
        this.date = date;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDateWithMillis(long timeMillis) {
        Date date = new Date(timeMillis);
        this.date = DateFormat
                .getDateInstance(DateFormat.MEDIUM, Locale.getDefault()).format(date)
                .concat(" ")
                .concat(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(date));
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text.replaceAll("\\\\n", "\n");
    }
}
