package me.wuts0n.hausrafaelapp.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import me.wuts0n.hausrafaelapp.R;
import me.wuts0n.hausrafaelapp.database.DatabaseHelper;
import me.wuts0n.hausrafaelapp.database.NewsTable;
import me.wuts0n.hausrafaelapp.firebase.object.NewsObject;
import me.wuts0n.hausrafaelapp.utils.IntentUtils;


public class NewsIntentService extends Service {

    private final static int NOTIFICATION_ID = 420;
    private NewsTable mNewsTable;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        DatabaseHelper dbhelper = new DatabaseHelper(this);
        SQLiteDatabase readableDatabase = dbhelper.getReadableDatabase();
        SQLiteDatabase writableDatabase = dbhelper.getWritableDatabase();
        mNewsTable = new NewsTable(readableDatabase, writableDatabase);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = firebaseDatabase.getReference().child("news");
        attachFirebaseReadListener();
        return START_REDELIVER_INTENT;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDatabaseReference.removeEventListener(mChildEventListener);
        mChildEventListener = null;
    }

    private void attachFirebaseReadListener() {
        if (mChildEventListener == null) {
            final Context context = this;
            mChildEventListener = new ChildEventListener() {

                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String key = dataSnapshot.getKey();
                    NewsObject entry = dataSnapshot.getValue(NewsObject.class);
                    if (entry != null && !(mNewsTable.existsRow(key))) {
                        insertSQLiteRow(key, entry.getText(), entry.getSeverity());
                        createNotification(entry, mNewsTable.getID(key));
                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    String key = dataSnapshot.getKey();
                    NewsObject entry = dataSnapshot.getValue(NewsObject.class);
                    if (entry != null) {
                        updateSQLiteRow(key, entry.getText(), entry.getSeverity());
                        createNotification(entry, mNewsTable.getID(key));
                    }
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    String key = dataSnapshot.getKey();
                    int id = mNewsTable.getID(key);
                    mNewsTable.deleteRow(key);
                    NotificationManagerCompat.from(context).cancel(id);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.wtf("FirebaseDatabaseError", databaseError.toString());
                }
            };
            mDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }

    private void insertSQLiteRow(CharSequence key, CharSequence text, int severity) {
        mNewsTable.insertRow(key, text, severity);
    }

    private void updateSQLiteRow(CharSequence key, CharSequence text, int severity) {
        mNewsTable.updateRow(key, text, severity);
    }

    private void createNotification(NewsObject entry, int id) {
        PendingIntent pendingIntent = PendingIntent.getActivity(this, NOTIFICATION_ID,
                IntentUtils.getNewsActivityIntent(this), PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = (new NotificationCompat.Builder(this))
                .setContentTitle(getString(R.string.menu_news))
                .setContentText(entry.getText())
                .setSmallIcon(R.drawable.ic_caritas)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();
        NotificationManagerCompat.from(this).notify(id, notification);
    }
}
