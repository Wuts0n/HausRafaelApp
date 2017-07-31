package me.wuts0n.hausrafaelapp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import me.wuts0n.hausrafaelapp.databinding.ActivityMainBinding;
import me.wuts0n.hausrafaelapp.firebase.object.SimpleLinkObject;
import me.wuts0n.hausrafaelapp.listener.MainActivityClickListener;
import me.wuts0n.hausrafaelapp.service.NewsService;
import me.wuts0n.hausrafaelapp.utils.IntentUtils;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityMainBinding mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainActivityClickListener listener = new MainActivityClickListener(this, mBinding);
        mBinding.tvMenuNews.setOnClickListener(listener);
        mBinding.tvMenuTeam.setOnClickListener(listener);
        mBinding.tvMenuContact.setOnClickListener(listener);
        mBinding.tvMenuAboutUs.setOnClickListener(listener);
        mBinding.tvMenuBusConnections.setOnClickListener(listener);
        mBinding.tvMenuGoogleMaps.setOnClickListener(listener);

        Intent intent = new Intent(this, NewsService.class);
        startService(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDatabaseReference = FirebaseReference.getDatabaseReference().child("simple_links");
        attachDatabaseReadListener();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mDatabaseReference.removeEventListener(mChildEventListener);
        mDatabaseReference = null;
        mChildEventListener = null;
    }

    private void attachDatabaseReadListener() {
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {

                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    updateLink(dataSnapshot);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    updateLink(dataSnapshot);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    deleteLink(dataSnapshot);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.w("FirebaseDatabaseError", databaseError.toString());
                }
            };
            mDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }

    private void updateLink(DataSnapshot dataSnapshot) {
        try {
            SimpleLinkObject entry = dataSnapshot.getValue(SimpleLinkObject.class);
            String key = dataSnapshot.getKey();
            String url = (entry != null ? entry.getUrl() : null);
            if (key.equals("bus_connection")) {
                IntentUtils.setBusConnectionUrl(url);
            } else if (key.equals("maps")) {
                IntentUtils.setMapsUrl(url);
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    private void deleteLink(DataSnapshot dataSnapshot) {
        String key = dataSnapshot.getKey();
        if (key.equals("bus_connection")) {
            IntentUtils.setBusConnectionUrl(null);
        } else if (key.equals("maps")) {
            IntentUtils.setMapsUrl(null);
        }
    }
}
