package me.wuts0n.hausrafaelapp;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.HashSet;
import java.util.Set;

import me.wuts0n.hausrafaelapp.adapter.NewsActivityAdapter;
import me.wuts0n.hausrafaelapp.database.DatabaseHelper;
import me.wuts0n.hausrafaelapp.database.NewsTable;

public class NewsActivity extends NavigateUpActivity {

    private NewsActivityAdapter mAdapter;
    private ProgressBar mProgressBar;
    private NewsTable mNewsTable;
    private Set<String> mKeys;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase readableDatabase = databaseHelper.getReadableDatabase();
        mNewsTable = new NewsTable(readableDatabase, null);

        mKeys = new HashSet<>();

        mAdapter = new NewsActivityAdapter(this, mNewsTable.selectAll(), mKeys);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.rv_news);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDatabaseReference = FirebaseReference.getDatabaseReference().child("news");
        attachDatabaseReadListener();
    }

    private void attachDatabaseReadListener() {
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {

                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String key = dataSnapshot.getKey();
                    mKeys.add(key);
                    mAdapter.swapCursor(mNewsTable.selectAll());
                    mProgressBar.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    mAdapter.swapCursor(mNewsTable.selectAll());
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    String key = dataSnapshot.getKey();
                    mKeys.remove(key);
                    mAdapter.swapCursor(mNewsTable.selectAll());
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


    public enum Severity {
        Info(0),
        Attention(1),
        Warning(2);

        private final int value;

        Severity(int val) {
            value = val;
        }

        public int getValue() {
            return value;
        }
    }
}
