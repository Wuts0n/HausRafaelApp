package me.wuts0n.hausrafaelapp.utils;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapUtils {

    public static Bitmap getBitMapFromByteArray(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

}
