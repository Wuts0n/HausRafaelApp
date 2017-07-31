package me.wuts0n.hausrafaelapp;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseReference {

    private static final DatabaseReference mDatabaseReference =
            FirebaseDatabase.getInstance().getReference();


    public static DatabaseReference getDatabaseReference() {
        return mDatabaseReference;
    }
}
