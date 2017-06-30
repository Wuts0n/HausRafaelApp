package me.wuts0n.hausrafaelapp.utils;


import android.support.v7.app.AppCompatActivity;

import me.wuts0n.hausrafaelapp.R;


public class NavigationUtils {


    public static void setNavigateNextTransition(AppCompatActivity activity) {
        activity.overridePendingTransition(R.anim.enter, R.anim.exit);
    }

    public static void setNavigatePreviousTransition(AppCompatActivity activity) {
        activity.overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

}
