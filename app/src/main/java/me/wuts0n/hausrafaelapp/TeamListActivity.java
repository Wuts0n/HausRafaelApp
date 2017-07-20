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

import me.wuts0n.hausrafaelapp.adapter.TeamListActivityAdapter;
import me.wuts0n.hausrafaelapp.firebase.object.TeamMemberObject;

public class TeamListActivity extends NavigateUpActivity {

    private TeamListActivityAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private Map<String, TeamMemberObject> mEntries;
    private ChildEventListener mChildEventListener;
    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_list);

        mEntries = new HashMap<>();
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mAdapter = new TeamListActivityAdapter(this, new Object[0]);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("team_member");
        attachDatabaseReadListener();

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_team_list);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContent();
    }

    private void attachDatabaseReadListener() {
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {

                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String key = dataSnapshot.getKey();
                    TeamMemberObject entry = dataSnapshot.getValue(TeamMemberObject.class);
                    if (entry != null) {
                        entry.setName(key);
                        mEntries.put(key, entry);
                        setContent();
                    }
                    mProgressBar.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    String key = dataSnapshot.getKey();
                    TeamMemberObject entry = dataSnapshot.getValue(TeamMemberObject.class);
                    if (entry != null) {
                        entry.setName(key);
                        mEntries.put(key, entry);
                        setContent();
                    }
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    String key = dataSnapshot.getKey();
                    mEntries.remove(key);
                    setContent();
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

    private void setContent() {
        if (!this.isDestroyed()) {
            mAdapter.swapArray(transformMapToArray(mEntries));
        }
    }

    @NonNull
    private Object[] transformMapToArray(Map<?, ?> map) {
        return map.values().toArray();
    }

}
