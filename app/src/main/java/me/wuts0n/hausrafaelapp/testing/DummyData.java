package me.wuts0n.hausrafaelapp.testing;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteReadOnlyDatabaseException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import me.wuts0n.hausrafaelapp.R;
import me.wuts0n.hausrafaelapp.database.DBAboutUsEntry;
import me.wuts0n.hausrafaelapp.database.DBContactEntry;
import me.wuts0n.hausrafaelapp.database.DBMemberEntry;
import me.wuts0n.hausrafaelapp.database.DBNewsEntry;
import me.wuts0n.hausrafaelapp.database.DBNewsEntry.Severity;

import java.io.ByteArrayOutputStream;


public class DummyData {

    private SQLiteDatabase mWritableDB;
    private Context mContext;


    public DummyData(Context context, SQLiteDatabase db) {

        if (db.isReadOnly())
            throw new SQLiteReadOnlyDatabaseException("No Writeable Database.");
        else
            mWritableDB = db;
        mContext = context;

        fillAboutUs();
        Log.wtf(this.getClass().getName(), "filled AboutUs successfully.");
        fillTeamList();
        Log.wtf(this.getClass().getName(), "filled TeamList successfully.");
        fillContact();
        Log.wtf(this.getClass().getName(), "filled Contact successfully.");
        fillNews();
        Log.wtf(this.getClass().getName(), "filled News successfully.");
    }


    private void fillAboutUs() {
        ContentValues val = new ContentValues();
        val.put(DBAboutUsEntry.COLUMN_HEADING, "Haus Raffi");
        val.put(DBAboutUsEntry.COLUMN_TEXT, "tools:text='Die Übergangseinrichtung Haus Rafael ist ein gemeindenahes Angebot der Rehabilitation.\nIn einem ganzheitlichen Programm werden Leistungen der medizinisch-beruflichen und sozialen Rehabilitation erbracht. Die einzelnen Maßnahmen sind aufeinander abgestimmt und wirken zusammen, um eine Stabilisierung des Gesundheitszustandes zu erreichen sowie die Teilhabe am gesellschaftlichen und beruflichen Leben zu ermöglichen.\n\nRehabilitationsprogramm:\nDen Rahmen für das gesamte Reha-Programm bildet das therapeutische Milieu der Einrichtung. Es ist gekennzeichnet durch die überschaubare Größe, eine familienähnliche moderne Wohnatmosphäre sowie ein klar strukturiertes und gruppenbezogenes Lernfeld.\nIm Mittelpunkt der Reha-Maßnahme steht das Ziel der Befähigung zu einer weitgehend selbständigen Lebensführung. Deshalb ist das Zusammenleben im \"Haus Rafael\" von größtmöglicher Alltagsnähe und durch das Prinzip der Eigenverantwortung geprägt. Die genzheitliche individuelle Betreuung wird durch das Bezugstherapeutensystem gewährleistet.'\n");
        mWritableDB.insert(DBAboutUsEntry.TABLE_NAME, null, val);

        val = new ContentValues();
        val.put(DBAboutUsEntry.COLUMN_HEADING, "dsfddfdsf");
        val.put(DBAboutUsEntry.COLUMN_TEXT, "ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
        mWritableDB.insert(DBAboutUsEntry.TABLE_NAME, null, val);
    }

    private void fillTeamList() {
        ContentValues val = new ContentValues();
        val.put(DBMemberEntry.COLUMN_NAME, "Lalu");
        val.put(DBMemberEntry.COLUMN_PICTURE, encodeBitmapToByteArray(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_milka_lalu)));
        val.put(DBMemberEntry.COLUMN_DESCRIPTION, "sleeps, sleeps, sleeps, shits, sleeps");
        val.put(DBMemberEntry.COLUMN_PHONE, "8374 78939");
        val.put(DBMemberEntry.COLUMN_FAX, "3333 33333");
        val.put(DBMemberEntry.COLUMN_EMAIL, "lalu@haus-rafael.ko");
        mWritableDB.insert(DBMemberEntry.TABLE_NAME, null, val);

        val = new ContentValues();
        val.put(DBMemberEntry.COLUMN_NAME, "Schorsch");
        val.put(DBMemberEntry.COLUMN_PICTURE, encodeBitmapToByteArray(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_milka_lalu)));
        val.put(DBMemberEntry.COLUMN_DESCRIPTION, "sleeps, sleeps, sleeps, shits, sleeps");
        val.put(DBMemberEntry.COLUMN_PHONE, "8374 78939");
        val.put(DBMemberEntry.COLUMN_FAX, "3333 33333");
        val.put(DBMemberEntry.COLUMN_EMAIL, "schorschl@haus-rafael.ko");
        mWritableDB.insert(DBMemberEntry.TABLE_NAME, null, val);

