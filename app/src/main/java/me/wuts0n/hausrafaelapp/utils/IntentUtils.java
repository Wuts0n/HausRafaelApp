package me.wuts0n.hausrafaelapp.utils;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import me.wuts0n.hausrafaelapp.AboutUsActivity;
import me.wuts0n.hausrafaelapp.ContactActivity;
import me.wuts0n.hausrafaelapp.NewsActivity;
import me.wuts0n.hausrafaelapp.TeamListActivity;
import me.wuts0n.hausrafaelapp.TeamMemberActivity;

public class IntentUtils {


    public static boolean isIntentValid(Context context, Intent intent) {
        return intent != null && intent.resolveActivity(context.getPackageManager()) != null;
    }


    /*
    * NewsActivity
    */
    public static Intent getNewsActivityIntent(Context context) {
        return new Intent(context, NewsActivity.class);
    }

    /*
    * TeamListActivity
    */
    public static Intent getTeamListActivityIntent(Context context) {
        return new Intent(context, TeamListActivity.class);
    }

    /*
    * ContactActivity
    */
    public static Intent getContactActivityIntent(Context context) {
        return new Intent(context, ContactActivity.class);
    }

    /*
    * AboutUsActivity
    */
    public static Intent getAboutUsActivityIntent(Context context) {
        return new Intent(context, AboutUsActivity.class);
    }

    /*
    * TeamMemberActivity
    */
    public static Intent getTeamMemberActivityIntent(Context context) {
        return new Intent(context, TeamMemberActivity.class);
    }

    /*
    * BusConnections
    *
    * http://live.vgn.de/abfahrten/?dm=de%3A09562%3A3472
    */
    public static Intent getBusConnectionsIntent() {
        Uri uri = new Uri.Builder()
                .scheme("http")             // ://
                .authority("live.vgn.de")   // /
                .appendPath("abfahrten")    // /
                .appendPath("")             // /
                .appendQueryParameter(/* ? */"dm", /* = */ "de%3A09562%3A3472")
                .build();
        Log.wtf(IntentUtils.class.getName(), uri.toString());
        return new Intent(Intent.ACTION_VIEW, uri);
    }

    /*
    * Maps
    */
    public static Intent getMapsIntent(CharSequence query) {
        Uri uri = new Uri.Builder()
                .scheme("geo")
                .authority("0,0")
                .appendQueryParameter("q", query.toString())
                .build();
        return new Intent(Intent.ACTION_VIEW, uri);
    }

    /*
    * Opens the custom Haus Rafael Orte map
    *
    * https://drive.google.com/open?id=14uXrqL_JiC0Ab-JflKJwfwan22Y&usp=sharing
    */
    public static Intent getDefaultMapsIntent() {
        Uri uri = new Uri.Builder()
                .scheme("https")
                .authority("drive.google.com")
                .appendPath("open")
                .appendQueryParameter("id", "14uXrqL_JiC0Ab-JflKJwfwan22Y")
                .appendQueryParameter("usp", "sharing")
                .build();
        return new Intent(Intent.ACTION_VIEW, uri);
    }

    /*
    * Phone dial
    */
    public static Intent getPhoneIntent(CharSequence number) {
        Uri uri = new Uri.Builder()
                .scheme("tel")
                .authority(number.toString().replaceAll("\\D+", ""))
                .build();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(uri);
        return intent;
    }

    /*
    * Email
    */
    @Nullable
    public static Intent getEmailIntent(CharSequence address) {
        String email = address.toString();
        if (email.matches("[\\p{Graph}&&[^@]]+@[\\p{Graph}&&[^\\.]]+\\..{2,}")) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            String[] recipients = {email};
            intent.putExtra(Intent.EXTRA_EMAIL, recipients);
            intent.putExtra(Intent.EXTRA_SUBJECT, "Entschuldigung für AT");
            intent.setType("*/*");
            return intent;
        }
        return null;
    }

    /*
    * Internet
    */
    public static Intent getInternetIntent(CharSequence url) {
        Uri uri = Uri.parse(url.toString());
        return new Intent(Intent.ACTION_DEFAULT, uri);
    }
}
