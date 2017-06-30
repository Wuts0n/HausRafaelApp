package me.wuts0n.hausrafaelapp.database;

import android.provider.BaseColumns;

/**
 * column names for the contact table
 */

public final class DBContactEntry implements BaseColumns {

    public static final String TABLE_NAME = "contact";

    public static final String COLUMN_PICTURE = "picture";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_FAX = "fax";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_WEBSITE = "website";

}
