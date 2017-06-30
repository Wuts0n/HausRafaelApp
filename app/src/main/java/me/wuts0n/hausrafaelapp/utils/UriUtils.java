package me.wuts0n.hausrafaelapp.utils;


import android.net.Uri;

public class UriUtils {

    public static String getAuthority(CharSequence uri) {
        String authority = Uri.parse(uri.toString()).getAuthority();
        if(authority == null || authority.equals("")) {
            return uri.toString();
        }
        authority = authority.replaceAll("w{3}\\.", "").replaceAll("\\s+", "");
        return authority;
    }
}
