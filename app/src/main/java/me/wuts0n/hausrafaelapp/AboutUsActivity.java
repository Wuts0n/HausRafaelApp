package me.wuts0n.hausrafaelapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import me.wuts0n.hausrafaelapp.adapter.AboutUsActivityAdapter;
import me.wuts0n.hausrafaelapp.firebase.object.AboutUsObject;


public class AboutUsActivity extends NavigateUpActivity {

    private AboutUsActivityAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;
    private Map<String, AboutUsObject> mEntries;
    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        mEntries = new HashMap<>();
        mAdapter = new AboutUsActivityAdapter(this, new Object[0]);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        if (mAdapter.getItemCount() <= 0) {
            mProgressBar.setVisibility(View.VISIBLE);
        }

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("about_us");
        attachDatabaseReadListener();

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_about_us);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
    }


    private void attachDatabaseReadListener() {
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {

                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String key = dataSnapshot.getKey();
                    AboutUsObject entry = dataSnapshot.getValue(AboutUsObject.class);
                    mEntries.put(key, entry);
                    update();
                    mProgressBar.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    String key = dataSnapshot.getKey();
                    AboutUsObject entry = dataSnapshot.getValue(AboutUsObject.class);
                    mEntries.put(key, entry);
                    update();
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    String key = dataSnapshot.getKey();
                    mEntries.remove(key);
                    update();
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.wtf("DatabaseError", databaseError.toString());
                }
            };
            mDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }

    private void update() {
        mAdapter.swapArray(transformMapToArray(mEntries));
    }

    @NonNull
    private Object[] transformMapToArray(Map<?, ?> map) {
        return map.values().toArray();
    }
}
