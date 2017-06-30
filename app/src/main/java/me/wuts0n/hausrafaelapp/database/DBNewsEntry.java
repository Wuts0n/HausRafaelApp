package me.wuts0n.hausrafaelapp.database;

import android.provider.BaseColumns;

/**
 * column names for the news table
 */

public final class DBNewsEntry implements BaseColumns {

    public static final String TABLE_NAME = "news";

    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String COLUMN_SEVERITY = "severity";
    public static final String COLUMN_TEXT = "text";

    public enum Severity {
        Info (0),
        Attention (1),
        Warning (2);

        private final int value;

        Severity(int val) {
            value = val;
        }

        public int getValue() {
            return value;
        }
    }

}
