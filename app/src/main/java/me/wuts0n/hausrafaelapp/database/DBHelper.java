package me.wuts0n.hausrafaelapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 4;
    private static final String DB_NAME = "db.rafael";
    private static final String TAG = DBHelper.class.getName();


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        // create member table
        db.execSQL("CREATE TABLE " + DBMemberEntry.TABLE_NAME + " (" +
                DBMemberEntry._ID                   + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DBMemberEntry.COLUMN_NAME           + " TEXT NOT NULL, "                     +
                DBMemberEntry.COLUMN_DESCRIPTION    + " TEXT, "                              +
                DBMemberEntry.COLUMN_PICTURE        + " BLOB, "                              +
                DBMemberEntry.COLUMN_PHONE          + " TEXT, "                              +
                DBMemberEntry.COLUMN_FAX            + " TEXT, "                              +
                DBMemberEntry.COLUMN_EMAIL          + " TEXT, "                              +
                // Override name if exists
                " UNIQUE (" + DBMemberEntry.COLUMN_NAME + ") ON CONFLICT REPLACE);");
        Log.i(TAG, "Created table \"" + DBMemberEntry.TABLE_NAME + "\" successfully.");

        // create contact table
        db.execSQL("CREATE TABLE " + DBContactEntry.TABLE_NAME + " (" +
                DBContactEntry._ID                   + " INTEGER PRIMARY KEY, " +
                DBContactEntry.COLUMN_DESCRIPTION    + " TEXT, "                              +
                DBContactEntry.COLUMN_PICTURE        + " BLOB, "                              +
                DBContactEntry.COLUMN_LOCATION       + " TEXT, "                              +
                DBContactEntry.COLUMN_PHONE          + " TEXT, "                              +
                DBContactEntry.COLUMN_FAX            + " TEXT, "                              +
                DBContactEntry.COLUMN_EMAIL          + " TEXT, "                              +
                DBContactEntry.COLUMN_WEBSITE        + " TEXT, "                              +
                // Override if exists already
                " UNIQUE (" + DBContactEntry._ID + ") ON CONFLICT REPLACE);");
        Log.i(TAG, "Created table \"" + DBContactEntry.TABLE_NAME + "\" successfully.");

        // create about_us table
        db.execSQL("CREATE TABLE " + DBAboutUsEntry.TABLE_NAME + " (" +
                DBAboutUsEntry._ID                   + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DBAboutUsEntry.COLUMN_HEADING        + " TEXT NOT NULL, "                     +
                DBAboutUsEntry.COLUMN_TEXT           + " TEXT, "                              +
                // Override entry if heading exists
                " UNIQUE (" + DBAboutUsEntry.COLUMN_HEADING + ") ON CONFLICT REPLACE);");
        Log.i(TAG, "Created table \"" + DBAboutUsEntry.TABLE_NAME + "\" successfully.");

        // create news table
        db.execSQL("CREATE TABLE " + DBNewsEntry.TABLE_NAME + " (" +
                DBNewsEntry._ID                   + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DBNewsEntry.COLUMN_TIMESTAMP      + " TEXT NOT NULL, "                     +
                DBNewsEntry.COLUMN_SEVERITY       + " INTEGER NOT NULL, "                  +
                DBNewsEntry.COLUMN_TEXT           + " TEXT NOT NULL);");
        Log.i(TAG, "Created table \"" + DBNewsEntry.TABLE_NAME + "\" successfully.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBMemberEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DBContactEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DBAboutUsEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DBNewsEntry.TABLE_NAME);
        onCreate(db);
    }
}
