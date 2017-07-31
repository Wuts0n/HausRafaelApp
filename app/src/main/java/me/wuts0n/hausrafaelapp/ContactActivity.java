package me.wuts0n.hausrafaelapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;

import me.wuts0n.hausrafaelapp.firebase.object.ContactObject;
import me.wuts0n.hausrafaelapp.listener.ContactClickListener;
import me.wuts0n.hausrafaelapp.utils.UriUtils;

public class ContactActivity extends NavigateUpActivity {

    private View mProgressBar;
    private TextView mTvContactLocation;
    private TextView mTvContactPhone;
    private TextView mTvContactFax;
    private TextView mTvContactEmail;
    private TextView mTvContactInternet;
    private TextView mTvContactHeading;
    private ImageView mIvPicture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        ContactClickListener listener = new ContactClickListener(this);

        mProgressBar = findViewById(R.id.progressBar);
        mTvContactLocation = (TextView) findViewById(R.id.tv_contact_location);
        mTvContactPhone = (TextView) findViewById(R.id.tv_contact_phone);
        mTvContactFax = (TextView) findViewById(R.id.tv_contact_fax);
        mTvContactEmail = (TextView) findViewById(R.id.tv_contact_email);
        mTvContactInternet = (TextView) findViewById(R.id.tv_contact_internet);
        mTvContactHeading = (TextView) findViewById(R.id.tv_contact_heading);
        mIvPicture = (ImageView) findViewById(R.id.iv_contact_picture);

        mTvContactLocation.setOnClickListener(listener);
        mTvContactPhone.setOnClickListener(listener);
        mTvContactFax.setOnClickListener(listener);
        mTvContactEmail.setOnClickListener(listener);
        mTvContactInternet.setOnClickListener(listener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDatabaseReference = FirebaseReference.getDatabaseReference().child("contact");
        attachDatabaseReadListener();
    }

    private void attachDatabaseReadListener() {
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    updateContact(dataSnapshot);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    updateContact(dataSnapshot);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
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

    private void updateContact(DataSnapshot dataSnapshot) {
        try {
            ContactObject entry = dataSnapshot.getValue(ContactObject.class);
            setContent(entry);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    private void setContent(ContactObject entry) {
        if (!this.isDestroyed()) {
            mTvContactHeading.setText(entry.getDescription());
            mTvContactLocation.setText(entry.getLocation().replaceAll("[,;] ", ",\n"));
            mTvContactPhone.setText(entry.getPhone());
            mTvContactFax.setText(entry.getFax());
            mTvContactEmail.setText(entry.getEmail());
            String url = entry.getWebsite().trim();
            mTvContactInternet.setContentDescription(url);
            mTvContactInternet.setText(UriUtils.getAuthority(url));
            Glide.with(mIvPicture.getContext()).load(entry.getPicture()).into(mIvPicture);
        }
        mProgressBar.setVisibility(View.INVISIBLE);
    }
}
