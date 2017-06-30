package me.wuts0n.hausrafaelapp.database;

import android.provider.BaseColumns;

/**
 * column names for the team table
 */

public final class DBMemberEntry implements BaseColumns {

    public static final String TABLE_NAME = "team";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PICTURE = "picture";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_FAX = "fax";
    public static final String COLUMN_EMAIL = "email";

}
