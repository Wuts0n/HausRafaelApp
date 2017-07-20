package me.wuts0n.hausrafaelapp.database;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class NewsTable implements BaseColumns {

    public static final String COLUMN_TEXT = "text";
    public static final String COLUMN_SEVERITY = "severity";
    public static final String COLUMN_DATE = "date";
    protected static final String COLUMN_KEY = "key";
    protected static final String NEWS_TABLE_NAME = "table_news";
    private static final int DEFAULT_MESSAGE_LIMIT = 42;
    private final SQLiteDatabase mReadableDatabase;
    private final SQLiteDatabase mWritableDatabase;


    public NewsTable(SQLiteDatabase readableDatabase, SQLiteDatabase writableDatabase) {
        mReadableDatabase = readableDatabase;
        mWritableDatabase = writableDatabase;
    }


    protected static void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + NEWS_TABLE_NAME
                + " ("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_KEY + " TEXT NOT NULL, "
                + COLUMN_TEXT + " TEXT, "
                + COLUMN_SEVERITY + " INTEGER, "
                + COLUMN_DATE + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
                + ");");
    }

    protected static void dropTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + NEWS_TABLE_NAME);
    }

    public Cursor selectAll() {
        return mReadableDatabase.query(NEWS_TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                _ID.concat(" DESC"),
                String.valueOf(DEFAULT_MESSAGE_LIMIT));
    }

    protected Cursor select(CharSequence key) {
        return mReadableDatabase.query(NEWS_TABLE_NAME,
                null,
                COLUMN_KEY.concat(" = '" + key + "'"),
                null,
                null,
                null,
                null);
    }

    public boolean existsRow(CharSequence key) {
        return select(key).getCount() > 0;
    }

    public int getID(CharSequence key) {
        Cursor cursor = select(key);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            int columnId = cursor.getColumnIndex(_ID);
            return cursor.getInt(columnId);
        }
        return -1;
    }

    public long insertRow(CharSequence key,
                          CharSequence text,
                          int severity) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_KEY, key.toString());
        values.put(COLUMN_TEXT, (text != null ? text.toString() : ""));
        values.put(COLUMN_SEVERITY, severity);
        return mWritableDatabase.insert(NEWS_TABLE_NAME, null, values);
    }

    public long updateRow(CharSequence key,
                          CharSequence text,
                          int severity) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_KEY, key.toString());
        values.put(COLUMN_TEXT, (text != null ? text.toString() : ""));
        values.put(COLUMN_SEVERITY, severity);
        return mWritableDatabase.update(NEWS_TABLE_NAME,
                values,
                COLUMN_KEY.concat(" = '").concat(key.toString()).concat("'"),
                null);
    }

    public int deleteRow(CharSequence key) {
        return mWritableDatabase.delete(NEWS_TABLE_NAME,
                COLUMN_KEY.concat(" = '").concat(key.toString()).concat("'"),
                null);
    }
}