        val = new ContentValues();
        val.put(DBMemberEntry.COLUMN_NAME, "Gärber");
        val.put(DBMemberEntry.COLUMN_PICTURE, encodeBitmapToByteArray(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_milka_lalu)));
        val.put(DBMemberEntry.COLUMN_DESCRIPTION, "sleeps, sleeps, sleeps, shits, sleeps");
        val.put(DBMemberEntry.COLUMN_PHONE, "8374 78939");
        val.put(DBMemberEntry.COLUMN_FAX, "3333 33333");
        val.put(DBMemberEntry.COLUMN_EMAIL, "gärber@haus-rafael.ko");
        mWritableDB.insert(DBMemberEntry.TABLE_NAME, null, val);

        val = new ContentValues();
        val.put(DBMemberEntry.COLUMN_NAME, "ddddddd");
        val.put(DBMemberEntry.COLUMN_PICTURE, encodeBitmapToByteArray(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_milka_lalu)));
        val.put(DBMemberEntry.COLUMN_DESCRIPTION, "sleeps, sleeps, sleeps, shits, sleeps");
        val.put(DBMemberEntry.COLUMN_PHONE, "8374 78939");
        val.put(DBMemberEntry.COLUMN_FAX, "3333 33333");
        val.put(DBMemberEntry.COLUMN_EMAIL, "lalu@haus-rafael.ko");
        mWritableDB.insert(DBMemberEntry.TABLE_NAME, null, val);

        val = new ContentValues();
        val.put(DBMemberEntry.COLUMN_NAME, "xd");
        val.put(DBMemberEntry.COLUMN_PICTURE, encodeBitmapToByteArray(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_milka_lalu)));
        val.put(DBMemberEntry.COLUMN_DESCRIPTION, "sleeps, sleeps, sleeps, shits, sleeps");
        val.put(DBMemberEntry.COLUMN_PHONE, "8374 78939");
        val.put(DBMemberEntry.COLUMN_FAX, "3333 33333");
        val.put(DBMemberEntry.COLUMN_EMAIL, "lalu@haus-rafael.ko");
        mWritableDB.insert(DBMemberEntry.TABLE_NAME, null, val);

        val = new ContentValues();
        val.put(DBMemberEntry.COLUMN_NAME, "hehe");
        val.put(DBMemberEntry.COLUMN_PICTURE, encodeBitmapToByteArray(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_milka_lalu)));
        val.put(DBMemberEntry.COLUMN_DESCRIPTION, "sleeps, sleeps, sleeps, shits, sleeps");
        val.put(DBMemberEntry.COLUMN_PHONE, "8374 78939");
        val.put(DBMemberEntry.COLUMN_FAX, "3333 33333");
        val.put(DBMemberEntry.COLUMN_EMAIL, "lalu@haus-rafael.ko");
        mWritableDB.insert(DBMemberEntry.TABLE_NAME, null, val);

        val = new ContentValues();
        val.put(DBMemberEntry.COLUMN_NAME, "tgteretg");
        val.put(DBMemberEntry.COLUMN_PICTURE, encodeBitmapToByteArray(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_milka_lalu)));
        val.put(DBMemberEntry.COLUMN_DESCRIPTION, "sleeps, sleeps, sleeps, shits, sleeps");
        val.put(DBMemberEntry.COLUMN_PHONE, "8374 78939");
        val.put(DBMemberEntry.COLUMN_FAX, "3333 33333");
        val.put(DBMemberEntry.COLUMN_EMAIL, "lalu@haus-rafael.ko");
        mWritableDB.insert(DBMemberEntry.TABLE_NAME, null, val);

        val = new ContentValues();
        val.put(DBMemberEntry.COLUMN_NAME, "qqqqqqqqq");
        val.put(DBMemberEntry.COLUMN_PICTURE, encodeBitmapToByteArray(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_milka_lalu)));
        val.put(DBMemberEntry.COLUMN_DESCRIPTION, "sleeps, sleeps, sleeps, shits, sleeps");
        val.put(DBMemberEntry.COLUMN_PHONE, "8374 78939");
        val.put(DBMemberEntry.COLUMN_FAX, "3333 33333");
        val.put(DBMemberEntry.COLUMN_EMAIL, "lalu@haus-rafael.ko");
        mWritableDB.insert(DBMemberEntry.TABLE_NAME, null, val);

        val = new ContentValues();
        val.put(DBMemberEntry.COLUMN_NAME, "fffffff");
        val.put(DBMemberEntry.COLUMN_PICTURE, encodeBitmapToByteArray(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_milka_lalu)));
        val.put(DBMemberEntry.COLUMN_DESCRIPTION, "sleeps, sleeps, sleeps, shits, sleeps");
        val.put(DBMemberEntry.COLUMN_PHONE, "8374 78939");
        val.put(DBMemberEntry.COLUMN_FAX, "3333 33333");
        val.put(DBMemberEntry.COLUMN_EMAIL, "lalu@haus-rafael.ko");
        mWritableDB.insert(DBMemberEntry.TABLE_NAME, null, val);

        val = new ContentValues();
        val.put(DBMemberEntry.COLUMN_NAME, "ggggggggggggggg");
        val.put(DBMemberEntry.COLUMN_PICTURE, encodeBitmapToByteArray(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_milka_lalu)));
        val.put(DBMemberEntry.COLUMN_DESCRIPTION, "sleeps, sleeps, sleeps, shits, sleeps");
        val.put(DBMemberEntry.COLUMN_PHONE, "8374 78939");
        val.put(DBMemberEntry.COLUMN_FAX, "3333 33333");
        val.put(DBMemberEntry.COLUMN_EMAIL, "lalu@haus-rafael.ko");
        mWritableDB.insert(DBMemberEntry.TABLE_NAME, null, val);
    }

    private void fillContact() {
        ContentValues val = new ContentValues();
        val.put(DBContactEntry.COLUMN_DESCRIPTION, "Haus Raffaela");
        val.put(DBContactEntry.COLUMN_PICTURE, encodeBitmapToByteArray(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_milka_lalu)));
        val.put(DBContactEntry.COLUMN_LOCATION, "Hammerbacherstr. 9a, 91058 Erlangen");
        val.put(DBContactEntry.COLUMN_PHONE, "09131 120880");
        val.put(DBContactEntry.COLUMN_FAX, "09131 120881");
        val.put(DBContactEntry.COLUMN_EMAIL, "hr@caritas-erlangen.de");
        val.put(DBContactEntry.COLUMN_WEBSITE, "https://caritas-erlangen.de/index.php/de/hilfe-bei-psychischer-erkrankung/uebergangseinrichtung-haus-rafael/");
        mWritableDB.insert(DBContactEntry.TABLE_NAME, null, val);
    }

    private void fillNews() {
        ContentValues val = new ContentValues();
        val.put(DBNewsEntry.COLUMN_TIMESTAMP, "2017-06-23T21:22:44+02:00");
        val.put(DBNewsEntry.COLUMN_SEVERITY, Severity.Attention.getValue());
        val.put(DBNewsEntry.COLUMN_TEXT, "Die Caritec wurde niedergebrannt. Deshalb fällt AT heute aus.");
        mWritableDB.insert(DBNewsEntry.TABLE_NAME, null, val);

        val = new ContentValues();
        val.put(DBNewsEntry.COLUMN_TIMESTAMP, "2017-06-23T11:11:11+11:11");
        val.put(DBNewsEntry.COLUMN_SEVERITY, Severity.Info.getValue());
        val.put(DBNewsEntry.COLUMN_TEXT, "???");
        mWritableDB.insert(DBNewsEntry.TABLE_NAME, null, val);

        val = new ContentValues();
        val.put(DBNewsEntry.COLUMN_TIMESTAMP, "6666-66-66T21:22:44+06:00");
        val.put(DBNewsEntry.COLUMN_SEVERITY, Severity.Warning.getValue());
        val.put(DBNewsEntry.COLUMN_TEXT, "hue");
        mWritableDB.insert(DBNewsEntry.TABLE_NAME, null, val);

        val = new ContentValues();
        val.put(DBNewsEntry.COLUMN_TIMESTAMP, "2017-06-23T21:52:44+02:00");
        val.put(DBNewsEntry.COLUMN_SEVERITY, Severity.Info.getValue());
        val.put(DBNewsEntry.COLUMN_TEXT, "Der kleine Christian Schirmer möchte bitte aus dem Spieleparadies abgeholt werden.");
        mWritableDB.insert(DBNewsEntry.TABLE_NAME, null, val);
    }


    private byte[] encodeBitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

}
