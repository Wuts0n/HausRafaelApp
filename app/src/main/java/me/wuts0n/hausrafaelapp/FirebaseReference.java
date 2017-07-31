package me.wuts0n.hausrafaelapp;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseReference {

    private static final FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    private static final DatabaseReference mDatabaseReference = mFirebaseDatabase.getReference();


    public static DatabaseReference getDatabaseReference() {
        return mDatabaseReference;
    }
}
