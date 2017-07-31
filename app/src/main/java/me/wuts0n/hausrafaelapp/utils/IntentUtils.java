package me.wuts0n.hausrafaelapp.utils;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.widget.Toast;

import me.wuts0n.hausrafaelapp.AboutUsActivity;
import me.wuts0n.hausrafaelapp.ContactActivity;
import me.wuts0n.hausrafaelapp.NewsActivity;
import me.wuts0n.hausrafaelapp.R;
import me.wuts0n.hausrafaelapp.TeamListActivity;
import me.wuts0n.hausrafaelapp.TeamMemberActivity;

public class IntentUtils {

    private static String mBusConnectionUrl;
    private static String mMapsUrl;


    public static String getBusConnectionUrl() {
        return mBusConnectionUrl;
    }

    public static void setBusConnectionUrl(CharSequence url) {
        mBusConnectionUrl = url.toString();
    }

    public static String getMapsUrl() {
        return mMapsUrl;
    }

    public static void setMapsUrl(CharSequence url) {
        mMapsUrl = url.toString();
    }


    /*
    * Check whether the intent is valid
    */
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
            intent.setType("*/*");
            return intent;
        }
        return null;
    }

    /*
    * Internet
    */
    @Nullable
    public static Intent getInternetIntent(Context context, CharSequence url) {
        if (url != null) {
            Uri uri = Uri.parse(url.toString());
            return new Intent(Intent.ACTION_DEFAULT, uri);
        } else {
            Toast.makeText(context, R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
        }
        return null;
    }
}
