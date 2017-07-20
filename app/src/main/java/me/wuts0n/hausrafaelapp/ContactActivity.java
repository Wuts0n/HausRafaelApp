package me.wuts0n.hausrafaelapp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import me.wuts0n.hausrafaelapp.databinding.ActivityContactBinding;
import me.wuts0n.hausrafaelapp.firebase.object.ContactObject;
import me.wuts0n.hausrafaelapp.listener.ContactClickListener;
import me.wuts0n.hausrafaelapp.utils.UriUtils;

public class ContactActivity extends NavigateUpActivity {

    private ActivityContactBinding mBinding;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;
    private ContactObject lastChanged;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_contact);

        if (mBinding.tvContactHeading.getText().toString().isEmpty()) {
            mBinding.progressBar.setVisibility(View.VISIBLE);
        }

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("contact");
        attachDatabaseReadListener();

        ContactClickListener listener = new ContactClickListener(this, mBinding);

        mBinding.tvContactLocation.setOnClickListener(listener);
        mBinding.tvContactPhone.setOnClickListener(listener);
        mBinding.tvContactFax.setOnClickListener(listener);
        mBinding.tvContactEmail.setOnClickListener(listener);
        mBinding.tvContactInternet.setOnClickListener(listener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (lastChanged != null) {
            setContent(lastChanged);
        }
    }

    private void attachDatabaseReadListener() {
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    ContactObject entry = dataSnapshot.getValue(ContactObject.class);
                    setContent(entry);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    ContactObject entry = dataSnapshot.getValue(ContactObject.class);
                    setContent(entry);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
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

    private void setContent(ContactObject entry) {
        lastChanged = entry;
        if (!this.isDestroyed()) {
            mBinding.tvContactHeading.setText(entry.getDescription());
            mBinding.tvContactLocation.setText(entry.getLocation().replaceAll("[,;] ", ",\n"));
            mBinding.tvContactPhone.setText(entry.getPhone());
            mBinding.tvContactFax.setText(entry.getFax());
            mBinding.tvContactEmail.setText(entry.getEmail());
            String url = entry.getWebsite().trim();
            mBinding.tvContactInternet.setContentDescription(url);
            mBinding.tvContactInternet.setText(UriUtils.getAuthority(url));
            ImageView ivPicture = mBinding.ivPicture;
            Glide.with(ivPicture.getContext()).load(entry.getPicture()).into(ivPicture);
        }
        mBinding.progressBar.setVisibility(View.INVISIBLE);
    }
}
