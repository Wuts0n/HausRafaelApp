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
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import me.wuts0n.hausrafaelapp.adapter.TeamListActivityAdapter;
import me.wuts0n.hausrafaelapp.firebase.object.TeamMemberObject;

public class TeamListActivity extends NavigateUpActivity {

    private TeamListActivityAdapter mAdapter;
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

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = firebaseDatabase.getReference().child("team_member");
        attachDatabaseReadListener();

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.rv_team_list);
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
                    updateChild(dataSnapshot);
                    mProgressBar.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    updateChild(dataSnapshot);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    deleteChild(dataSnapshot);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.w("DatabaseError", databaseError.toString());
                }
            };
            mDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }

    private void updateChild(DataSnapshot dataSnapshot) {
        String key = dataSnapshot.getKey();
        try {
            TeamMemberObject entry = dataSnapshot.getValue(TeamMemberObject.class);
            if (entry != null) {
                entry.setName(key.replace(" ", ". "));
                mEntries.put(key, entry);
                setContent();
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    private void deleteChild(DataSnapshot dataSnapshot) {
        String key = dataSnapshot.getKey();
        mEntries.remove(key);
        setContent();
    }

    private void setContent() {
        if (!this.isDestroyed()) {
            mAdapter.swapArray(transformMapToArray(mEntries));
        }
    }

    @NonNull
    private Object[] transformMapToArray(Map<?, ?> map) {
        Object[] array = map.values().toArray();
        Arrays.sort(array);
        return array;
    }

}
